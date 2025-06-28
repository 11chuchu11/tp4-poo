package controllers;

import dtos.*;
import models.*;
import types.TipoGenero;
import types.TipoTarjeta;
import utils.BusquedaBinaria;

import java.util.*;

public class VentasController {

    private static VentasController INSTANCE;
    private FuncionController funcionController;
    private DescuentoController descuentoController;
    private List<Venta> listadoVentas;
    private List<Entrada> listadoEntradas;
    private List<Combo> listadoCombos;

    private VentasController() {
        listadoVentas = new ArrayList<>();
        listadoEntradas = new ArrayList<>();
        listadoCombos = new ArrayList<>();
        precargarData();
    }

    //aca se implementa el patron singleton
    public static synchronized VentasController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new VentasController();
        return INSTANCE;
    }

    // ____________________________________HELPERS____________________________________

    private int getID(List<Data> list) {
        int listSize = list.size();
        return list.get(listSize - 1).getID() + 1;
    }


    private boolean existeEntrada(Entrada entrada) {
        for (Entrada ent : listadoEntradas) {
            if (entrada.equals(ent)) return true;
        }
        return false;
    }

    private boolean existeCombo(Combo combo) {
        for (Combo comb : listadoCombos) {
            if (comb.equals(combo)) return true;
        }
        return false;
    }

    private float calcularTotalPorVentaCombos(VentaDTO ventaDTO) {
        descuentoController = DescuentoController.getINSTANCE();
        float totalCombos = 0;
        try {
            for (ComboDTO combo : ventaDTO.getCombos()) {
                totalCombos = +(combo.getPrecio() * (1 - DescuentoController.getPorcentajeDescuentoPorTarjeta(ventaDTO.getTarjetaDescuento().getTipoTarjeta())));
            }
            return totalCombos;
        } catch (Exception ex) {
            return 0;
        }
    }

    private float calcularTotalPorVentaDeEntradas(VentaDTO ventaDTO) {
        descuentoController = DescuentoController.getINSTANCE();
        List<EntradaDTO> entradasDeVenta = buscarEntradasPorVenta(ventaDTO.getID());
        float totalEntradas = 0;
        try {
            if (entradasDeVenta.size() == 0) return 0;
            for (EntradaDTO entrada : entradasDeVenta) {
                totalEntradas = totalEntradas + (entrada.getPrecio() * (1 - descuentoController.getPorcentajeDescuento(ventaDTO.getFuncion().getPelicula().getCondicionesDescuento().getID())));
            }
            return totalEntradas;
        } catch (Exception ex) {
            return 0;
        }
    }

    private float calcularTotalVenta(VentaDTO ventaDTO) {
        return calcularTotalPorVentaCombos(ventaDTO) + calcularTotalPorVentaDeEntradas(ventaDTO);
    }


    public void precargarData() {
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            listadoCombos.add(new Combo(i, "Combo " + i, 1000 + (i * 100), random.nextInt(10) + 1)); // descuentoID del 1 al 10
        }

        List<TarjetaDescuento> tarjetas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tarjetas.add(new TarjetaDescuento(i, TipoTarjeta.UADE, "TD" + (1000 + i)));
        }

        int ventaIDCounter = 1;
        int entradaIDCounter = 1;
        int nroAsientoCounter = 1;

        for (int i = 0; i < 10; i++) {
            int funcionID = i + 1;
            TarjetaDescuento tarjeta = tarjetas.get(i);
            Venta venta = new Venta(ventaIDCounter, funcionID, tarjeta);

            int cantidadEntradas = 5 + random.nextInt(16);
            for (int j = 0; j < cantidadEntradas; j++) {
                Entrada entrada = new Entrada(entradaIDCounter++, nroAsientoCounter++, funcionID, 1800f, ventaIDCounter);
                listadoEntradas.add(entrada);
            }

            // Generar entre 5 y 20 combos por ID (puede haber repetidos)
            int cantidadCombos = 5 + random.nextInt(16);
            for (int j = 0; j < cantidadCombos; j++) {
                int comboIdRandom = listadoCombos.get(random.nextInt(listadoCombos.size())).getID();
                venta.getCombosIDs().add(comboIdRandom);
            }

            listadoVentas.add(venta);
            ventaIDCounter++;
        }
    }

    // ____________________________________METHODS____________________________________

    public VentaDTO buscarVentaPorID(int ventaID) {
        funcionController = FuncionController.getINSTANCE();
        try {
            Venta ventaBuscada = BusquedaBinaria.buscarPorId(listadoVentas, ventaID);
            if (ventaBuscada == null) return null;

            return ventaToDTO(ventaBuscada);
        } catch (Exception ex) {
            return null;
        }
    }

    public VentaDTO buscarVentaPorFuncion(int funcionID) {
        funcionController = FuncionController.getINSTANCE();
        try {
            for (Venta venta : listadoVentas) {
                if (venta.getFuncionID() == funcionID) return ventaToDTO(venta);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * View a desarrollar
     *
     * @param genero
     * @return
     */
    public List<VentaDTO> buscarVentasPorGenero(TipoGenero genero) {
        funcionController = FuncionController.getINSTANCE();
        try {
            List<VentaDTO> ventasDtos = new ArrayList<>();
            List<FuncionDTO> funcionesBuscadas = funcionController.buscarFuncionesPorGenero(genero);

            if (funcionesBuscadas.isEmpty()) return ventasDtos;

            for (FuncionDTO funcion : funcionesBuscadas) {
                VentaDTO venta = buscarVentaPorFuncion(funcion.getID());
                if (venta == null) return new ArrayList<>();
                ventasDtos.add(venta);
            }
            return ventasDtos;
        } catch (Exception ex) {
            return null;
        }
    }

    public EntradaDTO buscarEntradaPorID(int entradaID) {
        funcionController = FuncionController.getINSTANCE();
        try {
            Entrada entradaBuscada = BusquedaBinaria.buscarPorId(listadoEntradas, entradaID);
            if (entradaBuscada == null) return null;
            return entradaToDto(entradaBuscada);

        } catch (Exception ex) {
            return null;
        }
    }

    public List<EntradaDTO> buscarEntradasPorVenta(int ventaID) {
        try {
            List<EntradaDTO> entradasBuscadas = new ArrayList<>();
            for (Entrada entrada : listadoEntradas) {
                if (entrada.getVentaID() == ventaID) entradasBuscadas.add(entradaToDto(entrada));
            }

            return entradasBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public ComboDTO buscarComboPorID(int comboID) {
        try {
            Combo comboBuscado = BusquedaBinaria.buscarPorId(listadoCombos, comboID);
            if (comboBuscado == null) return null;

            return comboToDTO(comboBuscado);
        } catch (Exception ex) {
            return null;
        }
    }

    public ComboDTO comboMasVendido() {
        descuentoController = DescuentoController.getINSTANCE();
        try {
            PriorityQueue<Combo> combosMasVendidos = new PriorityQueue<>(Comparator.comparingInt((comb) -> {
                int count = 0;
                for (Venta venta : listadoVentas) {
                    for (int id : venta.getCombosIDs()) {
                        if (id == comb.getID()) count++;
                    }
                }

                return count;
            }));
            combosMasVendidos.addAll(listadoCombos);

            return comboToDTO(combosMasVendidos.poll());
        } catch (Exception ex) {
            return null;
        }
    }

    public float recaudacionPorFuncion(int funcionID) {
        VentaDTO ventaDTO = buscarVentaPorFuncion(funcionID);

        if (ventaDTO == null) return 0;
        return calcularTotalVenta(ventaDTO);
    }

    /**
     * Caso de secuencia a desarrollar
     *
     * @param peliculaID
     * @return
     */
    public float recaudacionPorPelicula(int peliculaID) {
        funcionController = FuncionController.getINSTANCE();
        try {
            VentaDTO ventaDTO;
            List<FuncionDTO> funciones = funcionController.buscarFuncionesPorPelicula(peliculaID);
            if (funciones.isEmpty()) return 0;
            float totalrecuadado = 0.0f;

            for (FuncionDTO funcionDTO : funciones) {
                ventaDTO = buscarVentaPorFuncion(funcionDTO.getID());
                if (ventaDTO == null) return 0;
                totalrecuadado = +calcularTotalVenta(ventaDTO);
            }
            return totalrecuadado;
        } catch (Exception ex) {
            return 0;
        }
    }

    public float recaudacionPorTarjetaDescuento(TipoTarjeta tipoTarjeta) {
        try {
            float totalrecuadado = 0.0f;
            VentaDTO ventaDTO;
            for (Venta venta : listadoVentas) {
                if (venta.getTarjetaDescuento().getTipoTarjeta() == tipoTarjeta) {
                    ventaDTO = buscarVentaPorID(venta.getID());
                    totalrecuadado = +calcularTotalVenta(ventaDTO);
                }
            }
            return totalrecuadado;
        } catch (Exception ex) {
            return 0;
        }
    }

    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    private VentaDTO ventaToDTO(Venta venta) {
        funcionController = FuncionController.getINSTANCE();
        VentaDTO ventaDTO = new VentaDTO(venta);
        ventaDTO.setFuncion(funcionController.buscarFuncionPorID(venta.getFuncionID()));
        List<ComboDTO> listadoCombos = new ArrayList<>();
        for (int comboID : venta.getCombosIDs()) listadoCombos.add(buscarComboPorID(comboID));
        ventaDTO.setCombos(listadoCombos);

        return ventaDTO;
    }

    private EntradaDTO entradaToDto(Entrada entrada) {
        funcionController = FuncionController.getINSTANCE();
        EntradaDTO entradaDTO = new EntradaDTO(entrada);
        entradaDTO.setFuncion(funcionController.buscarFuncionPorID(entrada.getFuncionID()));
        entradaDTO.setVenta(buscarVentaPorID(entrada.getVentaID()));
        return entradaDTO;
    }

    private ComboDTO comboToDTO(Combo combo) {
        descuentoController = DescuentoController.getINSTANCE();
        ComboDTO comboDTO = new ComboDTO(combo);
        comboDTO.setCondicionesDescuento(descuentoController.buscarDescuentoPorId(combo.getDescuentoID()));
        return comboDTO;
    }
}
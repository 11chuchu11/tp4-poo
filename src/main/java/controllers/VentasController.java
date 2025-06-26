package controllers;

import dtos.*;
import models.*;
import types.TipoGenero;
import types.TipoTarjeta;
import utils.BusquedaBinaria;

import java.util.*;

public class VentasController {

    private static VentasController INSTANCE;
    private final FuncionController funcionController;
    private final DescuentoController descuentoController;
    private final List<Venta> listadoVentas;
    private final List<Entrada> listadoEntradas;
    private final List<Combo> listadoCombos;

    private VentasController() {
        funcionController = FuncionController.getINSTANCE();
        descuentoController = DescuentoController.getINSTANCE();
        listadoVentas = new ArrayList<>();
        listadoEntradas = new ArrayList<>();
        listadoCombos = new ArrayList<>();
    }

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

    private float calcularTotalPorVentadeEntradas(VentaDTO ventaDTO) {
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
        return calcularTotalPorVentaCombos(ventaDTO) + calcularTotalPorVentadeEntradas(ventaDTO);
    }

    public void precargarData(){
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
        try {
            Venta ventaBuscada = BusquedaBinaria.buscarPorId(listadoVentas, ventaID);
            if (ventaBuscada == null) return null;
            VentaDTO ventaDTO = ventaToDTO(ventaBuscada);
            List<ComboDTO> comboDTOS = new ArrayList<>();
            ComboDTO comboBuscado;
            for (int comboID : ventaBuscada.getCombosIDs()) {
                comboBuscado = buscarComboPorID(comboID);
                if (comboBuscado != null) comboDTOS.add(comboBuscado);
            }
            FuncionDTO funcionDTO = funcionController.buscarFuncionPorID(ventaBuscada.getFuncionID());
            ventaDTO.setCombos(comboDTOS);
            ventaDTO.setFuncion(funcionDTO);
            return ventaDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    public VentaDTO buscarVentaPorFuncion(int funcionID) {
        try {
            VentaDTO ventaBuscada = null;
            List<ComboDTO> comboDTOS = new ArrayList<>();
            FuncionDTO funcionDTO;
            ComboDTO comboBuscado;
            for (Venta venta : listadoVentas) {
                if (venta.getFuncionID() == funcionID) {
                    funcionDTO = funcionController.buscarFuncionPorID(venta.getFuncionID());
                    ventaBuscada = ventaToDTO(venta);
                    for (int id : venta.getCombosIDs()) {
                        comboBuscado = buscarComboPorID(id);
                        if (comboBuscado != null) comboDTOS.add(comboBuscado);
                    }
                    ventaBuscada.setCombos(comboDTOS);
                    ventaBuscada.setFuncion(funcionDTO);
                    return ventaBuscada;
                }
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
        try {
            Entrada entradaBuscada = BusquedaBinaria.buscarPorId(listadoEntradas, entradaID);
            if (entradaBuscada == null) return null;
            FuncionDTO funciontDTO = funcionController.buscarFuncionPorID(entradaBuscada.getFuncionID());
            EntradaDTO entradaDTO = entradaToDto(entradaBuscada);
            VentaDTO ventaDTO = buscarVentaPorID(entradaBuscada.getVentaID());
            entradaDTO.setFuncion(funciontDTO);
            entradaDTO.setVenta(ventaDTO);
            return entradaDTO;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<EntradaDTO> buscarEntradasPorVenta(int ventaID) {
        try {
            List<EntradaDTO> entradasBuscadas = new ArrayList<>();
            for (Entrada entrada : listadoEntradas) {
                if (entrada.getVentaID() == ventaID) {
                    entradasBuscadas.add(entradaToDto(entrada));
                }
            }
            return entradasBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public ComboDTO buscarComboPorID(int comboID) {
        try {
            Combo comboBuscado = BusquedaBinaria.buscarPorId(listadoCombos, comboID);
            CondicionesDescuentoDTO descuentoDTO = descuentoController.buscarDescuentoPorId(comboBuscado.getDescuentoID());
            if (comboBuscado == null) return null;
            ComboDTO comboDTO = comboToDTO(comboBuscado);
            comboDTO.setCondicionesDescuento(descuentoDTO);
            return comboDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    public ComboDTO comboMasVendido() {
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
            Combo comboMasVendido = combosMasVendidos.poll();
            ComboDTO comboDTO = comboToDTO(comboMasVendido);
            comboDTO.setCondicionesDescuento(descuentoController.buscarDescuentoPorId(comboMasVendido.getDescuentoID()));
            return comboDTO;
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
        return new VentaDTO(venta);
    }

    private EntradaDTO entradaToDto(Entrada entrada) {
        return new EntradaDTO(entrada);
    }

    private ComboDTO comboToDTO(Combo combo) {
        return new ComboDTO(combo);
    }


}
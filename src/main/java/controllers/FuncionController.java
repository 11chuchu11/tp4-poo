package controllers;

import dtos.*;
import models.Entrada;
import models.Funcion;
import models.Pelicula;
import models.Sala;
import types.TipoGenero;
import utils.BusquedaBinaria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuncionController {

    private static FuncionController INSTANCE;
    private List<Funcion> listadoFunciones;
    private SucursalController sucursalController;
    private PeliculasController peliculasController;
    private VentasController ventasController;

    private FuncionController() {
        listadoFunciones = new ArrayList<>();
        precargarFunciones();
    }

    public static synchronized FuncionController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new FuncionController();
        return INSTANCE;
    }

    // ____________________________________HELPERS____________________________________
    private int getFuncionID() {
        int listSize = listadoFunciones.size();
        if (listSize == 0) return 1;
        return listadoFunciones.get(listSize - 1).getID() + 1;
    }

    private boolean existeFuncion(Funcion funcion) {
        for (Funcion f : listadoFunciones) {
            if (f.equals(funcion)) return true;
        }
        return false;
    }

    private void precargarFunciones() {
        listadoFunciones.add(new Funcion(1, LocalDateTime.of(2025, 6, 24, 18, 30), "18:30", 1, 1, Arrays.asList(1, 2)));
        listadoFunciones.add(new Funcion(2, LocalDateTime.of(2025, 6, 24, 21, 0), "21:00", 1, 2, Arrays.asList(3, 4)));
        listadoFunciones.add(new Funcion(3, LocalDateTime.of(2025, 6, 25, 15, 45), "15:45", 2, 3, Arrays.asList(5, 6)));
        listadoFunciones.add(new Funcion(4, LocalDateTime.of(2025, 6, 25, 20, 15), "20:15", 2, 4, Arrays.asList(7, 8)));
        listadoFunciones.add(new Funcion(5, LocalDateTime.of(2025, 6, 26, 17, 0), "17:00", 3, 5, Arrays.asList(9, 10)));
        listadoFunciones.add(new Funcion(6, LocalDateTime.of(2025, 6, 26, 22, 30), "22:30", 3, 6, Arrays.asList(11, 12)));
        listadoFunciones.add(new Funcion(7, LocalDateTime.of(2025, 6, 27, 14, 0), "14:00", 4, 7, Arrays.asList(13, 14)));
        listadoFunciones.add(new Funcion(8, LocalDateTime.of(2025, 6, 27, 19, 30), "19:30", 4, 8, Arrays.asList(15, 16)));
        listadoFunciones.add(new Funcion(9, LocalDateTime.of(2025, 6, 28, 16, 15), "16:15", 5, 9, Arrays.asList(17, 18)));
        listadoFunciones.add(new Funcion(10, LocalDateTime.of(2025, 6, 28, 21, 45), "21:45", 5, 10, Arrays.asList(19, 20)));
    }

    // ____________________________________METHODS____________________________________
    // [4.a. Registrar una nueva función por género]
    public int altaNuevaFuncion(FuncionDTO funcionDTO) {
        peliculasController = PeliculasController.getINSTANCE();
        try {
            Funcion nuevaFuncion = dtoToFuncion(funcionDTO);
            Pelicula peliculaFuncion = peliculasController.dtoToPelicula(peliculasController.buscarPeliculaPorID(funcionDTO.getPelicula().getID()));
            if (existeFuncion(nuevaFuncion)) return 0;
            // id = -2 no se puede crear la funcion para esa sala por el genero
            if (funcionDTO.getSala().getGenero() != peliculaFuncion.getGenero() && funcionDTO.getSala().getGenero() != null) return -2;

            int id = getFuncionID();
            nuevaFuncion.setID(id);
            listadoFunciones.add(nuevaFuncion);
            return id;
        } catch (Exception ex) {
            return -1;
        }
    }

    public FuncionDTO buscarFuncionPorID(int funcionID) {
        try {
            Funcion funcionBuscada = BusquedaBinaria.buscarPorId(listadoFunciones, funcionID);

            if (funcionBuscada == null) return null;

            return funcionToDTO(funcionBuscada);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorPelicula(int peliculaID) {
        peliculasController = PeliculasController.getINSTANCE();

        try {
            List<FuncionDTO> funcionesBuscadas = new ArrayList<>();
            PeliculaDTO peliculaBuscada = peliculasController.buscarPeliculaPorID(peliculaID);

            if (peliculaBuscada == null) return funcionesBuscadas;

            for (Funcion funcion : listadoFunciones) {
                if (funcion.getPeliculaID() == peliculaID) {
                    funcionesBuscadas.add(funcionToDTO(funcion));
                }
            }

            return funcionesBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorGenero(TipoGenero genero) {
        peliculasController = PeliculasController.getINSTANCE();
        try {
            List<PeliculaDTO> peliculasBuscadas = peliculasController.buscarPeliculasPorGenero(genero);
            List<FuncionDTO> funcionesBuscadas = new ArrayList<>();
            PeliculaDTO peliculaDTO;

            if (!peliculasBuscadas.isEmpty()) return funcionesBuscadas;

            for (Funcion funcion : listadoFunciones) {
                peliculaDTO = BusquedaBinaria.buscarPorId(peliculasBuscadas, funcion.getPeliculaID());
                if (peliculaDTO != null) funcionesBuscadas.add(funcionToDTO(funcion));
            }
            return funcionesBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDateTime fchFuncion) {
        List<FuncionDTO> funcionesBuscadas = new ArrayList<>();
        try {
            for (Funcion funcion : listadoFunciones) {
                if (funcion.getFecha().toLocalDate().isEqual(fchFuncion.toLocalDate()))
                    funcionesBuscadas.add(funcionToDTO(funcion));
            }

            return funcionesBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public int obtenerAsientosDisponiblePorFuncion(int funcionID) {
        ventasController = VentasController.getINSTANCE();
        try{
            FuncionDTO funcionDTO = buscarFuncionPorID(funcionID);
            if (funcionDTO == null) return 0;
            VentaDTO ventaBuscada = ventasController.buscarVentaPorFuncion(funcionID);
            List<EntradaDTO> entradasBuscadas = ventasController.buscarEntradasPorVenta(ventaBuscada.getID());

            return funcionDTO.getSala().getAsientos() - entradasBuscadas.size();
        }catch (Exception ex) {
            return 0;
        }
    }

    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    public FuncionDTO funcionToDTO(Funcion funcion) {
        peliculasController = PeliculasController.getINSTANCE();
        sucursalController = SucursalController.getINSTANCE();
        FuncionDTO funcionDTO = new FuncionDTO(funcion);
        funcionDTO.setPelicula(peliculasController.buscarPeliculaPorID(funcion.getPeliculaID()));
        funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));

        return funcionDTO;
    }

    // DTOs to OBJs
    public Funcion dtoToFuncion(FuncionDTO dto) {
        return new Funcion(dto.getID(), dto.getFecha(), dto.getHorario(), dto.getSala().getID(), dto.getPelicula().getID());

    }

}
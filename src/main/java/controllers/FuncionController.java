package controllers;

import dtos.EntradaDTO;
import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import dtos.SalaDTO;
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
    private final List<Funcion> listadoFunciones;
    private final SucursalController sucursalController;
    private final PeliculasController peliculasController;
    private final VentasController ventasController;

    private FuncionController() {
        listadoFunciones = new ArrayList<>();
        sucursalController = SucursalController.getINSTANCE();
        peliculasController = PeliculasController.getINSTANCE();
        ventasController = VentasController.getINSTANCE();
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
        Funcion funcionBuscada = BusquedaBinaria.buscarPorId(listadoFunciones, funcionID);
        List<EntradaDTO>  entradasBuscadas = new ArrayList<>();
        EntradaDTO entradaDTO;


        if (funcionBuscada == null) return null;

        try {
            FuncionDTO funcionDTO = funcionToDTO(funcionBuscada);
            SalaDTO sala = sucursalController.buscarSalaPorID(funcionBuscada.getSalaID());
            PeliculaDTO pelicula = peliculasController.buscarPeliculaPorID(funcionBuscada.getPeliculaID());
            for (Integer entradaID: funcionBuscada.getEntradasIDs()){
                entradaDTO = ventasController.buscarEntradaPorID(entradaID);
                entradasBuscadas.add(entradaDTO);
            }
            funcionDTO.setSala(sala);
            funcionDTO.setPelicula(pelicula);
            funcionDTO.setEntradas(entradasBuscadas);
            return funcionDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorPelicula(int peliculaID) {
        List<FuncionDTO> funcionesBuscadas = new ArrayList<>();
        PeliculaDTO peliculaBuscada = peliculasController.buscarPeliculaPorID(peliculaID);
        FuncionDTO funcionDTO;

        if (peliculaBuscada == null) return funcionesBuscadas;

        List<EntradaDTO> entradasBuscadas = new ArrayList<>();

        try {

            for (Funcion funcion : listadoFunciones) {
                if (funcion.getPeliculaID() == peliculaID) {
                    funcionDTO = funcionToDTO(funcion);
                    funcionDTO.setPelicula(peliculaBuscada);
                    funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));
                    for (Integer entradaID: funcion.getEntradasIDs()){
                        entradasBuscadas.add(ventasController.buscarEntradaPorID(entradaID));
                    }
                    funcionDTO.setEntradas(entradasBuscadas);

                    funcionesBuscadas.add(funcionDTO);
                }

                entradasBuscadas.clear();
            }
            return funcionesBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorGenero(TipoGenero genero) {
        List<PeliculaDTO> peliculasBuscadas = peliculasController.buscarPeliculasPorGenero(genero);
        List<FuncionDTO> funcionesBuscadas = new ArrayList<>();
        PeliculaDTO peliculaDTO;
        FuncionDTO funcionDTO;
        List<EntradaDTO> entradasBuscadas = new ArrayList<>();

        try {
            if (!peliculasBuscadas.isEmpty())return funcionesBuscadas;

            for (Funcion funcion : listadoFunciones) {
                peliculaDTO = BusquedaBinaria.buscarPorId(peliculasBuscadas, funcion.getPeliculaID());
                if (peliculaDTO != null) {
                    funcionDTO = funcionToDTO(funcion);
                    funcionDTO.setPelicula(peliculaDTO);
                    funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));
                    for (Integer entradaID: funcion.getEntradasIDs()){
                        entradasBuscadas.add(ventasController.buscarEntradaPorID(entradaID));
                    }
                    funcionDTO.setEntradas(entradasBuscadas);

                    funcionesBuscadas.add(funcionDTO);
                }

                entradasBuscadas.clear();
            }
            return funcionesBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDateTime fchFuncion) {
        List<FuncionDTO> funcionesBuscadas = new ArrayList<>();
        FuncionDTO funcionDTO;
        List<EntradaDTO> entradasBuscadas = new ArrayList<>();

        for (Funcion funcion : listadoFunciones) {
            if (funcion.getFecha().toLocalDate().isEqual(fchFuncion.toLocalDate())) {
                funcionDTO = funcionToDTO(funcion);
                funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));
                funcionDTO.setPelicula(peliculasController.buscarPeliculaPorID(funcion.getPeliculaID()));
                for (Integer entradaID: funcion.getEntradasIDs()){
                    entradasBuscadas.add(ventasController.buscarEntradaPorID(entradaID));
                }

                funcionesBuscadas.add(funcionDTO);
            }

            entradasBuscadas.clear();
        }
        return funcionesBuscadas;
    }

    public int obtenerAsientosDisponiblePorFuncion(int funcionID) {
        FuncionDTO funcionDTO = buscarFuncionPorID(funcionID);
        if (funcionDTO == null) return 0;
        return funcionDTO.getSala().getAsientos() - funcionDTO.getEntradas().size();
    }


    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    public FuncionDTO funcionToDTO(Funcion funcion) {
        return new FuncionDTO(funcion);
    }

    // DTOs to OBJs
    public Funcion dtoToFuncion(FuncionDTO dto) {
        List<Integer> entradasIDs = new ArrayList<>();
        for (EntradaDTO entradaDTO : dto.getEntradas()){
            entradasIDs.add(entradaDTO.getID());
        }
        Funcion funcion = new Funcion(dto.getID(),dto.getFecha(),dto.getHorario(),dto.getSala().getID(),dto.getPelicula().getID());
        funcion.setEntradasIDs(entradasIDs);
        return funcion;

    }

}
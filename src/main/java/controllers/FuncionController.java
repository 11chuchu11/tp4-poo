package controllers;

import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import dtos.SalaDTO;
import models.Funcion;
import models.Pelicula;
import models.Sala;
import types.TipoGenero;
import utils.BusquedaBinaria;

import java.time.LocalDateTime;
import java.util.*;

public class FuncionController {

    private static FuncionController INSTANCE;
    private final List<Funcion> listadoFunciones;
    private final SucursalController sucursalController;
    private final PeliculasController peliculasController;

    private FuncionController() {
        listadoFunciones = new ArrayList<Funcion>();
        sucursalController = SucursalController.getINSTANCE();
        peliculasController = PeliculasController.getINSTANCE();
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

    // ____________________________________METHODS____________________________________
    // [4.a. Registrar una nueva función por género]
    public int altaNuevaFuncion(FuncionDTO funcionDTO) {
        try {
            Funcion nuevaFuncion = dtoToFuncion(funcionDTO);
            Sala salaFuncion = sucursalController.dtoToSala(sucursalController.buscarSalaPorID(funcionDTO.getSala().getID()));
            Pelicula peliculaFuncion = peliculasController.dtoToPelicula(peliculasController.buscarPeliculaPorID(funcionDTO.getPelicula().getID()));
            if (existeFuncion(nuevaFuncion)) return 0;
            // id = -2 no se puede crear la funcion para esa sala por el genero
            if (salaFuncion.getGenero() != peliculaFuncion.getGenero() && salaFuncion.getGenero() != null) return -2;
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

        if (funcionBuscada == null) return null;

        try {
            FuncionDTO funcionDTO = simpleFuncionToDTO(funcionBuscada);
            SalaDTO sala = sucursalController.buscarSalaPorID(funcionBuscada.getSalaID());
            PeliculaDTO pelicula = peliculasController.buscarPeliculaPorID(funcionBuscada.getPeliculaID());
            funcionDTO.setSala(sala);
            funcionDTO.setPelicula(pelicula);
            return funcionDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorPelicula(int peliculaID) {
        List<FuncionDTO> funcionesDeLaPelicula = new ArrayList<>();
        PeliculaDTO pelicula = peliculasController.buscarPeliculaPorID(peliculaID);
        FuncionDTO funcionDTO;

        if (pelicula == null) return funcionesDeLaPelicula;

        try {

            for (Funcion funcion : listadoFunciones) {
                if (funcion.getPeliculaID() == peliculaID) {
                    funcionDTO = simpleFuncionToDTO(funcion);
                    funcionDTO.setPelicula(pelicula);
                    funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));
                    funcionesDeLaPelicula.add(funcionDTO);
                }
            }
            return funcionesDeLaPelicula;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FuncionDTO> buscarFuncionesPorGenero(TipoGenero genero) {
        List<PeliculaDTO> peliculasPorGenero = peliculasController.buscarPeliculasPorGenero(genero);
        List<FuncionDTO> funcionesPorGenero = new ArrayList<>();
        PeliculaDTO peliculaDTO;
        FuncionDTO funcionDTO;

        try {
            for (Funcion funcion : listadoFunciones) {
                peliculaDTO = BusquedaBinaria.buscarPorId(peliculasPorGenero, funcion.getPeliculaID());
                if (peliculaDTO == null) {
                    funcionDTO = simpleFuncionToDTO(funcion);
                    funcionDTO.setPelicula(peliculaDTO);
                    funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));
                    funcionesPorGenero.add(funcionDTO);
                }

            }
            return funcionesPorGenero;
        } catch (Exception ex) {
            return null;
        }
    }

    public int obtenerAsientosDisponiblePorFuncion(int funcionID) {
        FuncionDTO funcionDTO = buscarFuncionPorID(funcionID);
        if (funcionDTO == null) return 0;
        return funcionDTO.getSala().getAsientos()-funcionDTO.getEntradas().size();
    }

    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDateTime fchFuncion){
        List<FuncionDTO> funcionesPorFecha = new ArrayList<>();
        FuncionDTO funcionDTO;
        for (Funcion funcion : listadoFunciones) {
            if (funcion.getFecha().toLocalDate().isEqual(fchFuncion.toLocalDate())) {
                funcionDTO = simpleFuncionToDTO(funcion);
                funcionDTO.setSala(sucursalController.buscarSalaPorID(funcion.getSalaID()));
                funcionDTO.setPelicula(peliculasController.buscarPeliculaPorID(funcion.getPeliculaID()));

                funcionesPorFecha.add(funcionDTO);
            }
        }
        return funcionesPorFecha;
    }



    public int diaDeLaSemanaConMenorVentas() {
        // TODO implement here
        return 0;
    }

    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    public FuncionDTO simpleFuncionToDTO(Funcion funcion) {
        return new FuncionDTO(funcion);
    }


    // DTOs to OBJs
    public Funcion dtoToFuncion(FuncionDTO dto) {
        return new Funcion(dto.getFecha(), dto.getHorario(), dto.getEntradas(), dto.getSala().getID(), dto.getPelicula().getID());
    }

}
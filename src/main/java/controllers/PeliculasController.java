package controllers;

import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import models.Pelicula;
import types.TipoGenero;
import utils.BusquedaBinaria;

import java.util.*;

public class PeliculasController {

    private static PeliculasController INSTANCE;
    private final List<Pelicula> listadoPeliculas;
    private final FuncionController funcionController;

    public PeliculasController() {
        this.funcionController = FuncionController.getINSTANCE();
        this.listadoPeliculas = new ArrayList<Pelicula>();
    }

    public static PeliculasController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new PeliculasController();
        return INSTANCE;
    }

    // ____________________________________HELPERS____________________________________
    private int getPeliculaID() {
        int listSize = listadoPeliculas.size();
        if (listSize == 0) return 0;
        return listadoPeliculas.get(listSize - 1).getID() + 1;
    }

    private boolean existePelicula(Pelicula pelicula) {
        for (Pelicula p : listadoPeliculas) {
            if (p.equals(pelicula)) return true;
        }
        return false;
    }

    // ____________________________________METHODS____________________________________
    // [4.b. Registrar una pelicula por genero (el registro por genero debería darse en la view)]
    public int altaNuevaPelicula(PeliculaDTO dto) {
        Pelicula nuevaPelicula = dtoToPelicula(dto);
        if (existePelicula(nuevaPelicula)) return 0;
        int id = getPeliculaID();
        nuevaPelicula.setID(id);
        listadoPeliculas.add(nuevaPelicula);
        return id;
    }

    public PeliculaDTO buscarPeliculaPorID(int peliculaID) {
        Pelicula peliculaBucada = BusquedaBinaria.buscarPorId(listadoPeliculas, peliculaID);
        if (peliculaBucada == null) return null;
        return peliculaToDTO(peliculaBucada);
    }

    // [4.c. Consultar peliculas por genero]
    public List<PeliculaDTO> buscarPeliculasPorGenero(TipoGenero genero) {
        List<PeliculaDTO> peliculas = new ArrayList<>();
        for (Pelicula p : listadoPeliculas) {
            if (p.getGenero() == genero) peliculas.add(peliculaToDTO(p));
        }
        return peliculas;
    }

    public PeliculaDTO buscarPeliculaMasVista() {
        try {
            PriorityQueue<Pelicula> peliculasMasVista = new PriorityQueue<>(Comparator.comparingInt(pel -> {
                int counter = 0;
                List<FuncionDTO> funcionesPorPeliculas = funcionController.buscarFuncionesPorPelicula(pel.getID());
                for (FuncionDTO funcion : funcionesPorPeliculas) {
                    counter += funcion.getEntradas().size();
                }
                return counter;
            }));
            peliculasMasVista.addAll(listadoPeliculas);


            return peliculaToDTO(peliculasMasVista.poll());
        } catch (Exception ex) {
            return null;
        }
    }

    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    public PeliculaDTO peliculaToDTO(Pelicula pelicula) {
        return new PeliculaDTO(pelicula);
    }

    // DTOs to OBJs
    public Pelicula dtoToPelicula(PeliculaDTO dto) {
        return new Pelicula(dto.getGenero(), dto.getDirector(), dto.getDuracionEnMinutos(), dto.getNombrePelicula(), dto.getTipo(), dto.getActores(), dto.getCondicionesDescuento());
    }

}
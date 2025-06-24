package controllers;

import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import models.Pelicula;
import types.TipoGenero;
import utils.BusquedaBinaria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PeliculasController {

    private static PeliculasController INSTANCE;
    private final DescuentoController descuentoController;
    private final FuncionController funcionController;
    private final List<Pelicula> listadoPeliculas;

    public PeliculasController() {
        this.funcionController = FuncionController.getINSTANCE();
        this.descuentoController = DescuentoController.getINSTANCE();
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
        try {
            Pelicula nuevaPelicula = dtoToPelicula(dto);
            if (existePelicula(nuevaPelicula)) return 0;
            int id = getPeliculaID();
            nuevaPelicula.setID(id);
            listadoPeliculas.add(nuevaPelicula);
            return id;
        } catch (Exception ex) {
            return -1;
        }
    }

    public PeliculaDTO buscarPeliculaPorID(int peliculaID) {
        try {
            Pelicula peliculaBucada = BusquedaBinaria.buscarPorId(listadoPeliculas, peliculaID);
            if (peliculaBucada == null) return null;
            PeliculaDTO peliculaDTO = peliculaToDTO(peliculaBucada);
            peliculaDTO.setCondicionesDescuento(descuentoController.buscarDescuentoPorId(peliculaBucada.getCondicionesDescuentoID()));
            return peliculaDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    // [4.c. Consultar peliculas por genero]
    public List<PeliculaDTO> buscarPeliculasPorGenero(TipoGenero genero) {
        try {
            List<PeliculaDTO> peliculasBuscadas = new ArrayList<>();
            PeliculaDTO peliculaDTO;
            for (Pelicula p : listadoPeliculas) {
                if (p.getGenero() == genero) {
                    peliculaDTO = peliculaToDTO(p);
                    peliculaDTO.setCondicionesDescuento(descuentoController.buscarDescuentoPorId(p.getCondicionesDescuentoID()));
                    peliculasBuscadas.add(peliculaDTO);
                }
            }
            return peliculasBuscadas;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<PeliculaDTO> buscarPeliculasPorDescuentoID(int descuentoID) {
        try {
            List<PeliculaDTO> peliculasBuscadas = new ArrayList<>();
            PeliculaDTO peliculaDTO;

            for (Pelicula p : listadoPeliculas) {
                if (p.getCondicionesDescuentoID() == descuentoID) {
                    peliculaDTO = peliculaToDTO(p);
                    peliculaDTO.setCondicionesDescuento(descuentoController.buscarDescuentoPorId(p.getCondicionesDescuentoID()));
                    peliculasBuscadas.add(peliculaDTO);
                }
            }
            return peliculasBuscadas;
        } catch (Exception ex) {
            return null;
        }
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
            Pelicula peliculaMasVista = peliculasMasVista.poll();
            PeliculaDTO peliculaDTO = peliculaToDTO(peliculaMasVista);
            peliculaDTO.setCondicionesDescuento(descuentoController.buscarDescuentoPorId(peliculaMasVista.getCondicionesDescuentoID()));
            return peliculaDTO;
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
        return new Pelicula(dto.getGenero(), dto.getDirector(), dto.getDuracionEnMinutos(), dto.getNombrePelicula(), dto.getTipo(), dto.getActores(), dto.getCondicionesDescuento().getID());
    }

}
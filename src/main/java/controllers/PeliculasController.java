package controllers;

import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import models.Pelicula;
import types.TipoGenero;
import types.TipoProyeccion;
import utils.BusquedaBinaria;

import java.util.*;

public class PeliculasController {

    private static PeliculasController INSTANCE;
    private final DescuentoController descuentoController;
    private final FuncionController funcionController;
    private final List<Pelicula> listadoPeliculas;

    public PeliculasController() {
        this.funcionController = FuncionController.getINSTANCE();
        this.descuentoController = DescuentoController.getINSTANCE();
        this.listadoPeliculas = new ArrayList<>();
        precargaPelicula();
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

    private void precargaPelicula(){
        listadoPeliculas.add(new Pelicula(1, TipoGenero.Terror, "James Wan", 112, "El Conjuro", TipoProyeccion.TresD, Arrays.asList("Vera Farmiga", "Patrick Wilson"), 1));
        listadoPeliculas.add(new Pelicula(2, TipoGenero.Romance, "Greta Gerwig", 134, "Mujercitas", TipoProyeccion.DosD, Arrays.asList("Saoirse Ronan", "Timothée Chalamet"), 2));
        listadoPeliculas.add(new Pelicula(3, TipoGenero.Drama, "Christopher Nolan", 150, "Oppenheimer", TipoProyeccion.CuatroD, Arrays.asList("Cillian Murphy", "Emily Blunt"), 3));
        listadoPeliculas.add(new Pelicula(4, TipoGenero.Suspenso, "David Fincher", 138, "Perdida", TipoProyeccion.TresDMax, Arrays.asList("Ben Affleck", "Rosamund Pike"), 3));
        listadoPeliculas.add(new Pelicula(5, TipoGenero.Biografica, "Ron Howard", 140, "Una mente brillante", TipoProyeccion.DosD, Arrays.asList("Russell Crowe", "Jennifer Connelly"), 5));
        listadoPeliculas.add(new Pelicula(6, TipoGenero.Terror, "Jordan Peele", 100, "¡Huye!", TipoProyeccion.TresD, Arrays.asList("Daniel Kaluuya", "Allison Williams"), 2));
        listadoPeliculas.add(new Pelicula(7, TipoGenero.Romance, "Richard Linklater", 101, "Antes del amanecer", TipoProyeccion.DosD, Arrays.asList("Ethan Hawke", "Julie Delpy"), 3));
        listadoPeliculas.add(new Pelicula(8, TipoGenero.Drama, "Martin Scorsese", 210, "El irlandés", TipoProyeccion.TresDMax, Arrays.asList("Robert De Niro", "Al Pacino"), 2));
        listadoPeliculas.add(new Pelicula(9, TipoGenero.Biografica, "Pablo Larraín", 111, "Jackie", TipoProyeccion.DosD, Arrays.asList("Natalie Portman", "Peter Sarsgaard"), 1));
        listadoPeliculas.add(new Pelicula(10, TipoGenero.Suspenso, "Alfred Hitchcock", 109, "Psicosis", TipoProyeccion.CuatroD, Arrays.asList("Anthony Perkins", "Janet Leigh"), 3));

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
            PriorityQueue<Pelicula> peliculasMasVistas = new PriorityQueue<>(Comparator.comparingInt(pel -> {
                int counter = 0;
                List<FuncionDTO> funcionesPorPeliculas = funcionController.buscarFuncionesPorPelicula(pel.getID());
                for (FuncionDTO funcion : funcionesPorPeliculas) {
                    counter += funcion.getEntradas().size();
                }
                return counter;
            }));
            peliculasMasVistas.addAll(listadoPeliculas);
            Pelicula peliculaMasVista = peliculasMasVistas.poll();
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
package dtos;

import models.CondicionesDescuento;
import models.Data;
import models.Pelicula;
import types.TipoGenero;
import types.TipoProyeccion;

import java.util.List;

public class PeliculaDTO extends Data {

    private TipoGenero genero;
    private String nombrePelicula;
    private int duracionEnMinutos;
    private String director;
    private List<String> actores;
    private TipoProyeccion tipo;
    private CondicionesDescuento condicionesDescuento;

    public PeliculaDTO(Pelicula pelicula) {
        super(pelicula.getID());
        this.genero = pelicula.getGenero();
        this.nombrePelicula = pelicula.getNombrePelicula();
        this.duracionEnMinutos = pelicula.getDuracionEnMinutos();
        this.director = pelicula.getDirector();
        this.actores = pelicula.getActores();
        this.tipo = pelicula.getTipo();
        this.condicionesDescuento = pelicula.getCondicionesDescuento();
    }

    public PeliculaDTO(int id, TipoGenero genero, String nombrePelicula, int duracionEnMinutos, String director, List<String> actores, TipoProyeccion tipo, CondicionesDescuento condicionesDescuento) {
        super(id);
        this.genero = genero;
        this.nombrePelicula = nombrePelicula;
        this.duracionEnMinutos = duracionEnMinutos;
        this.director = director;
        this.actores = actores;
        this.tipo = tipo;
        this.condicionesDescuento = condicionesDescuento;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public CondicionesDescuento getCondicionesDescuento() {
        return condicionesDescuento;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public TipoProyeccion getTipo() {
        return tipo;
    }

    public List<String> getActores() {
        return actores;
    }

    public String getDirector() {
        return director;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }
}



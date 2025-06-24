package dtos;

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
    private CondicionesDescuentoDTO condicionesDescuento;

    public PeliculaDTO(Pelicula pelicula) {
        super(pelicula.getID());
        this.genero = pelicula.getGenero();
        this.nombrePelicula = pelicula.getNombrePelicula();
        this.duracionEnMinutos = pelicula.getDuracionEnMinutos();
        this.director = pelicula.getDirector();
        this.actores = pelicula.getActores();
        this.tipo = pelicula.getTipo();
        this.condicionesDescuento = null;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public CondicionesDescuentoDTO getCondicionesDescuento() {
        return condicionesDescuento;
    }

    public void setCondicionesDescuento(CondicionesDescuentoDTO condicionesDescuento) {
        this.condicionesDescuento = condicionesDescuento;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public TipoProyeccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoProyeccion tipo) {
        this.tipo = tipo;
    }

    public List<String> getActores() {
        return actores;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }
}



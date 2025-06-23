package models;

import types.TipoGenero;
import types.TipoProyeccion;

import java.util.*;

public class Pelicula extends Data {

    private TipoGenero genero;
    private String nombrePelicula;
    private int duracionEnMinutos;
    private String director;
    private List<String> actores;
    private TipoProyeccion tipo;
    private CondicionesDescuento condicionesDescuento;

    public Pelicula( TipoGenero genero, String director, int duracionEnMinutos, String nombrePelicula, TipoProyeccion tipo, List<String> actores, CondicionesDescuento descuento) {
        super(0);
        this.actores = actores;
        this.director = director;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;
        this.nombrePelicula = nombrePelicula;
        this.tipo = tipo;
        this.condicionesDescuento = descuento;
    }

    public void setID(int id) {
        this.id = id;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public CondicionesDescuento getCondicionesDescuento() {
        return condicionesDescuento;
    }

    public int getID() {
        return this.id;
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
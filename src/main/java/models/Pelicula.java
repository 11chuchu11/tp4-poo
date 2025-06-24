package models;

import types.TipoGenero;
import types.TipoProyeccion;

import java.util.List;

public class Pelicula extends Data {

    private TipoGenero genero;
    private final String nombrePelicula;
    private final int duracionEnMinutos;
    private final String director;
    private final List<String> actores;
    private final TipoProyeccion tipo;
    private final int condicionesDescuentoID;

    public Pelicula(TipoGenero genero, String director, int duracionEnMinutos, String nombrePelicula, TipoProyeccion tipo, List<String> actores, int condicionesDescuentoID) {
        super(0);
        this.actores = actores;
        this.director = director;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;
        this.nombrePelicula = nombrePelicula;
        this.tipo = tipo;
        this.condicionesDescuentoID = condicionesDescuentoID;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public int getCondicionesDescuentoID() {
        return condicionesDescuentoID;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
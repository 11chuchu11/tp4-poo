package models;

import types.TipoGenero;
import types.TipoProyeccion;

import java.util.*;

/**
 * 
 */
public class Pelicula {

    /**
     * Default constructor
     */
    public Pelicula(TipoGenero generoID, String director, int duracionEnMinutos, String nombrePelicula, TipoProyeccion tipo, List<String> actores, CondicionesDescuento descuento) {
    
    this.actores = actores;
    this.director = director;
    this.duracionEnMinutos = duracionEnMinutos;
    this.generoID = generoID;
    this.nombrePelicula = nombrePelicula;
    this.tipo = tipo;
    this.condicionesDescuento = descuento;
    
    
    
    }

    public TipoGenero getGeneroID() {
        return generoID;
    }

    public void setGeneroID(TipoGenero generoID) {
        this.generoID = generoID;
    }

    /**
     * 
     */
    private TipoGenero generoID;

    /**
     * 
     */
    private String nombrePelicula;

    /**
     * 
     */
    private int duracionEnMinutos;

    /**
     * 
     */
    private String director;

    /**
     * 
     */
    private List<String> actores;

    /**
     * 
     */
    private TipoProyeccion tipo;

    public CondicionesDescuento getCondicionesDescuento() {
        return condicionesDescuento;
    }

    private CondicionesDescuento condicionesDescuento;


    /**
     * 
     */
    public int getPeliculaID() {
        // TODO implement here
        return 0;
    }

}
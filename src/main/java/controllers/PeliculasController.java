package controllers;

import models.Pelicula;
import types.TipoGenero;
import types.TipoProyeccion;

import java.util.*;


/**
 * 
 */
    	
public class PeliculasController {

    /**
     * Default constructor
     */
	
	private List<Pelicula> peliculas;
	
    public PeliculasController() {
    	
    	peliculas= new ArrayList<Pelicula>();
    	peliculas.add(new Pelicula(TipoGenero.Suspenso, "Pelicula1", 180 , "Director X", TipoProyeccion.DosD, Arrays.asList("Actriz Principal", "Actor Secundario"),null));

    }

    /**
     * 
     */
    public void ABM() {
        // TODO implement here
    }

}
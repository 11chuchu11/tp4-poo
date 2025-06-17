package models;

/**
 * 
 */
public class Entrada {

    /**
     * Default constructor
     */
    public Entrada(int nroAsiento, Funcion funcion, float precio) {
    	this.funcion = funcion;
    	this.nroAsiento = nroAsiento;
    	this.precio = precio;
    }
    
    public Entrada() {

    }

    public float getPrecio() {
        return precio;
    }

    private float precio;

    /**
     * 
     */
    private int nroAsiento;

    /**
     * 
     */
    public Funcion funcion;


    public int getFuncionID() {
        // TODO implement here
        return 0;
    }

    /**
     * 
     */
    public void getPeliculaID() {
        // TODO implement here
    }

}
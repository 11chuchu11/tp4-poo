package models;

import java.util.*;

/**
 * 
 */
public class Sucursal {

    /**
     * Default constructor
     */
    public Sucursal(int sucursalID, String denominacion, String direccion, ArrayList<Sala> sala) {
    	this.denominacion = denominacion;
    	this.direccion = direccion;
    	this.sucursalID = sucursalID;
    	this.salas = sala;
    }

    /**
     * 
     */
    private int sucursalID;

    /**
     * 
     */
    private String denominacion;

    /**
     * 
     */
    private String direccion;
    
    private List<Sala> salas;



    /**
     * @return
     */
    public int getSucursalID() {
        // TODO implement here
        return 0;
    }

}
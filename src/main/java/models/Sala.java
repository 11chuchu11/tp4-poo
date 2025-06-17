package models;

/**
 * 
 */
public class Sala {

    /**
     * Default constructor
     */
    public Sala(int salaID, String denominacion, int asientos) {
    	this.asientos = asientos;
    	this.denominacion = denominacion;
    	this.salaID = salaID;
    }

    /**
     * 
     */
    private int salaID;

    /**
     * 
     */
    private String denominacion;

    /**
     * 
     */
    private int asientos;



    /**
     * @return
     */
    public int getSucursalID() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getSalaID() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getAsientos() {
        return this.asientos;
    }

}
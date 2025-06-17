package models;

import types.TipoTarjeta;

/**
 * 
 */
public class TarjetaDescuento {

    /**
     * Default constructor
     */
    public TarjetaDescuento(int tarjetaID, TipoTarjeta tipoTarjeta, String numeroTarjeta) {
    	this.numeroTarjeta = numeroTarjeta;
    	this.tarjetaID = tarjetaID;
    	this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * 
     */
    private int tarjetaID;

    /**
     * 
     */
    private TipoTarjeta tipoTarjeta;

    /**
     * 
     */
    private String numeroTarjeta;



    /**
     * @return
     */
    public TipoTarjeta getTipoTarjeta() {
        // TODO implement here
        return null;
    }


}
package models;

import types.TipoTarjeta;

public class TarjetaDescuento {

    private int tarjetaID;
    private TipoTarjeta tipoTarjeta;
    private String numeroTarjeta;

    public TarjetaDescuento(int tarjetaID, TipoTarjeta tipoTarjeta, String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        this.tarjetaID = tarjetaID;
        this.tipoTarjeta = tipoTarjeta;
    }

    public TipoTarjeta getTipoTarjeta() {
        return this.tipoTarjeta;
    }
}
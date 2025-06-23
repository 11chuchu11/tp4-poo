package dtos;

import models.Data;
import models.Sala;
import types.TipoGenero;

public class SalaDTO extends Data {
    private SucursalDTO sucursal;
    private int asientos;
    private String denominacion;
    private TipoGenero genero;

    public SalaDTO(Sala sala) {
        super(sala.getID());
        this.asientos = sala.getAsientos();
        this.denominacion = sala.getDenominacion();
        this.genero = sala.getGenero();
        this.sucursal = null;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setSucursal(SucursalDTO sucursal) {}

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public int getID() {
        return id;
    }

    public int getAsientos() {
        return asientos;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public TipoGenero getGenero() {
        return genero;
    }
}

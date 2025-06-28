package dtos;

import models.Data;
import models.Sala;
import types.TipoGenero;

public class SalaDTO extends Data {
    private SucursalDTO sucursal;
    private int asientos;
    private String denominacion;
    private TipoGenero genero;

    public SalaDTO( int asientos, String denominacion, TipoGenero genero) {
        super(0);
        this.asientos = asientos;
        this.denominacion = denominacion;
        this.genero = genero;
        this.sucursal = null;
    }

    public SalaDTO(Sala sala) {
        super(sala.getID());
        this.asientos = sala.getAsientos();
        this.denominacion = sala.getDenominacion();
        this.genero = sala.getGenero();
        this.sucursal = null;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return denominacion + " (" + asientos + " asientos)";
    }
}

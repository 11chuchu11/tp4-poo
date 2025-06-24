package dtos;

import models.Data;
import models.Sala;
import types.TipoGenero;

public class SalaDTO extends Data {
    private final SucursalDTO sucursal;
    private final int asientos;
    private final String denominacion;
    private final TipoGenero genero;

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
}

package dtos;

import models.Data;
import models.Sucursal;

import java.util.List;

public class SucursalDTO extends Data {
    private final String denominacion;
    private final String direccion;
    private List<SalaDTO> salas;

    public SucursalDTO(Sucursal sucursal) {
        super(sucursal.getID());
        this.denominacion = sucursal.getDenominacion();
        this.direccion = sucursal.getDireccion();
        this.salas = null;
    }

    public void setSalas(List<SalaDTO> salas) {
        this.salas = salas;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getSucursalID() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDenominacion() {
        return denominacion;
    }

}

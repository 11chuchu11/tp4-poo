package dtos;

import models.Data;
import models.Sucursal;

import java.util.List;

public class SucursalDTO extends Data {
    private  String denominacion;

    public SucursalDTO( String denominacion, String direccion) {
        super(0);
        this.denominacion = denominacion;
        this.direccion = direccion;
    }

    private  String direccion;
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

    // agregué este metodo para que al imprimir el objeto SucursalDTO se muestre su denominación
    // y no salfa la posición de memoria - Emilio
    @Override
    public String toString() {
        return this.getDenominacion();
    }
}

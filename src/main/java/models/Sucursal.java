package models;

import java.util.*;

public class Sucursal extends Data {

    private String denominacion;
    private String direccion;


    public Sucursal( String denominacion, String direccion) {
        super(0);
        this.denominacion = denominacion;
        this.direccion = direccion;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return Objects.equals(denominacion, sucursal.denominacion) && Objects.equals(direccion, sucursal.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denominacion, direccion);
    }
}
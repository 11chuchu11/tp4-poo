package models;

import java.util.Objects;

public class Entrada extends Data{

    private int funcionID, ventaID;
    private float precio;
    private int nroAsiento;

    public Entrada(int nroAsiento, int funcionID, float precio, int ventaID) {
        super(0);
        this.funcionID = funcionID;
        this.ventaID = ventaID;
        this.nroAsiento = nroAsiento;
        this.precio = precio;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public float getPrecio() {
        return precio;
    }

    public int getNroAsiento() {
        return nroAsiento;
    }

    public int getFuncionID() {
        return this.getFuncionID();
    }

    public void setFuncionID(int funcionID) {
        this.funcionID = funcionID;
    }

    public int getVentaID() {
        return ventaID;
    }

    public void setVentaID(int ventaID) {
        this.ventaID = ventaID;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setNroAsiento(int nroAsiento) {
        this.nroAsiento = nroAsiento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return funcionID == entrada.funcionID && ventaID == entrada.ventaID && Float.compare(precio, entrada.precio) == 0 && nroAsiento == entrada.nroAsiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcionID, ventaID, precio, nroAsiento);
    }
}
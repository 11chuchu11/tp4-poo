package dtos;

import models.Data;
import models.Entrada;

public class EntradaDTO extends Data {

    private FuncionDTO funcion;
    private VentaDTO venta;
    private float precio;
    private int nroAsiento;

    public EntradaDTO(Entrada entrada) {
        super(0);
        this.funcion = null;
        this.venta = null;
        this.precio = entrada.getPrecio();
        this.nroAsiento = entrada.getNroAsiento();
    }

    public VentaDTO getVenta() {
        return venta;
    }

    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }

    public FuncionDTO getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionDTO funcion) {
        this.funcion = funcion;
    }

    public int getNroAsiento() {
        return nroAsiento;
    }

    public void setNroAsiento(int nroAsiento) {
        this.nroAsiento = nroAsiento;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}

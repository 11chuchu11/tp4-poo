package dtos;

import models.Combo;
import models.CondicionesDescuento;
import models.Data;

public class ComboDTO extends Data {

    private CondicionesDescuento descuento;
    private String descripcion;
    private float precio;

    public ComboDTO(Combo combo) {
        super(combo.getID());
        this.descuento = combo.getDescuento();
        this.descripcion = combo.getDescripcion();
        this.precio = combo.getPrecio();
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public CondicionesDescuento getDescuento() {
        return descuento;
    }

    public void setDescuento(CondicionesDescuento descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

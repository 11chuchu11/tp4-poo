package dtos;

import models.Combo;
import models.Data;

public class ComboDTO extends Data {

    private CondicionesDescuentoDTO condicionesDescuento;
    private String descripcion;
    private float precio;

    public ComboDTO(Combo combo) {
        super(combo.getID());
        this.condicionesDescuento = null;
        this.descripcion = combo.getDescripcion();
        this.precio = combo.getPrecio();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public CondicionesDescuentoDTO getCondicionesDescuento() {
        return condicionesDescuento;
    }

    public void setCondicionesDescuento(CondicionesDescuentoDTO condicionesDescuento) {
        this.condicionesDescuento = condicionesDescuento;
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

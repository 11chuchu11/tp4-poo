package models;

public class Combo extends Data {

    private final int descuentoID;
    private final String descripcion;
    private final float precio;

    public Combo(String descripcion, float precio, int descuentoID) {
        super(0);
        this.descuentoID = descuentoID;
        this.descripcion = descripcion;
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

    public int getDescuentoID() {
        return descuentoID;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
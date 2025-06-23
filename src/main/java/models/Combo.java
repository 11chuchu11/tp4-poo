package models;

public class Combo extends Data{

    private CondicionesDescuento descuento;
    private String descripcion;
    private float precio;

    public Combo(String descripcion, float precio, CondicionesDescuento Contiene) {
        super(0);
        this.descuento = Contiene;
        this.descripcion = descripcion;
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

    public CondicionesDescuento getDescuento() {
        return descuento;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
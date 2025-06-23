package models;

import types.TipoGenero;

import java.util.Objects;

public class Sala extends Data{

    private int sucursalID, asientos;
    private String denominacion;
    // Indica si solo se puede mostra un unico genero en la sala
    private TipoGenero genero;

    public Sala(int sucursalID, int asientos, String denominacion, TipoGenero genero) {
        super(0);
        this.sucursalID = sucursalID;
        this.asientos = asientos;
        this.denominacion = denominacion;
        this.genero = genero;
    }

    public Sala(int id, int sucursalID, String denominacion, int asientos) {
        super(id);
        this.sucursalID = sucursalID;
        this.asientos = asientos;
        this.denominacion = denominacion;
        this.genero = null;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getSucursalID() {
        return this.sucursalID;
    }

    public int getID() {
        return this.id;
    }

    public int getAsientos() {
        return this.asientos;
    }

    public TipoGenero getGenero() {
        return this.genero;
    }

    public String getDenominacion() {
        return denominacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return sucursalID == sala.sucursalID && asientos == sala.asientos && Objects.equals(denominacion, sala.denominacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sucursalID, asientos, denominacion);
    }
}
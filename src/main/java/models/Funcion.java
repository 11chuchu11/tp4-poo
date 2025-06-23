package models;

import java.time.LocalDateTime;
import java.util.*;

public class Funcion extends Data {

    private int peliculaID;
    private String horario;
    private LocalDateTime fecha;
    private List<Entrada> entradas;
    private int salaID;

    public Funcion(LocalDateTime fecha, String horario, List<Entrada> entradas, int salaID, int peliculaID) {
        super(0);
        this.entradas = entradas;
        this.fecha = fecha;
        this.horario = horario;
        this.salaID = salaID;
        this.peliculaID = peliculaID;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }


    public int getPeliculaID() {
        return this.peliculaID;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }

    public int getSalaID() {
        return salaID;
    }


    // TODO: ver esto
    public float calcularMontoPorEntradaDeLaPelicula() {
        float total = 0.0f;
        for (Entrada entrada : getEntradas()) {
           // total = total + (entrada.getPrecio() - (entrada.getPrecio() * pelicula.getCondicionesDescuento().getDescuento()));
        }
        return total;
    }

}
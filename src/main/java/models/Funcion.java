package models;

import java.time.LocalDateTime;
import java.util.List;

public class Funcion extends Data {

    private final int peliculaID;
    private final String horario;
    private final LocalDateTime fecha;
    private final List<Entrada> entradas;
    private final int salaID;

    public Funcion(LocalDateTime fecha, String horario, List<Entrada> entradas, int salaID, int peliculaID) {
        super(0);
        this.entradas = entradas;
        this.fecha = fecha;
        this.horario = horario;
        this.salaID = salaID;
        this.peliculaID = peliculaID;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
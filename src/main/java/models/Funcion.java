package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion extends Data {

    private int peliculaID;
    private String horario;
    private LocalDateTime fecha;
    private int salaID;

    public Funcion(int id, LocalDateTime fecha, String horario, int salaID, int peliculaID) {
        super(id);
        this.fecha = fecha;
        this.horario = horario;
        this.salaID = salaID;
        this.peliculaID = peliculaID;
    }

    public Funcion(int id, LocalDateTime fecha, String horario, int salaID, int peliculaID, List<Integer> entradasIDs) {
        super(id);
        this.fecha = fecha;
        this.horario = horario;
        this.salaID = salaID;
        this.peliculaID = peliculaID;
    }

    public Funcion(LocalDateTime fecha, String horario,  int salaID, int peliculaID) {
        super(0);
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



}
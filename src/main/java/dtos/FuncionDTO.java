package dtos;

import models.Data;
import models.Entrada;
import models.Funcion;

import java.time.LocalDateTime;
import java.util.List;

public class FuncionDTO extends Data {

    private PeliculaDTO pelicula;
    private String horario;
    private LocalDateTime fecha;
    private List<EntradaDTO> entradas;
    private SalaDTO sala;


    public FuncionDTO( String horario, LocalDateTime fecha, List<EntradaDTO> entradas) {
        super(0);
        this.horario = horario;
        this.fecha = fecha;
        this.entradas = entradas;
        this.sala = null;
        this.pelicula = null;
    }

    public FuncionDTO(Funcion funcion) {
        super(funcion.getID());
        this.pelicula = null;
        this.horario = funcion.getHorario();
        this.fecha = funcion.getFecha();
        this.entradas = null;
        this.sala = null;
    }

    public int getID() {
        return id;
    }

    public void setID(int funcionID) {
        this.id = funcionID;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }

    public PeliculaDTO getPelicula() {
        return pelicula;
    }

    public void setPelicula(PeliculaDTO pelicula) {
        this.pelicula = pelicula;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<EntradaDTO> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaDTO> entradas) {
        this.entradas = entradas;
    }
}

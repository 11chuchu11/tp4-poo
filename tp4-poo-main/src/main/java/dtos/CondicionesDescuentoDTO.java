package dtos;

import models.CondicionesDescuento;
import models.Data;
import models.TarjetaDescuento;
import types.DiaDeLaSemana;
import types.TipoTarjeta;

import java.time.LocalDateTime;
import java.util.List;

public class CondicionesDescuentoDTO extends Data {

    private LocalDateTime fchDesde;
    private LocalDateTime fchHasta;
    private DiaDeLaSemana diaSemana;
    private float porcentaje;
    private TipoTarjeta tipoTarjeta;
    private List<TarjetaDescuento> tarjetasDescuentos;

    public CondicionesDescuentoDTO(CondicionesDescuento descuento) {
        super(descuento.getID());
        this.fchDesde = descuento.getFchDesde();
        this.fchHasta = descuento.getFchHasta();
        this.diaSemana = descuento.getDiaSemana();
        this.porcentaje = descuento.getPorcentaje();
        this.tipoTarjeta = descuento.getTipoTarjeta();
        this.tarjetasDescuentos = descuento.getTarjetasDescuentos();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public LocalDateTime getFchDesde() {
        return fchDesde;
    }

    public void setFchDesde(LocalDateTime fchDesde) {
        this.fchDesde = fchDesde;
    }

    public LocalDateTime getFchHasta() {
        return fchHasta;
    }

    public void setFchHasta(LocalDateTime fchHasta) {
        this.fchHasta = fchHasta;
    }

    public DiaDeLaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaDeLaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public List<TarjetaDescuento> getTarjetasDescuentos() {
        return tarjetasDescuentos;
    }

    public void setTarjetasDescuentos(List<TarjetaDescuento> tarjetasDescuentos) {
        this.tarjetasDescuentos = tarjetasDescuentos;
    }

    //puse este tostring para que al imprimir el DTO se vea mejor - Emilio
    @Override
    public String toString() {
        return "Descuento " + porcentaje + "% - " + (tipoTarjeta != null ? tipoTarjeta.name() : "Sin tarjeta") +
               " (" + (diaSemana != null ? diaSemana.name() : "Cualquier día") + ")";
    }
}

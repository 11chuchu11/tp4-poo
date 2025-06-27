package models;

import types.DiaDeLaSemana;
import types.TipoTarjeta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CondicionesDescuento extends Data {

    private LocalDateTime fchDesde;
    private LocalDateTime fchHasta;
    private DiaDeLaSemana diaSemana;
    private float porcentaje;
    private TipoTarjeta tipoTarjeta;
    private List<TarjetaDescuento> tarjetasDescuentos;

    public CondicionesDescuento(int id, LocalDateTime fchDesde, LocalDateTime fchHasta, DiaDeLaSemana diaSemana, float porcentaje, TipoTarjeta tipoTarjeta, ArrayList<TarjetaDescuento> tarjetasDescuentos) {
        super(id);
        this.diaSemana = diaSemana;
        this.fchDesde = fchDesde;
        this.fchHasta = fchHasta;
        this.porcentaje = porcentaje;
        this.tipoTarjeta = tipoTarjeta;
        this.tarjetasDescuentos = tarjetasDescuentos;
    }

    public CondicionesDescuento(LocalDateTime fchDesde, LocalDateTime fchHasta, DiaDeLaSemana diaSemana, float porcentaje, TipoTarjeta tipoTarjeta, ArrayList<TarjetaDescuento> tarjetasDescuentos) {
        super(0);
        this.diaSemana = diaSemana;
        this.fchDesde = fchDesde;
        this.fchHasta = fchHasta;
        this.porcentaje = porcentaje;
        this.tipoTarjeta = tipoTarjeta;
        this.tarjetasDescuentos = tarjetasDescuentos;
    }

    public CondicionesDescuento(LocalDateTime fchDesde, LocalDateTime fchHasta, DiaDeLaSemana diaSemana, float porcentaje, TipoTarjeta tipoTarjeta) {
        super(0);
        this.diaSemana = diaSemana;
        this.fchDesde = fchDesde;
        this.fchHasta = fchHasta;
        this.porcentaje = porcentaje;
        this.tipoTarjeta = tipoTarjeta;
        this.tarjetasDescuentos = new ArrayList<>();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public LocalDateTime getFchDesde() {
        return fchDesde;
    }

    public LocalDateTime getFchHasta() {
        return fchHasta;
    }

    public DiaDeLaSemana getDiaSemana() {
        return diaSemana;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public List<TarjetaDescuento> getTarjetasDescuentos() {
        return tarjetasDescuentos;
    }

    public void setFchDesde(LocalDateTime fchDesde) {
        this.fchDesde = fchDesde;
    }

    public void setFchHasta(LocalDateTime fchHasta) {
        this.fchHasta = fchHasta;
    }

    public void setDiaSemana(DiaDeLaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public void setTarjetasDescuentos(List<TarjetaDescuento> tarjetasDescuentos) {
        this.tarjetasDescuentos = tarjetasDescuentos;
    }
}
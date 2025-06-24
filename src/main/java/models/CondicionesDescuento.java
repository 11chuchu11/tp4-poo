package models;

import types.DiaDeLaSemana;
import types.TipoTarjeta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CondicionesDescuento extends Data {

    private final LocalDateTime fchDesde;
    private final LocalDateTime fchHasta;
    private final DiaDeLaSemana diaSemana;
    private final float porcentaje;
    private final TipoTarjeta tipoTarjeta;
    private final List<TarjetaDescuento> tarjetasDescuentos;

    public CondicionesDescuento(LocalDateTime fchDesde, LocalDateTime fchHasta, DiaDeLaSemana diaSemana, float porcentaje, TipoTarjeta tipoTarjeta, ArrayList<TarjetaDescuento> tarjetasDescuentos) {
        super(0);
        this.diaSemana = diaSemana;
        this.fchDesde = fchDesde;
        this.fchHasta = fchHasta;
        this.porcentaje = porcentaje;
        this.tipoTarjeta = tipoTarjeta;
        this.tarjetasDescuentos = tarjetasDescuentos;
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

}
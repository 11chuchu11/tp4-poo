package dtos;

import models.Data;
import models.TarjetaDescuento;
import models.Venta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaDTO extends Data {

    private LocalDateTime fchVenta;
    private List<ComboDTO> combos;
    private FuncionDTO funcion;
    private TarjetaDescuento tarjetaDescuento;

    public VentaDTO(Venta venta) {
        super(venta.getID());
        this.fchVenta = venta.getFchVenta();
        this.funcion = null;
        this.tarjetaDescuento = venta.getTarjetaDescuento();
        this.combos = new ArrayList<>();
    }


    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    public LocalDateTime getFchVenta() {
        return fchVenta;
    }

    public void setFchVenta(LocalDateTime fchVenta) {
        this.fchVenta = fchVenta;
    }

    public List<ComboDTO> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboDTO> combos) {
        this.combos = combos;
    }

    public FuncionDTO getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionDTO funcion) {
        this.funcion = funcion;
    }

    public TarjetaDescuento getTarjetaDescuento() {
        return tarjetaDescuento;
    }

    public void setTarjetaDescuento(TarjetaDescuento tarjetaDescuento) {
        this.tarjetaDescuento = tarjetaDescuento;
    }
}

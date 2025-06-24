package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta extends Data {

    private final LocalDateTime fchVenta;
    private final List<Integer> combosIDs;
    private final int funcionID;
    private final TarjetaDescuento tarjetaDescuento;

    public Venta(int funcionID) {
        super(0);
        this.combosIDs = new ArrayList<>();
        this.funcionID = funcionID;
        this.fchVenta = LocalDateTime.now();
        this.tarjetaDescuento = null;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public int getFuncionID() {
        return funcionID;
    }

    public List<Integer> getCombosIDs() {
        return combosIDs;
    }

    public LocalDateTime getFchVenta() {
        return fchVenta;
    }

    public TarjetaDescuento getTarjetaDescuento() {
        return tarjetaDescuento;
    }

}
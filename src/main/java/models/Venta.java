package models;

import java.time.LocalDateTime;
import java.util.*;

public class Venta extends Data{

    private LocalDateTime fchVenta;
    private List<Integer> combosIDs;
    private int funcionID;
    private TarjetaDescuento tarjetaDescuento;

    public Venta( int funcionID ) {
       super(0);
        this.combosIDs = new ArrayList<>();
        this.funcionID = funcionID;
        this.fchVenta = LocalDateTime.now();
        this.tarjetaDescuento = null;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
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
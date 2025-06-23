package controllers;

import dtos.SalaDTO;
import dtos.SucursalDTO;
import models.Sala;
import models.Sucursal;
import utils.BusquedaBinaria;

import java.util.*;

public class SucursalController {

    private static SucursalController INSTANCE;
    private final List<Sucursal> listadoSucursales;
    private List<Sala> listadoSalas;

    private SucursalController() {
        listadoSucursales = new ArrayList<Sucursal>();
        listadoSalas = new ArrayList<Sala>();
    }

    public static synchronized SucursalController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new SucursalController();
        return INSTANCE;
    }

    // ____________________________________HELPERS____________________________________
    private int getSucursalID() {
        int sucursalListSize = listadoSucursales.size();
        if (sucursalListSize == 0) return 1;
        return listadoSucursales.get(sucursalListSize-1).getID()+1;
    }

    private int getSalaID() {
        int salaListSize = listadoSalas.size();
        if (salaListSize == 0) return 1;
        return listadoSalas.get(salaListSize-1).getID()+1;
    }

    private boolean existeSucursal(Sucursal sucursal) {
        for (Sucursal suc : listadoSucursales) {
            if (suc.equals(sucursal)) return true;
        }
        return false;
    }

    private boolean existeSala(Sala sala) {
        for (Sala salaSala : listadoSalas) {
            if (salaSala.equals(sala)) return true;
        }
        return false;
    }

    // ____________________________________METHODS____________________________________
    public int altaNuevaSucursal(SucursalDTO dto) {
        Sucursal nuevaSucursal = dtoToSucursal(dto);
        if (existeSucursal(nuevaSucursal)) return 0;
        int id = getSucursalID();
        nuevaSucursal.setID(id);
        listadoSucursales.add(nuevaSucursal);
        return id;
    }

    public SucursalDTO buscarSucursalPorID(int sucursalID) {
        Sucursal sucursalBuscada = BusquedaBinaria.buscarPorId(listadoSucursales, sucursalID);
        if (sucursalBuscada == null) return null;
        return sucursalToDto(sucursalBuscada);
    }

    public int altaNuevaSala(SalaDTO dto) {
        Sala nuevaSala = dtoToSala(dto);
        if (existeSala(nuevaSala)) return 0;
        int id = getSalaID();
        nuevaSala.setID(id);
        listadoSalas.add(nuevaSala);
        return id;
    }

    public List<SalaDTO> buscarSalasPorSucursalID(int sucursalID){
        List<SalaDTO> salasPorSucursal = new ArrayList<>();
        for (Sala sala : listadoSalas) {
            if(sala.getSucursalID() == sucursalID) salasPorSucursal.add(salaToDto(sala));
        }
        return salasPorSucursal;
    }

    public SalaDTO buscarSalaPorID(int salaID) {
        Sala salaBuscada = BusquedaBinaria.buscarPorId(listadoSalas, salaID);
        if (salaBuscada == null) return null;
        return salaToDto(salaBuscada);
    }

    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    public  SucursalDTO sucursalToDto(Sucursal sucursal) {
        return new SucursalDTO(sucursal);
    }

    public static SalaDTO salaToDto(Sala sala) {
        return new SalaDTO(sala);
    }

    // DTOs to OBJs
    public Sucursal dtoToSucursal(SucursalDTO dto) {
        return new Sucursal(dto.getDenominacion(),dto.getDireccion());
    }

    public Sala dtoToSala(SalaDTO dto) {
        return new Sala(dto.getSucursal().getSucursalID(), dto.getAsientos(), dto.getDenominacion(),dto.getGenero());
    }







}
package controllers;

import dtos.SalaDTO;
import dtos.SucursalDTO;
import models.Sala;
import models.Sucursal;
import types.TipoGenero;
import utils.BusquedaBinaria;

import java.util.ArrayList;
import java.util.List;

public class SucursalController {

    private static SucursalController INSTANCE;
    private final List<Sucursal> listadoSucursales;
    private final List<Sala> listadoSalas;

    private SucursalController() {
        listadoSucursales = new ArrayList<>();
        listadoSalas = new ArrayList<>();
        precargarData();
    }

    public static synchronized SucursalController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new SucursalController();
        return INSTANCE;
    }

    // ____________________________________HELPERS____________________________________
    private int getSucursalID() {
        int sucursalListSize = listadoSucursales.size();
        if (sucursalListSize == 0) return 1;
        return listadoSucursales.get(sucursalListSize - 1).getID() + 1;
    }

    private int getSalaID() {
        int salaListSize = listadoSalas.size();
        if (salaListSize == 0) return 1;
        return listadoSalas.get(salaListSize - 1).getID() + 1;
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

    private void precargarData() {
        for (int i = 1; i <= 10; i++) {
            listadoSucursales.add(new Sucursal(i, "Sucursal " + i, "Calle Falsa " + (100 + i)));
        }

        TipoGenero[] generos = TipoGenero.values();

        int salaIdCounter = 1;
        for (Sucursal sucursal : listadoSucursales) {
            for (int i = 1; i <= 5; i++) {
                TipoGenero genero = generos[(salaIdCounter - 1) % generos.length];
                listadoSalas.add(new Sala(
                        salaIdCounter++,
                        sucursal.getID(),
                        50 + (i * 10),
                        "Sala " + i + " - " + sucursal.getDenominacion(),
                        genero
                ));
            }
        }
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

    public int altaNuevaSala(SalaDTO dto) {
        Sala nuevaSala = dtoToSala(dto);
        if (existeSala(nuevaSala)) return 0;
        int id = getSalaID();
        nuevaSala.setID(id);
        listadoSalas.add(nuevaSala);
        return id;
    }

    public SucursalDTO buscarSucursalPorID(int sucursalID) {
        Sucursal sucursalBuscada = BusquedaBinaria.buscarPorId(listadoSucursales, sucursalID);
        if (sucursalBuscada == null) return null;
        return sucursalToDto(sucursalBuscada);
    }

    public List<SalaDTO> buscarSalasPorSucursalID(int sucursalID) {
        List<SalaDTO> salasBuscadas = new ArrayList<>();
        for (Sala sala : listadoSalas) {
            if (sala.getSucursalID() == sucursalID) salasBuscadas.add(salaToDto(sala));
        }
        return salasBuscadas;
    }

    public SalaDTO buscarSalaPorID(int salaID) {
        Sala salaBuscada = BusquedaBinaria.buscarPorId(listadoSalas, salaID);
        if (salaBuscada == null) return null;
        SalaDTO salaDTO = salaToDto(salaBuscada);
        salaDTO.setSucursal(buscarSucursalPorID(salaBuscada.getSucursalID()));

        return salaDTO;
    }

    // ____________________________________CONVERTERS____________________________________
    // OBJs to DTOs
    public SucursalDTO sucursalToDto(Sucursal sucursal) {
        return new SucursalDTO(sucursal);
    }

    public static SalaDTO salaToDto(Sala sala) {
        return new SalaDTO(sala);
    }

    // DTOs to OBJs
    public Sucursal dtoToSucursal(SucursalDTO dto) {
        return new Sucursal(dto.getDenominacion(), dto.getDireccion());
    }

    public Sala dtoToSala(SalaDTO dto) {
        return new Sala(dto.getSucursal().getSucursalID(), dto.getAsientos(), dto.getDenominacion(), dto.getGenero());
    }
}
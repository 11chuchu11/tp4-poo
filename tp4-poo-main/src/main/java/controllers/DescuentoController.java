package controllers;

import dtos.CondicionesDescuentoDTO;
import models.CondicionesDescuento;
import models.TarjetaDescuento;
import types.DiaDeLaSemana;
import types.TipoTarjeta;
import utils.BusquedaBinaria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DescuentoController {

    private static DescuentoController INSTANCE;
    private final List<CondicionesDescuento> listadoDescuentos;

    private DescuentoController() {
        listadoDescuentos = new ArrayList<>();
        precargarDescuentos();
    }

    //aca se implementa el patron singleton
    public static synchronized DescuentoController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new DescuentoController();
        return INSTANCE;
    }

    // ____________________________________HELPERS____________________________________
    private void precargarDescuentos() {
        listadoDescuentos.add(new CondicionesDescuento(1,
                LocalDateTime.of(2025, 6, 1, 0, 0),
                LocalDateTime.of(2025, 6, 30, 23, 59),
                DiaDeLaSemana.LUNES,
                .1f,
                TipoTarjeta.LaNacion,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(1, TipoTarjeta.LaNacion, "LN123456")))));

        listadoDescuentos.add(new CondicionesDescuento(2,
                LocalDateTime.of(2025, 6, 1, 0, 0),
                LocalDateTime.of(2025, 6, 30, 23, 59),
                DiaDeLaSemana.MARTES,
                .15f,
                TipoTarjeta.Clarin365,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(2, TipoTarjeta.Clarin365, "C36578901")))));

        listadoDescuentos.add(new CondicionesDescuento(3,
                LocalDateTime.of(2025, 6, 1, 0, 0),
                LocalDateTime.of(2025, 6, 30, 23, 59),
                DiaDeLaSemana.MIERCOLES,
                .125f,
                TipoTarjeta.MovieClub,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(3, TipoTarjeta.MovieClub, "MC202412")))));

        listadoDescuentos.add(new CondicionesDescuento(4,
                LocalDateTime.of(2025, 6, 1, 0, 0),
                LocalDateTime.of(2025, 7, 1, 23, 59),
                DiaDeLaSemana.JUEVES,
                .2f,
                TipoTarjeta.PAMI,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(4, TipoTarjeta.PAMI, "PM654321")))));

        listadoDescuentos.add(new CondicionesDescuento(5,
                LocalDateTime.of(2025, 6, 15, 0, 0),
                LocalDateTime.of(2025, 7, 15, 23, 59),
                DiaDeLaSemana.VIERNES,
                .18f,
                TipoTarjeta.UADE,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(5, TipoTarjeta.UADE, "UD999888")))));

        listadoDescuentos.add(new CondicionesDescuento(6,
                LocalDateTime.of(2025, 7, 1, 0, 0),
                LocalDateTime.of(2025, 7, 31, 23, 59),
                DiaDeLaSemana.SABADO,
                .08f,
                TipoTarjeta.LaNacion,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(6, TipoTarjeta.LaNacion, "LN777333")))));

        listadoDescuentos.add(new CondicionesDescuento(7,
                LocalDateTime.of(2025, 6, 5, 0, 0),
                LocalDateTime.of(2025, 6, 25, 23, 59),
                DiaDeLaSemana.DOMINGO,
                .25f,
                TipoTarjeta.Clarin365,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(7, TipoTarjeta.Clarin365, "C3654444")))));

        listadoDescuentos.add(new CondicionesDescuento(8,
                LocalDateTime.of(2025, 6, 10, 0, 0),
                LocalDateTime.of(2025, 6, 20, 23, 59),
                DiaDeLaSemana.LUNES,
                .05f,
                TipoTarjeta.MovieClub,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(8, TipoTarjeta.MovieClub, "MC101010")))));

        listadoDescuentos.add(new CondicionesDescuento(9,
                LocalDateTime.of(2025, 6, 12, 0, 0),
                LocalDateTime.of(2025, 7, 1, 23, 59),
                DiaDeLaSemana.MARTES,
                .30f,
                TipoTarjeta.PAMI,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(9, TipoTarjeta.PAMI, "PM001122")))));

        listadoDescuentos.add(new CondicionesDescuento(10,
                LocalDateTime.of(2025, 6, 18, 0, 0),
                LocalDateTime.of(2025, 6, 30, 23, 59),
                DiaDeLaSemana.MIERCOLES,
                .225f,
                TipoTarjeta.UADE,
                new ArrayList<>(Arrays.asList(new TarjetaDescuento(10, TipoTarjeta.UADE, "UD232323")))));


    }

    // ____________________________________METHODS____________________________________

    public static float getPorcentajeDescuentoPorTarjeta(TipoTarjeta tipoTarjeta) {
        switch (tipoTarjeta) {
            case TipoTarjeta.PAMI -> {
                return 0.25f;
            }
            case TipoTarjeta.UADE, TipoTarjeta.MovieClub -> {
                return 0.15f;
            }
            case TipoTarjeta.LaNacion, TipoTarjeta.Clarin365 -> {
                return 0.5f;
            }
            default -> {
                return 0.0f;
            }
        }
    }

    public CondicionesDescuentoDTO buscarDescuentoPorId(int descuentoID) {
        try {
            CondicionesDescuento descuentoBuscado = BusquedaBinaria.buscarPorId(listadoDescuentos, descuentoID);
            if (descuentoBuscado == null) return null;
            return descuentoToDTO(descuentoBuscado);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<CondicionesDescuentoDTO> buscarDescuentoPorDiaDeLaSemana(DiaDeLaSemana diaDeLaSemana) {
        try {
            List<CondicionesDescuentoDTO> descuentosBuscados = new ArrayList<>();
            for (CondicionesDescuento descuento : listadoDescuentos) {
                if (descuento.getDiaSemana() == diaDeLaSemana) descuentosBuscados.add(descuentoToDTO(descuento));
            }
            return descuentosBuscados;
        } catch (Exception ex) {
            return null;
        }
    }

    public float getPorcentajeDescuento(int descuentoID) {
        CondicionesDescuentoDTO descuentoBuscado = buscarDescuentoPorId(descuentoID);
        if (descuentoBuscado == null) return 0;
        float descuento = 0.0f;
        for (TarjetaDescuento tarjetaDescuento : descuentoBuscado.getTarjetasDescuentos()) {
            descuento += getPorcentajeDescuentoPorTarjeta(tarjetaDescuento.getTipoTarjeta());
        }
        descuento = descuento + descuentoBuscado.getPorcentaje();
        return descuento;
    }

    // ____________________________________CONVERTERS____________________________________
    private CondicionesDescuentoDTO descuentoToDTO(CondicionesDescuento descuento) {
        return new CondicionesDescuentoDTO(descuento);
    }

}
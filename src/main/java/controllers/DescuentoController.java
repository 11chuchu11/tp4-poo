package controllers;

import dtos.CondicionesDescuentoDTO;
import models.CondicionesDescuento;
import models.TarjetaDescuento;
import types.DiaDeLaSemana;
import types.TipoTarjeta;
import utils.BusquedaBinaria;

import java.util.ArrayList;
import java.util.List;


public class DescuentoController {

    private static DescuentoController INSTANCE;
    private final List<CondicionesDescuento> listadoDescuentos;

    private DescuentoController() {
        listadoDescuentos = new ArrayList<CondicionesDescuento>();
    }

    public static synchronized DescuentoController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new DescuentoController();
        return INSTANCE;
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
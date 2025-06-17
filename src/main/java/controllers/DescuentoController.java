package controllers;

import models.CondicionesDescuento;
import models.TarjetaDescuento;
import types.TipoTarjeta;

import java.util.*;


/**
 * 
 */
public class DescuentoController {
	
	
	private static DescuentoController instancia;
	
	private List<CondicionesDescuento> Descuento;
	
    public DescuentoController() {
    	Descuento = new ArrayList<CondicionesDescuento>();
    	CondicionesDescuento CondicionesDescuento = new CondicionesDescuento(new Date(), new Date(), 5, 50, TipoTarjeta.PAMI, new ArrayList<TarjetaDescuento>());
    	Descuento.add(CondicionesDescuento);
    	
    }

    /**
     * 
     */
    public void ABM() {
	}


}
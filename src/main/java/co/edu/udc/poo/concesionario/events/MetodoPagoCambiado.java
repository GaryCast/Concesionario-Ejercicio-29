package co.edu.udc.poo.concesionario.events;

import co.edu.udc.poo.concesionario.valueobjects.MetodoPago;
import co.edu.udc.poo.concesionario.valueobjects.Precio;

public record MetodoPagoCambiado(
        String idVenta,
        MetodoPago metodoPago,
        Precio nuevoPrecio
) {}

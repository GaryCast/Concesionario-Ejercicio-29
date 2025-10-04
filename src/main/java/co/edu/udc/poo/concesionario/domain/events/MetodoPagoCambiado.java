package co.edu.udc.poo.concesionario.domain.events;

import co.edu.udc.poo.concesionario.domain.valueobjects.MetodoPago;
import co.edu.udc.poo.concesionario.domain.valueobjects.Precio;

public record MetodoPagoCambiado(
        String idVenta,
        MetodoPago metodoPago,
        Precio nuevoPrecio
) {}

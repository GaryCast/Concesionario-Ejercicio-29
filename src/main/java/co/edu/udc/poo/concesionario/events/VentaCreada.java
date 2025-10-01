package co.edu.udc.poo.concesionario.events;

import java.time.LocalDate;

public record VentaCreada(
        String idVenta,
        String idCliente,
        String idVendedor,
        String idVehiculo,
        LocalDate fechaHora
) {}


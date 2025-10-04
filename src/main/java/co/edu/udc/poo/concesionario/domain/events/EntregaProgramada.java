package co.edu.udc.poo.concesionario.domain.events;

import java.time.LocalDate;

public record EntregaProgramada(
        String idVenta,
        LocalDate fechaEntrega,
        String ubicacion
) {}


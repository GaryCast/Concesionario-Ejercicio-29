package co.edu.udc.poo.concesionario.domain.services;

import co.edu.udc.poo.concesionario.domain.model.Cliente;
import co.edu.udc.poo.concesionario.domain.model.Vehiculo;
import co.edu.udc.poo.concesionario.domain.valueobjects.Precio;
import co.edu.udc.poo.concesionario.domain.valueobjects.MetodoPago;

import java.math.BigDecimal;

public class ServicioCalculoDescuento {

    /**
     * Aplica las reglas de negocio de descuentos sobre el precio base de un vehículo.
     */
    public Precio calcularPrecioFinal(Vehiculo vehiculo,
                                      Cliente cliente,
                                      MetodoPago metodoPago,
                                      Precio precioBase) {
        BigDecimal descuento = BigDecimal.ZERO;

        // Regla: cliente fiel (ejemplo)
        if (cliente.esClienteFiel()) {
            descuento = descuento.add(new BigDecimal("0.05"));
        }

        // Regla: vehículo con más de 12 meses de antigüedad
        if (vehiculo.antiguedadEnMeses() > 12) {
            descuento = descuento.add(new BigDecimal("0.10"));
        }

        // Regla: pago en efectivo
        if (metodoPago.isEfectivo()) {
            descuento = descuento.add(new BigDecimal("0.02"));
        }

        return precioBase.conDescuento(descuento);
    }
}


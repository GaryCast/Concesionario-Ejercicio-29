package co.edu.udc.poo.concesionario.domain.valueobjects;

/**
 * Value Object que representa el metodo de pago usado en una venta.
 * Inmutable y expresivo.
 */
public enum MetodoPago {
    EFECTIVO,
    TARJETA,
    CREDITO;

    /**
     * Indica si el metodo de pago es en efectivo.
     */
    public boolean isEfectivo() {
        return this == EFECTIVO;
    }

    /**
     * Indica si el metodo de pago es con tarjeta.
     */
    public boolean isTarjeta() {
        return this == TARJETA;
    }

    /**
     * Indica si el metodo de pago es a crédito.
     */
    public boolean isCredito() {
        return this == CREDITO;
    }
}

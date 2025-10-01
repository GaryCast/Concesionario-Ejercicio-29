package co.edu.udc.poo.concesionario.valueobjects;

import java.math.BigDecimal;

public final class Precio {
    private final BigDecimal precioBase;
    private final BigDecimal precioExtras;
    private final BigDecimal descuento; // 0 <= descuento <= 1
    private final BigDecimal precioFinal;

    public Precio(BigDecimal precioBase, BigDecimal precioExtras, BigDecimal descuento) {
        if (precioBase.compareTo(BigDecimal.ZERO) < 0 || precioExtras.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Los precios no pueden ser negativos");
        if (descuento.compareTo(BigDecimal.ZERO) < 0 || descuento.compareTo(BigDecimal.ONE) > 0)
            throw new IllegalArgumentException("Descuento debe estar entre 0 y 1");
        this.precioBase = precioBase;
        this.precioExtras = precioExtras;
        this.descuento = descuento;
        BigDecimal total = precioBase.add(precioExtras);
        this.precioFinal = total.multiply(BigDecimal.ONE.subtract(descuento));
    }

    public Precio conDescuento(BigDecimal nuevoDescuento) {
        return new Precio(this.precioBase, this.precioExtras, nuevoDescuento);
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public BigDecimal getPrecioExtras() {
        return precioExtras;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }
// equals/hashCode: usar compareTo( ) == 0 para BigDecimal si quieres comparar valores.
}


package co.edu.udc.poo.concesionario.factory;

import co.edu.udc.poo.concesionario.valueobjects.Precio;

import java.math.BigDecimal;

/**
 * Factory para crear instancias de Precio de forma segura.
 *
 * Garantiza que ningún Precio inválido (nulo o <= 0) pueda existir en el dominio.
 */
public class PrecioFactory {

    /**
     * Crea un Precio a partir de un BigDecimal validado.
     *
     * @param precioBase valor monetario del precio
     * @return instancia de Precio válida
     * @throws IllegalArgumentException si el valor es nulo o <= 0
     */
    public static Precio crear(BigDecimal precioBase,BigDecimal precioFinal) {
        if (precioBase == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        if (precioBase.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        return new Precio(precioBase,precioFinal,BigDecimal.ZERO);
    }

    /**
     * Crea un Precio a partir de un double, convirtiéndolo internamente a BigDecimal.
     *
     * @param valor valor en double
     * @return instancia de Precio válida
     * @throws IllegalArgumentException si el valor es <= 0
     */
    public static Precio crearDesdeDouble(double valor) {
       BigDecimal precioBase = BigDecimal.valueOf(valor);
       return new Precio(precioBase,precioBase,BigDecimal.ZERO);
    }
}

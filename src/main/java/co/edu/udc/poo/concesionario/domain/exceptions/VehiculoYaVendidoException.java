package co.edu.udc.poo.concesionario.domain.exceptions;

/**
 * Excepción de dominio lanzada cuando se intenta vender un vehículo
 * que ya fue marcado como vendido previamente.
 */
public class VehiculoYaVendidoException extends RuntimeException {

    public VehiculoYaVendidoException(String message) {
        super(message);
    }
}


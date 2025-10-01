package co.edu.udc.poo.concesionario.factory;

import co.edu.udc.poo.concesionario.model.Vendedor;
import co.edu.udc.poo.concesionario.model.Venta;
import co.edu.udc.poo.concesionario.model.Cliente;
import co.edu.udc.poo.concesionario.model.Vehiculo;
import co.edu.udc.poo.concesionario.valueobjects.MetodoPago;
import co.edu.udc.poo.concesionario.valueobjects.Precio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Factory para crear instancias de Venta asegurando las invariantes del dominio.
 */
public class VentaFactory {

    /**
     * Crea una nueva Venta garantizando:
     * - El vehículo no esté previamente vendido.
     * - Se asigne un UUID único como identidad.
     * - Se registre la fecha de creación automáticamente.
     *
     * @param cliente    Cliente que realiza la compra
     * @param vehiculo   Vehículo a vender
     * @param metodoPago Metodo de pago elegido
     * @param precioFinal Precio final calculado con descuentos
     * @return una instancia de Venta consistente
     * @throws IllegalStateException si el vehículo ya está vendido
     */
    public static Venta crear(String id,
                              Cliente cliente,
                              Vehiculo vehiculo,
                              MetodoPago metodoPago,
                              Precio precioFinal, Vendedor vendedor) {

        if (vehiculo.estaVendido()) {
            throw new IllegalStateException("El vehículo ya fue vendido y no puede asignarse a otra venta");
        }

        // Marcamos el vehículo como vendido para mantener la invariante
        vehiculo.marcarComoVendido();

        // Creamos y devolvemos la Venta consistente
        return new Venta(id,LocalDateTime.now(),vendedor,cliente,vehiculo,precioFinal,metodoPago,new ArrayList<>());// Fecha de creación automática
    }
}


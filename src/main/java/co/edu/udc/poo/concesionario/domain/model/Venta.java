package co.edu.udc.poo.concesionario.domain.model;

import co.edu.udc.poo.concesionario.domain.enums.Extras;
import co.edu.udc.poo.concesionario.domain.valueobjects.Precio;
import co.edu.udc.poo.concesionario.domain.valueobjects.MetodoPago;
import co.edu.udc.poo.concesionario.domain.exceptions.VehiculoYaVendidoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Agregado raíz Venta.
 * Representa la transacción de venta de un vehículo a un cliente
 * gestionada por un vendedor, con su precio y metodo de pago.
 */
public class Venta {

    private final String idVenta;
    private final LocalDateTime fechaHora;
    private LocalDateTime fechaEntrega;
    private String ubicacionEntrega;

    private final Vendedor vendedor;
    private final Cliente cliente;
    private final Vehiculo vehiculo;
    private final Precio precio;
    private final MetodoPago metodoPago;
    private final ArrayList<Extras> extras;

    // Constructor privado: fuerza el uso de la factory
    public Venta(String idVenta,
                 LocalDateTime fechaHora,
                 Vendedor vendedor,
                 Cliente cliente,
                 Vehiculo vehiculo,
                 Precio precio,
                 MetodoPago metodoPago, ArrayList<Extras> extras) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.precio = precio;
        this.metodoPago = metodoPago;
        this.extras = extras;
    }

    /**
     * Fábrica estática para crear una venta.
     * Verifica las invariantes: el vehículo no debe estar previamente vendido.
     */
    public static Venta crear(Vendedor vendedor,
                              Cliente cliente,
                              Vehiculo vehiculo,
                              Precio precio,
                              MetodoPago metodoPago) {

        Objects.requireNonNull(vendedor, "El vendedor no puede ser nulo");
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo");
        Objects.requireNonNull(precio, "El precio no puede ser nulo");
        Objects.requireNonNull(metodoPago, "El método de pago no puede ser nulo");

        if (vehiculo.estaVendido()) {
            throw new VehiculoYaVendidoException("El vehículo con ID " + vehiculo.getId() + " ya fue vendido");
        }

        String idVenta = UUID.randomUUID().toString();
        Venta venta = new Venta(idVenta, LocalDateTime.now(), vendedor, cliente, vehiculo, precio, metodoPago, new ArrayList<>());

        // Marcar el vehículo como vendido
        vehiculo.marcarComoVendido();

        // (Opcional) Publicar evento de dominio: VehiculoVendido
        // DomainEvents.publish(new VehiculoVendido(vehiculo.getId(), idVenta, LocalDateTime.now()));

        return venta;
    }

    public ArrayList<Extras> getExtras() {
        return extras;
    }

    /**
     * Programa la entrega de un vehículo vendido.
     * Verifica que la fecha de entrega sea posterior a la fecha de la venta.
     */
    public void programarEntrega(LocalDateTime fecha, String ubicacion) {
        Objects.requireNonNull(fecha, "La fecha de entrega no puede ser nula");
        Objects.requireNonNull(ubicacion, "La ubicación no puede ser nula");

        if (fecha.isBefore(this.fechaHora)) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser anterior a la fecha de la venta");
        }

        this.fechaEntrega = fecha;
        this.ubicacionEntrega = ubicacion;
    }

    // ==== Getters ====
    public String getIdVenta() {
        return idVenta;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public String getUbicacionEntrega() {
        return ubicacionEntrega;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Precio getPrecio() {
        return precio;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
}

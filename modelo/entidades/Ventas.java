package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.Date;

public class Ventas {
    public Date fechaHora;
    public Vendedor vendedor;
    public PrecioVenta precioVenta;
    public MetodoPago metodoPago;
    public double descuentos;
    public double precioFinal;
    public Vehiculo vehiculo;
    public String matricula;
    public String ubicacion;
    public Date fechaEntrega;
    public Cliente cliente;

    public Ventas() {
        fechaHora = new Date();
        vendedor = new Vendedor();
        precioVenta = new PrecioVenta();
        metodoPago = new MetodoPago();
        descuentos = 0.05;
        precioFinal = 60000000;
        vehiculo = new Vehiculo();
        matricula = "GVY 130";
        ubicacion = "Bodega";
        fechaEntrega = new Date();
        cliente = new Cliente();
    }

    public Ventas(Date fechaHora, Vendedor vendedor, PrecioVenta precioVenta,MetodoPago metodoPago, double descuentos, double precioFinal,
    Vehiculo vehiculo, String matricula, String ubicacion, Date fechaEntrega, Cliente cliente) {
        this.fechaHora = fechaHora;
        this.vendedor = vendedor;
        this.precioVenta = precioVenta;
        this.metodoPago = metodoPago;
        this.descuentos = descuentos;
        this.precioFinal = precioFinal;
        this.vehiculo = vehiculo;
        this.matricula = matricula;
        this.ubicacion = ubicacion;
        this.fechaEntrega = fechaEntrega;
        this.cliente = cliente;
    }  
    

}
package co.edu.udc.poo.concesionario.modelo.entidades;

import java.sql.Date;
import java.util.ArrayList;


public class Ventas {
    public String registroVenta;
    public Date fechaHora;
    public Vendedor vendedor;
    public PrecioVenta precioVenta;
    public MetodoPago metodoPago;
    public double descuentos;
    public double precioFinal;
    public ArrayList<String> extras;
    public Vehiculo vehiculo;
    public String matricula;
    public String ubicacion;
    public Date fechaEntrega;
    public Cliente cliente;

    public Ventas() {
        registroVenta = new String();
        fechaHora = new Date(2020,10,12);
        vendedor = new Vendedor();
        precioVenta = new PrecioVenta();
        metodoPago = new MetodoPago();
        descuentos = 0.05;
        precioFinal = 60000000;
        vehiculo = new Vehiculo();
        matricula = "GVY 130";
        ubicacion = "Bodega";
        fechaEntrega = new Date(2020,12,12);
        cliente = new Cliente();
    }

    public Ventas(String registroVenta,Date fechaHora, Vendedor vendedor, PrecioVenta precioVenta,MetodoPago metodoPago, double descuentos, double precioFinal,
    Vehiculo vehiculo, String matricula, String ubicacion, Date fechaEntrega, Cliente cliente) {
        this.registroVenta = registroVenta;
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
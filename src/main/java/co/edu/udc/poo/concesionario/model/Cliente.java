package co.edu.udc.poo.concesionario.model;

import co.edu.udc.poo.concesionario.enums.Satisfaccion;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private final String cedula;
    private final String nombre;
    private final String email;
    private final String telefono;
    private final String departamento;
    private final String ciudad;
    private final String direccion;
    private final String contactoInicial;
    private final Satisfaccion nivelSatisfaccion;
    private String referidoPor;

    public void setReferidoPor(String referidoPor) {
        this.referidoPor = referidoPor;
    }

    public String getReferidoPor() {
        return referidoPor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Satisfaccion getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContactoInicial() {
        return contactoInicial;
    }


    public List<String> getVentasRealizadas() {
        return ventasRealizadas;
    }

    // historial de compras
    private final List<String> ventasRealizadas = new ArrayList<>();

    public Cliente(String cedula, String nombre, String email, String telefono, String departamento, String ciudad, String direccion, String contactoInicial, Satisfaccion nivelSatisfaccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.contactoInicial = contactoInicial;
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    // registrar una compra (se guarda el ID de la venta)
    public void agregarCompra(String idVenta) {
        ventasRealizadas.add(idVenta);
    }

    // regla de negocio: un cliente fiel es el que ha hecho al menos 3 compras
    public boolean esClienteFiel() {
        return ventasRealizadas.size() >= 3;
    }

    // getters
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}


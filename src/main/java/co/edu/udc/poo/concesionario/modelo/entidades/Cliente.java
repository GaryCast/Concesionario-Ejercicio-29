package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    public String nombre;
    public String cedula;
    public String departamento;
    public String ciudad;
    public String direccion;
    public String telefono;
    public String correo;
    public boolean contactoInicial;
    public String referidoPor;
    public String referidoTel;
    public int nivelSatisfaccion;
    public List<Vehiculo> vehiculosComprados = new ArrayList<>();

    public Cliente() {
        nombre = "Juan Perez";
        cedula = "123456789";
        departamento = "Cundinamarca";
        ciudad = "Bogotá";
        direccion = "Calle 123 # 45-67";
        telefono = "3001234567";
        correo = "";
        contactoInicial = true;
        referidoPor = "";
        referidoTel = "";
        nivelSatisfaccion = Satisfaccion.bueno.ordinal();
        vehiculosComprados = new ArrayList<>();
    }

    public Cliente(String nombre, String cedula, String departamento, String ciudad, String direccion, String telefono,
    String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public List<Vehiculo> vehiculosPorCliente() {
        return vehiculosComprados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isContactoInicial() {
        return contactoInicial;
    }

    public void setContactoInicial(boolean contactoInicial) {
        this.contactoInicial = contactoInicial;
    }

    public String getReferidoPor() {
        return referidoPor;
    }

    public void setReferidoPor(String referidoPor) {
        this.referidoPor = referidoPor;
    }

    public String getReferidoTel() {
        return referidoTel;
    }

    public void setReferidoTel(String referidoTel) {
        this.referidoTel = referidoTel;
    }

    public int getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setNivelSatisfaccion(int nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public List<Vehiculo> getVehiculosComprados() {
        return vehiculosComprados;
    }

    public void setVehiculosComprados(List<Vehiculo> vehiculosComprados) {
        this.vehiculosComprados = vehiculosComprados;
    }
}

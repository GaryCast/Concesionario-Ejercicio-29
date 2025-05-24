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
    
}

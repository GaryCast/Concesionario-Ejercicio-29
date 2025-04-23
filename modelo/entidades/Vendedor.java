package co.edu.udc.poo.concesionario.modelo.entidades;

public class Vendedor {
    public String nombre;
    public String sexo;
    public String edad;
    public String direccion;
    public String ciudad;
    public String departamento;
    public String telefono;
    public String correo;
    public String nif;
    public int ventasRealizadas;
    public double totalVentas;

    public Vendedor() {
        nombre = "Yuris Tovar";
        sexo = "femenino";
        edad = "41";
        direccion = "Condominio Ipanema";
        telefono = "3043029876";
        correo = "cami041002@hotmail.com";
        nif = "45549964";
        ventasRealizadas = 4;
        totalVentas = 325000000;
        ciudad = "Cartagena";
        departamento = "Bolivar";

    }

    public Vendedor(String nombre, String sexo, String edad, String direccion, String telefono, String correo,
    String nif, int ventasRealizadas, double totalVentas) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.nif = nif;
        this.ventasRealizadas = ventasRealizadas;
        this.totalVentas = totalVentas;
    }
}
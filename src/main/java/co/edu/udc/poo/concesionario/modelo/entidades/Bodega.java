package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Bodega{

    public String nombre;
    public String ubicacion;
    public List<Vehiculo> VehiculosDisponiblesInSitu = new ArrayList<>();

    public Bodega(){
        nombre = "ternera";
        ubicacion = "local";
    }
    public Bodega(String nombre, String ubicacion){

        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }
    public List<Vehiculo> getVehiculos(){
        return VehiculosDisponiblesInSitu;
    }
    
}
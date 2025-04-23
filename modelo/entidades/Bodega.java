package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Bodega{
    String nombre;
    String ubicacion;
    List<Vehiculo> VehiculosDisponiblesInSitu = new ArrayList<>();

    Bodega(){
        nombre = "ternera";
        ubicacion = "local";
    }
    Bodega(String nombre, String ubicacion){

        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }
    public List<Vehiculo> getVehiculos(){
        return VehiculosDisponiblesInSitu;
    }
    
}
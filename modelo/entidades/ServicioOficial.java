package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class ServicioOficial extends Concesionario {
    List<Vehiculo> VehiculosDisponiblesInSitu;

    public ServicioOficial() {
        VehiculosDisponiblesInSitu= new ArrayList<>();
    }

    public ServicioOficial(Vehiculo vehiculo) {
        VehiculosDisponiblesInSitu= new ArrayList<>();
        this.VehiculosDisponiblesInSitu.add(vehiculo);
    }

    public List<Vehiculo> getVehiculos(){
        return VehiculosDisponiblesInSitu;
    }
}

package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Oficina extends Concesionario {
    List<Vehiculo> VehiculosDisponiblesInSitu;

    public Oficina() {
    VehiculosDisponiblesInSitu = new ArrayList<>();
    }

    public Oficina(Vehiculo vehiculo) {
        VehiculosDisponiblesInSitu = new ArrayList<>();
        this.VehiculosDisponiblesInSitu.add(vehiculo);
    }

    public List<Vehiculo> getVehiculos(){
        return VehiculosDisponiblesInSitu;
    }
}

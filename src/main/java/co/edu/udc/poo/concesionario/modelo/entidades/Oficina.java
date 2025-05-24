package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Oficina extends Concesionario {
    List<Vehiculo> VehiculosDisponiblesInSitu;

    public Oficina() {
    VehiculosDisponiblesInSitu = new ArrayList<>();
    }

    public Oficina(Vehiculo vehiculo) {
        super();
        VehiculosDisponiblesInSitu = new ArrayList<>();
        this.VehiculosDisponiblesInSitu.add(vehiculo);
    }

    public Oficina(String nombre, String departamento, String ciudad,
                   String direccion, String nif, String telefono, String correo) throws Exception {
        super(nombre, departamento, ciudad, direccion, nif, telefono, correo);
        VehiculosDisponiblesInSitu = new ArrayList<>();
    }

    public List<Vehiculo> getVehiculos(){
        return VehiculosDisponiblesInSitu;
    }
}

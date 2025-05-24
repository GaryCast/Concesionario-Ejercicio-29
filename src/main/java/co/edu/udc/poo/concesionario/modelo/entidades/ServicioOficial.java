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

    public ServicioOficial(String nombre, String departamento, String ciudad,
                   String direccion, String nif, String telefono, String correo) throws Exception {
        super(nombre, departamento, ciudad, direccion, nif, telefono, correo);
        VehiculosDisponiblesInSitu = new ArrayList<>();
    }

    public List<Vehiculo> getVehiculos(){
        return VehiculosDisponiblesInSitu;
    }
}

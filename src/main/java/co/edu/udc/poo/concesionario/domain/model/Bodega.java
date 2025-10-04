
package co.edu.udc.poo.concesionario.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Bodega {
    private Long id;
    private String nombre;
    private String ubicacion;
    private List<Vehiculo> vehiculosDisponiblesInSitu;
    private static Long contadorId = 1L;

    public Bodega() {
        this.id = contadorId++;
        this.nombre = "ternera";
        this.ubicacion = "local";
        this.vehiculosDisponiblesInSitu = new ArrayList<>();
    }

    public Bodega(String nombre, String ubicacion) {
        this();
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        if (ubicacion != null && !ubicacion.trim().isEmpty()) {
            this.ubicacion = ubicacion;
        }
    }

    public List<Vehiculo> getVehiculosDisponiblesInSitu() {
        return vehiculosDisponiblesInSitu;
    }

    public void setVehiculosDisponiblesInSitu(List<Vehiculo> vehiculosDisponiblesInSitu) {
        this.vehiculosDisponiblesInSitu = vehiculosDisponiblesInSitu != null ?
                vehiculosDisponiblesInSitu : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bodega bodega = (Bodega) o;
        return id != null && id.equals(bodega.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}

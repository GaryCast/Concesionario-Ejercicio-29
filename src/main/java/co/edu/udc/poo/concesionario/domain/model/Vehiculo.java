package co.edu.udc.poo.concesionario.domain.model;

import co.edu.udc.poo.concesionario.domain.enums.Caracteristicas;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vehiculo {
    public boolean nuevo;
    public String mantenimientos;
    public String añoFabrica;
    public String cilindraje;
    public String potenciaFiscal;
    public Integer precioBase;
    public String color;
    public String Marca;
    public String Carroceria;
    public String Modelo;
    public String tipoVehiculo;
    public String carroceria;
    public String numeroBastidor;
    public String ubicacion;
    public String transmision;
    public String traccion;
    public String combustible;
    public String eficienciaCombustible;
    public List<Caracteristicas> Basicos = new ArrayList<>();
    public HashMap<Caracteristicas, Integer> Extras = new HashMap<Caracteristicas, Integer>();

    // === Nuevo atributo ===
    private boolean vendido = false;

    public Vehiculo() {
        mantenimientos = "no incluido";
        nuevo = true;
        añoFabrica = "2025";
        cilindraje = "1.6";
        potenciaFiscal = "14C";
        precioBase = 51990000;
        color = "blanco";
        numeroBastidor = "889_891_37";
        ubicacion = "vitrina";
        transmision = "mecanico";
        traccion = "2 ruedas";
        combustible = "gasolina";
        eficienciaCombustible = "45 kil/galon";
        tipoVehiculo = "Sencillo";
        List<Caracteristicas> basicos = new ArrayList<>();

        Basicos.addAll(basicos);
        for (Caracteristicas caracteriscas : Caracteristicas.values()) {
            if (basicos.contains(caracteriscas)) {
                continue;
            } else {
                Extras.put(caracteriscas, 500000);
            }
        }

    }

    public Vehiculo(List<Caracteristicas> basico) {
        nuevo = true;
        añoFabrica = "2025";
        cilindraje = "1.6";
        potenciaFiscal = "14C";
        precioBase = 51990000;
        color = "blanco";
        numeroBastidor = "889_891_37";
        ubicacion = "vitrina";
        transmision = "mecanico";
        traccion = "2 ruedas";
        combustible = "gasolina";
        eficienciaCombustible = "45 kil/galon";
        tipoVehiculo = "Sencillo";
        List<Caracteristicas> basicos = new ArrayList<>();
        basicos.addAll(basico);
        Basicos.addAll(basicos);
        for (Caracteristicas caracteriscas : Caracteristicas.values()) {
            if (basicos.contains(caracteriscas)) {
                continue;
            } else {
                Extras.put(caracteriscas, 500000);
            }
        }

    }


    public Vehiculo(boolean nuevo, String añoFabrica, String cilindraje, String potenciaFiscal, Integer precioBase,
                    List<Caracteristicas> basico, String color, String numeroBastidor, String ubicacion,
                    String transmision, String traccion, String combustible, String eficienciaCombustible) {
        this.nuevo = nuevo;
        this.añoFabrica = añoFabrica;
        this.cilindraje = cilindraje;
        this.potenciaFiscal = potenciaFiscal;
        this.precioBase = precioBase;
        this.color = color;
        this.numeroBastidor = numeroBastidor;
        this.ubicacion = ubicacion;
        this.transmision = transmision;
        this.traccion = traccion;
        this.combustible = combustible;
        this.eficienciaCombustible = eficienciaCombustible;
        List<Caracteristicas> basicos = new ArrayList<>();
        basicos.addAll(basico);
        Basicos.addAll(basicos);
        for (Caracteristicas caracteriscas : Caracteristicas.values()) {
            if (basicos.contains(caracteriscas)) {
                continue;
            } else {
                Extras.put(caracteriscas, 500000);
            }
        }
    }

    public List<Caracteristicas> getBasicos() {
        return Basicos;
    }

    public HashMap<Caracteristicas, Integer> getExtras() {
        return Extras;
    }

    public String getCarroceria() {
        return Carroceria;
    }

    public void setCarroceria(String carroceria) {
        Carroceria = carroceria;
    }

    public void mostrarCaracteristicasSeguridad() {
        System.out.println("Caracteristicas de seguridad: ");
        for (Caracteristicas caracteristica : Basicos) {
            if (caracteristica == Caracteristicas.abs
                    || caracteristica == Caracteristicas.airBags
                    || caracteristica == Caracteristicas.direccionAsistida) {
                System.out.println(caracteristica);
            }
        }
    }

    public List<Vehiculo> vehiculosConMantenimientoIncluido(List<Vehiculo> vehiculos) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.mantenimientos.equals("incluido")) {
                resultado.add(vehiculo);
            }
        }
        return resultado;
    }

    // === Nuevos métodos para integración con Venta ===

    /**
     * Indica si el vehículo ya fue vendido.
     * @return true si ya está vendido, false en caso contrario
     */
    public boolean estaVendido() {
        return vendido;
    }

    /**
     * Marca el vehículo como vendido. Lanza IllegalStateException si ya estaba marcado.
     */
    public void marcarComoVendido() {
        if (this.vendido) {
            throw new IllegalStateException("El vehículo ya está marcado como vendido");
        }
        this.vendido = true;
    }

    /**
     * Devuelve el identificador del vehículo (número de bastidor).
     */
    public String getId() {
        return numeroBastidor;
    }

    /**
     * Calcula la antigüedad aproximada en meses usando el campo añoFabrica.
     * Si añoFabrica no puede convertirse a entero, devuelve 0.
     */
    public int antiguedadEnMeses() {
        try {
            int anioFab = Integer.parseInt(añoFabrica);
            LocalDate fechaFabricacion = LocalDate.of(anioFab, 1, 1);
            Period periodo = Period.between(fechaFabricacion, LocalDate.now());
            return periodo.getYears() * 12 + periodo.getMonths();
        } catch (NumberFormatException e) {
            return 0; // si el campo no tiene un número válido
        }
    }
}

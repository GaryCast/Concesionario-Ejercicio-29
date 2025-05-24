package co.edu.udc.poo.concesionario.modelo.entidades;
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
    public HashMap<Caracteristicas, Integer> Extras = new HashMap <Caracteristicas, Integer>();

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
        tipoVehiculo="Sencillo";
        List<Caracteristicas> basicos = new ArrayList<>();
        
        Basicos.addAll(basicos);
        for(Caracteristicas caracteriscas: Caracteristicas.values()){
            if(basicos.contains(caracteriscas)){
                continue;
            }else{
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
        tipoVehiculo="Sencillo";
        List<Caracteristicas> basicos = new ArrayList<>();
        basicos.addAll(basico);
        Basicos.addAll(basicos);
        for(Caracteristicas caracteriscas: Caracteristicas.values()){
            if(basicos.contains(caracteriscas)){
                continue;
            }else{
                Extras.put(caracteriscas, 500000);
            }
        }

    }


    public Vehiculo(boolean nuevo, String añoFabrica, String cilindraje, String potenciaFiscal, Integer precioBase,List<Caracteristicas> basico, String color, String numeroBastidor, String ubicacion, String transmision, String traccion, String combustible, String eficienciaCombustible) {
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
        for(Caracteristicas caracteriscas: Caracteristicas.values()){
            if(basicos.contains(caracteriscas)){
                continue;
            }else{
                Extras.put(caracteriscas, 500000);
            }
        }
    }

    public List<Caracteristicas> getBasicos(){
        return Basicos;
    }

    public HashMap<Caracteristicas,Integer> getExtras(){
        return Extras;
    }

    public String getCarroceria() {
        return Carroceria;
    }

    public void setCarroceria(String carroceria) {
        Carroceria = carroceria;
    }

    public void mostrarCaracteristicasSeguridad(){
        System.out.println("Caracteristicas de seguridad: ");
        for(Caracteristicas caracteristica: Basicos){
            if(caracteristica == Caracteristicas.abs || caracteristica == Caracteristicas.airBags || caracteristica == Caracteristicas.direccionAsistida){
                System.out.println(caracteristica);
            }
        }
    }
    public List<Vehiculo>vehiculosConMantenimientoIncluido(List<Vehiculo> vehiculos){
        List<Vehiculo> resultado = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.mantenimientos.equals("incluido")){
                resultado.add(vehiculo);
            }
        }
        return resultado;
    }
}


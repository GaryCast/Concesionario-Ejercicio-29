package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.*;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VehiculoCRUD {

    public ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public void agregar(Vehiculo vehiculo) throws Exception{
        cargarDatos();
        boolean existe = false;
        for(Vehiculo v: listaVehiculos){
            if(v.numeroBastidor.equals(vehiculo.numeroBastidor)){
                existe = true;
            }
        }
        if(existe){
            throw new Exception("El vehiculo ya existe");
        }else{
            cargarDatos();
            listaVehiculos.add(vehiculo);
            guardarDatos();
        }
    }

    public Vehiculo buscar(String codigo) throws Exception{
        cargarDatos();
        for(Vehiculo v: listaVehiculos){
            if(v.numeroBastidor.equals(codigo)){
                return v;
            }
        }
        throw new Exception("Vehiculo no encontrado");
    }

    public void editar(Vehiculo vehiculo) throws Exception{
        cargarDatos();
        int index = 0;
        Scanner entrada = new Scanner(System.in);
        for(Vehiculo v: listaVehiculos){
            if(v.numeroBastidor.equals(vehiculo.numeroBastidor)){
                index = listaVehiculos.indexOf(v);

                System.out.println("Editar Vehiculo");
                System.out.println("1) nuevo: "+(v.nuevo?"nuevo":"usado"));
                System.out.println("2) Mantenimientos: "+v.mantenimientos);
                System.out.println("3) Cilindraje: "+ v.cilindraje);
                System.out.println("4) Potencia fiscal: "+v.potenciaFiscal);
                System.out.println("5) Precio base: $"+v.precioBase);
                System.out.println("6) Color: "+ v.color);
                System.out.println("7) Marca: "+v.Marca);
                System.out.println("8) Carroceria: "+v.carroceria);
                System.out.println("9) Modelo: "+v.Modelo);
                System.out.println("10) Ubicación: "+v.ubicacion);
                System.out.println("11) Eficiencia de combustible: "+v.eficienciaCombustible);
                System.out.println("12) Caracteristicas basicas "+v.Basicos.size()+" elementos");
                System.out.print("Digite el numero correspondiente a la opción: ");
                String opcion = entrada.nextLine();
                switch (opcion){
                    case "1":
                        if(v.nuevo){
                            System.out.println("Quieres cambiar el estado de nuevo a usado? (S/N)");
                            String nuevo = entrada.nextLine();
                            v.nuevo = nuevo.equalsIgnoreCase("S")?false:true;
                            listaVehiculos.set(index,v);
                        }else{
                            System.out.println("Quieres cambiar el estado de usado a nuevo? (S/N)");
                            String nuevo = entrada.nextLine();
                            v.nuevo = nuevo.equalsIgnoreCase("S")?true:false;
                            listaVehiculos.set(index,v);
                        }
                        guardarDatos();
                        break;
                    case "2":
                        System.out.println("mantenimiento disponible por: ");
                        String mantenimiento = entrada.nextLine();
                        v.mantenimientos = mantenimiento;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "3":
                        System.out.println("Nuevo cilindraje: ");
                        String cilindraje = entrada.nextLine();
                        v.cilindraje = cilindraje;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "4":
                        System.out.println("Potencia fiscal: ");
                        String potenciaFiscal = entrada.nextLine();
                        v.potenciaFiscal = potenciaFiscal;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "5":
                        System.out.print("nuevo precio base: $");
                        Integer precioBase = Integer.parseInt(entrada.nextLine());
                        v.precioBase = precioBase;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "6":
                        System.out.println("Nuevo color: ");
                        String color = entrada.nextLine();
                        v.color = color;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "7":
                        System.out.println("Cambiar marca");
                        int count = 1;
                        for(Marca marca: MarcaCRUD.listarTodo()){
                            System.out.println(count+") "+marca.marca);
                            count++;
                        }
                        count = 1;
                        System.out.println("Elija el numero de la marca elegida");
                        int marca = Integer.parseInt(entrada.nextLine());
                        v.Marca = MarcaCRUD.listarTodo().get(marca-1).marca;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "8":
                        System.out.println("Cambiar carroceria por");
                        count = 1;
                        for(Carroceria carroceria: MarcaCRUD.buscar(v.Marca).Carroceria){
                            System.out.println(count+") "+carroceria.tipo);
                            count++;
                        }
                        System.out.println("Selecciona la carroceria por su numero: ");
                        int carroceria = Integer.parseInt(entrada.nextLine());
                        Carroceria elegida = MarcaCRUD.buscar(v.Marca).Carroceria.get(carroceria-1);
                        v.carroceria = elegida.tipo.toString();
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "9":
                        System.out.println("Cambiar modelo");
                        count=1;
                        int selected = 0;
                        Carroceria carroceriaActual = CarroceriaCRUD.buscar(TipoCarroceria.valueOf(v.carroceria));
                        System.out.println("Modelos de la carroceria: "+carroceriaActual.tipo);
                        for(Modelo modelo: carroceriaActual.getModelos()){
                            if(modelo.TipoModelo.equals(v.Modelo)){
                                System.out.println(count+") "+modelo.TipoModelo+" (actual)");
                                selected = count;
                            }else{
                                System.out.println(count+") "+modelo.TipoModelo);
                            }
                            count++;
                        }
                        System.out.println("Elija el modelo: ");
                        int modelo = Integer.parseInt(entrada.nextLine());
                        if(selected==modelo){
                            throw new Exception("No puedes elegir el modelo actual!");
                        }
                        v.Modelo = carroceriaActual.modelos.get(modelo-1).TipoModelo;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "10":
                        System.out.println("nueva Ubicación: ");
                        String ubicacion = entrada.nextLine();
                        v.ubicacion=ubicacion;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "11":
                        System.out.println("Eficiencia de combustible: ");
                        String eficiencia = entrada.nextLine();
                        v.eficienciaCombustible = eficiencia;
                        listaVehiculos.set(index,v);
                        guardarDatos();
                        break;
                    case "12":
                        System.out.println("Caracteristicas basicas");
                        System.out.println("1) agregar");
                        System.out.println("2) Eliminar una caracterisitica");
                        System.out.println("3) Ver caracteristicas basicas");
                        String caracteristicaOpcion = entrada.nextLine();
                        switch (caracteristicaOpcion){
                            case "1":
                                System.out.println("Agregar una caracteristica");
                                for(Caracteristicas car: Caracteristicas.values()){
                                    System.out.println(car.ordinal()+1+") "+car.name());
                                }
                                System.out.println("Seleccione una caracteristica");
                                int caracteristica = Integer.parseInt(entrada.nextLine());
                                Caracteristicas carNew = Caracteristicas.values()[caracteristica];
                                v.Basicos.add(carNew);
                                listaVehiculos.set(index,v);
                                guardarDatos();
                                break;
                            case "2":
                                System.out.println("Eliminar caracteristica");
                                count = 1;
                                Caracteristicas eliminada = null;
                                for(Caracteristicas car: v.Basicos){
                                    System.out.println(count+") "+car.name());
                                }
                                int caracteristicaEliminada = Integer.parseInt(entrada.nextLine());
                                if(caracteristicaEliminada < 0 || caracteristicaEliminada > v.Basicos.size()){
                                    throw new Exception("Opcion no valida");
                                }
                                eliminada = v.Basicos.get(caracteristicaEliminada-1);
                                v.Basicos.remove(eliminada);
                                listaVehiculos.set(index,v);
                                guardarDatos();
                                break;
                            case "3":
                                System.out.println("id    12Caracteristica");
                                for(Caracteristicas c: v.Basicos){
                                    System.out.println(c.ordinal()+1+"    "+c.name());
                                }
                                System.out.println("Presione cualquier tecla para continuar...");
                                entrada.nextLine();
                                editar(vehiculo);
                                break;
                        }
                        break;
                    default:
                        throw new Exception("Opción invalida");
                }
            }
        }
        entrada.close();
    }

    public void eliminar(String codigo)throws Exception{
        cargarDatos();
        boolean encontrado = false;
        for(Vehiculo v: listaVehiculos){
            if(v.numeroBastidor.equals(codigo)){
                encontrado = true;
                listaVehiculos.remove(v);
                guardarDatos();
            }
        }
        if(!encontrado){
            throw new Exception("Vehiculo no existe");
        }
    }

    public ArrayList<Vehiculo> listarTodo() throws Exception {
        cargarDatos();
        return listaVehiculos;
    }

    public Integer contar() throws Exception{
        return listarTodo().size();
    }

    public void cargarDatos() throws Exception{
        Gson gson = new Gson();
        FileReader lector = new FileReader("databaseJson/Vehiculos.json");
        Type vehiculoTypeList = new TypeToken<ArrayList<Vehiculo>>(){}.getType();
        listaVehiculos = gson.fromJson(lector,vehiculoTypeList);
    }

    public void guardarDatos(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaVehiculos);
        Datos.escribirArchivo("databaseJson/Vehiculos.json",json);
    }
}

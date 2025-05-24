package co.edu.udc.poo.concesionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.edu.udc.poo.concesionario.modelo.crud.ListaMarcaCRUD;
import co.edu.udc.poo.concesionario.modelo.crud.VehiculoCRUD;
import co.edu.udc.poo.concesionario.modelo.entidades.*;


public class Main {


    static List<Caracteristicas> SencilloLogan = new ArrayList<>();
    static List<Caracteristicas> PremiumLogan = new ArrayList<>();
    static Modelo Logan = new Modelo("Logan");
    static Carroceria sedan = new Carroceria(TipoCarroceria.SEDAN);
    static List<Carroceria> carroceria = new ArrayList<>();
    static Marca RENAULT = new Marca("Renault");

    static List<Marca> marcas = new ArrayList<>();
    

    public static void main(String[] args) throws Exception {

        /*SencilloLogan.add(Caracteristicas.direccionAsistida);
        SencilloLogan.add(Caracteristicas.abs);
        SencilloLogan.add(Caracteristicas.airBags);
        SencilloLogan.add(Caracteristicas.sistEntretenimiento);
        for(Caracteristicas caracteristica: Caracteristicas.values()){
            PremiumLogan.add(caracteristica);
        }
        Logan.Sencillo.addAll(SencilloLogan);
        Logan.Premium.addAll(PremiumLogan);
        sedan.modelos.add(Logan);
        RENAULT.Carroceria.add(sedan);

        marcas.add(RENAULT);



        registrarVehiculo();
        List<Vehiculo> vehiculos = new ArrayList<>();*/
        VehiculoCRUD crud = new VehiculoCRUD();
        Vehiculo encontrado = crud.buscar("889_891_37");
        crud.editar(encontrado);
        System.out.println(encontrado.ubicacion);
        
    }



    public static void listarMarcas(){
        int numerador = 1;
        for(ListaMarcas marca: ListaMarcas.values()){
            System.out.println(numerador+") "+marca);
            numerador++;
        }
    }
    public static void listarCarrocerias(){
        int numerador = 1;
        for(TipoCarroceria tipo: TipoCarroceria.values()){
            System.out.println(numerador+") "+tipo);
            numerador++;
        }
    }

    public static void listarModelos(){
        int numerador = 1;
        for(TipoModelo tipo: TipoModelo.values()){
            System.out.println(numerador+") "+tipo);
            numerador++;
        }
    }


    public static void registrarVehiculo(){
        System.out.println("Seleccione la marca");
        Scanner input = new Scanner(System.in);
        int num = 1;
        Marca marcaElegida;
        for(Marca marca: marcas){
            System.out.println(num+") "+marca.marca);
        }
        num = 1;
        int respuesta = Integer.parseInt(input.nextLine())-1;
        marcaElegida = marcas.get(respuesta);


        System.out.println("Seleccione la carroceria");
        for(Carroceria carroceria: marcaElegida.Carroceria ){
            System.out.println(num+") "+carroceria.tipo);
            num++;
        }
        num = 1;
        respuesta = Integer.parseInt(input.nextLine())-1;
        Carroceria carroceriaElegida = marcaElegida.Carroceria.get(respuesta);

        System.out.println("Seleccione el modelo");
        for(Modelo modelo: carroceriaElegida.modelos){
            System.out.println(num+") "+modelo.TipoModelo);
        }
        num = 1;
        respuesta = Integer.parseInt(input.nextLine())-1;
        Modelo modeloElegido = carroceriaElegida.modelos.get(respuesta);

        System.out.println("Seleccione un tipo de vehiculo");
        System.out.println("1.) Sencillo");
        System.out.println("2.) Premium");
        respuesta = Integer.parseInt(input.nextLine());


        if(respuesta == 1){
            Vehiculo vehiculoSeleccionado = new Vehiculo(modeloElegido.Sencillo);

            System.out.println("Elegiste el "+ marcaElegida.marca+", Carroceria: "+carroceriaElegida.tipo+", Modelo: "+modeloElegido.TipoModelo+" Tipo: Sencillo");
            System.out.println("Tiene este equipamiento");
            for(Caracteristicas caracteristica: modeloElegido.Sencillo){
                System.out.println(num+") "+caracteristica);
            }
            System.out.println("El precio base de este vehiculo es: "+vehiculoSeleccionado.precioBase);

            System.out.println("Puedes agregar los siguientes Extras");
            for(Caracteristicas caracteristica : vehiculoSeleccionado.Extras.keySet()){
                System.out.println(caracteristica);
            }
            System.out.print("Escriba los extra que desea seleccionar (separelos por ,)");
            String extraPedidos = input.nextLine();
            String extrasSelect[] = extraPedidos.split(",");
            System.out.println("Tienes "+extrasSelect.length+" extras");
            vehiculoSeleccionado.precioBase += (extrasSelect.length*500000);
            System.out.println("el precio del vehiculo con los extras seleccionados es $ "+vehiculoSeleccionado.precioBase);
        }
        else if(respuesta == 2){
            Vehiculo vehiculoSeleccionado = new Vehiculo(modeloElegido.Premium);

            System.out.println("Elegiste el "+ marcaElegida.marca+", Carroceria: "+carroceriaElegida.tipo+", Modelo: "+modeloElegido.TipoModelo+" Tipo: Premium");
            System.out.println("Tiene este equipamiento");
            Integer valorBase = vehiculoSeleccionado.precioBase;
            for(Caracteristicas caracteristica: modeloElegido.Premium){
                System.out.println(num+") "+caracteristica);
                valorBase += 500000;
                num++;
            }
            
            

            System.out.println("El costo de este vehiculo es $ "+valorBase);



        }


    }

}
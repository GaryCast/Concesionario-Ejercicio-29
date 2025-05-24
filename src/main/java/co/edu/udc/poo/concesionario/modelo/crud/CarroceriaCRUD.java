package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.Carroceria;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.udc.poo.concesionario.modelo.entidades.Modelo;
import co.edu.udc.poo.concesionario.modelo.entidades.TipoCarroceria;
import co.edu.udc.poo.concesionario.modelo.entidades.TipoModelo;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CarroceriaCRUD {
    public static ArrayList<Carroceria> listaCarrocerias = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        try{
            Carroceria encontrada = buscar(TipoCarroceria.SUV);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
    public static void agregar(Carroceria objeto) throws Exception {
        getCarrocerias();
        for (Carroceria b : listaCarrocerias) {
            if(b.tipo.equals(objeto.tipo)){
                throw new Exception("Carroceria ya existe");
            }
        }
        listaCarrocerias.add(objeto);
        guardar();
    }

    public static Carroceria buscar(TipoCarroceria tipo) throws FileNotFoundException {
        getCarrocerias();
        for(Carroceria b : listaCarrocerias){
            if(b.tipo.equals(tipo)){
                return b;
            }
        }
        throw new IllegalArgumentException("Carroceria no encontrada");
    }

    public static void editar(Carroceria objeto) throws FileNotFoundException {
        getCarrocerias();
        int index = 0;
        boolean condicion = false;
        for(Carroceria b : listaCarrocerias){
            if(b.tipo.equals(objeto.tipo)){
                Scanner entrada = new Scanner(System.in);
                condicion = true;
                Carroceria encontrada = objeto;
                if(encontrada != null){
                    while (condicion) {
                        System.out.println("1) Tipo Carroceria actual: " + encontrada.tipo);
                        System.out.println("2) Lista de modelos: " + encontrada.modelos.toString());
                        System.out.println("elija que quiere editar: 1/2");
                        try {
                            int opcion = Integer.parseInt(entrada.nextLine());
                            switch (opcion) {
                                case 1:
                                    condicion = false;
                                    System.out.println("escoja el nuevo tipo de carroceria:");
                                    int i = 1;
                                    for(TipoCarroceria tipo: TipoCarroceria.values()){
                                        System.out.println(i+")"+tipo);
                                        i++;
                                    }
                                    int respuesta = Integer.parseInt(entrada.nextLine())-1;
                                    while(respuesta < 0 || respuesta > TipoCarroceria.values().length-1){
                                        System.out.println("Seleccione una opción valida, de 1 a 4: ");
                                        respuesta = Integer.parseInt(entrada.nextLine())-1;
                                    }

                                    encontrada.tipo = TipoCarroceria.values()[respuesta];
                                    System.out.println("Carroceria editada correctamente, nueva tipo: " + encontrada.tipo + "");
                                    listaCarrocerias.set(index, encontrada);
                                    guardar();
                                    break;
                                case 2:
                                    System.out.println("1) Agregar modelo");
                                    System.out.println("2) Eliminar modelo");
                                    System.out.println("¿Que desea hacer?");
                                    int opcion2 = Integer.parseInt(entrada.nextLine());

                                    switch (opcion2) {
                                        case 1:
                                            System.out.println("escoja el tipo de modelo:");
                                            int j = 1;
                                            for (TipoModelo tipoModelo : TipoModelo.values()) {
                                                System.out.println(j+") "+ tipoModelo);
                                                j++;
                                            }
                                            int respuesta2 = Integer.parseInt(entrada.nextLine())-1;

                                            Modelo nuevo = new Modelo();
                                            encontrada.modelos.add(nuevo);
                                            listaCarrocerias.set(index, encontrada);
                                            guardar();
                                            break;
                                        case 2:
                                            System.out.println("Escoja el modelo que desea eliminar: ");
                                            int k = 1;
                                            for(Modelo modelo: encontrada.modelos){
                                                System.out.println(k+") "+modelo.TipoModelo);
                                            }
                                            int respuesta3 = Integer.parseInt(entrada.nextLine())-1;
                                            encontrada.modelos.remove(respuesta3);
                                            listaCarrocerias.set(index, encontrada);
                                            guardar();
                                            break;
                                        default:
                                            System.out.println("Opción seleccionada no existe");
                                            break;
                                    }
                                    condicion = false;
                                    break;
                                default:
                                    System.out.println("Opción no valida, vuelva a intentarlo: ");
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("solo puede elegir numeros");
                        }
                    }
                }
            }
            index++;
        }
        if(!condicion){
            throw new IllegalArgumentException("Carroceria no encontrada");
        }
    }

    public static void eliminar(Carroceria objeto) throws Exception {
        getCarrocerias();
        for(Carroceria b : listaCarrocerias){
            if(b.tipo.equals(objeto.tipo)){
                listaCarrocerias.remove(b);
                guardar();
                break;
            }
        }
        throw new Exception("Carroceria no existe!");
    }

    public static ArrayList<Carroceria> listarTodo() throws FileNotFoundException {
        getCarrocerias();
        return listaCarrocerias;
    }

    public static Integer contar() throws FileNotFoundException {
        return listarTodo().size();
    }

    public static void getCarrocerias() throws FileNotFoundException {
        Gson gson = new Gson();
        Type CarroceriaListType = new TypeToken<ArrayList<Carroceria>>(){}.getType();
        FileReader lector = new FileReader("databaseJson/Carrocerias.json");
        listaCarrocerias = gson.fromJson(lector, CarroceriaListType);
    }

    public static void guardar(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaCarrocerias);
        Datos.escribirArchivo("databaseJson/Carrocerias.json", json);
    }


}

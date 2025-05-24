package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.Modelo;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloCRUD {
    public static void main(String[] args) {
        System.out.println("Hello ");
    }

    public static ArrayList<Modelo> listaModelos = new ArrayList<>();

    public static void agregar(Modelo modelo) throws Exception{
        for(Modelo model: listarTodo()){
            if(model.TipoModelo.equals(modelo.TipoModelo)){
                throw new Exception("Ya existe un modelo con ese tipo");
            }
        }
        listaModelos.add(modelo);
        guardar();
    }

    public static Modelo buscar(String tipoModelo) throws Exception{
        for(Modelo model: listarTodo()){
            if(model.TipoModelo.equals(tipoModelo)){
                return model;
            }
        }
        throw new Exception("Modelo no existe");
    }

    public static void editar(Modelo modelo) throws Exception{
        for(Modelo model: listarTodo()){
            if(model.TipoModelo.equals(modelo.TipoModelo)){
                System.out.println("Editar modelo");
                System.out.println("1) nombre " + model.TipoModelo);
                Scanner entrada = new Scanner(System.in);
                String opcion = entrada.nextLine();
                if(opcion.equals("1")){
                    String nuevoNombre = entrada.nextLine();
                    model.TipoModelo = nuevoNombre;
                    guardar();
                    return;
                }else{
                    throw new Exception("Opcion no valida");
                }
            }
        }
        throw new Exception("Modelo no existe");

    }

    public static void eliminar(String tipoModelo) throws Exception{

        for(Modelo model: listarTodo()){
            if(model.TipoModelo.equals(tipoModelo)){
                listaModelos.remove(model);
                guardar();
                return;
            }
        }
        throw new Exception("Modelo no existe");
    }

    public static ArrayList<Modelo> listarTodo() throws Exception{
        cargarDatos();
        return listaModelos;
    }

    public static Integer contar() throws Exception{
        return listarTodo().size();
    }

    public static void cargarDatos() throws Exception{
        try(FileReader lector = new FileReader("databaseJson/Modelos.json");) {
            Gson gson = new Gson();
            Type modeletoTypeList = new TypeToken<ArrayList<Modelo>>() {
            }.getType();
            listaModelos = gson.fromJson(lector, modeletoTypeList);
        }catch (Exception e){
            throw new Exception("Error al cargar datos: "+e.getMessage());
        }
    }

    public static void guardar(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaModelos);
        Datos.escribirArchivo("databaseJson/Modelos.json", json);
    }
}

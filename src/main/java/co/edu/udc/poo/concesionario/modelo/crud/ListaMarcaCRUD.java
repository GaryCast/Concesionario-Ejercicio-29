package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListaMarcaCRUD {
    public static void main(String[] args) throws Exception {
        for(String marca: getListaDeMarcas()){
            System.out.println("marca: "+marca+"");
        }
    }

    public static ArrayList<String> listaDeMarcas = new ArrayList<>();

    public static void agregarMarca(String marca) throws Exception {
        boolean encontrado = false;
        for(String m: listaDeMarcas){
            if(m.equals(marca)){
                encontrado = true;
                break;
            }
        }
        if(encontrado){
            throw new Exception("Marca ya existe");
        }else{
            listaDeMarcas.add(marca);
            guardarMarcas();
        }
    }

    public static void eliminar(String marca) throws Exception {
        boolean encontrado = false;
        int index=0;
        for(String m: listaDeMarcas){
            if(m.equals(marca)){
                encontrado = true;
                index = listaDeMarcas.indexOf(m);
                listaDeMarcas.remove(index);
                guardarMarcas();
                break;
            }
        }
        if(!encontrado){
            throw new Exception("Marca no existe");
        }
    }

    public static ArrayList<String> getListaDeMarcas() throws Exception {
        cargarMarcas();
        return listaDeMarcas;
    }

    public static void cargarMarcas()throws  Exception{
        try(FileReader lector = new FileReader("databaseJson/ListaMarcas.json")){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            listaDeMarcas = gson.fromJson(lector, listType);
        }catch (FileNotFoundException e){
            throw e;
        }

    }

    public static void guardarMarcas(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaDeMarcas);
        Datos.escribirArchivo("databaseJson/ListaMarcas.json", json);
    }
}

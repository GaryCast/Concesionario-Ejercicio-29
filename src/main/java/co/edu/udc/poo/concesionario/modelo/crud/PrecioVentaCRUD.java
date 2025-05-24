package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.PrecioVenta;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrecioVentaCRUD {

    public static void main(String[] args) throws Exception {
        /*PrecioVenta encontrada = buscar("0");

        System.out.println("Precio extra: "+encontrada.precioExtras);
        System.out.println("Precio base: "+encontrada.precioBase);*/
        PrecioVenta venta = buscar("0");

    }

    public static ArrayList<PrecioVenta>listaPrecioVenta = new ArrayList<>();

    public static void agregar(PrecioVenta precioVenta) throws Exception{
        if (listaPrecioVenta == null) {
            listaPrecioVenta = new ArrayList<>();
        }
        for(PrecioVenta preci: listarTodo()){
            if(preci.id == precioVenta.id){
                throw new Exception("Ya existe un precio con ese id");
            }
        }
        listaPrecioVenta.add(precioVenta);
        guardar();
    }

    public static PrecioVenta buscar(String id) throws Exception{
        for(PrecioVenta precio: listarTodo()){
            if(precio.id == Integer.parseInt(id)){
                return precio;
            }
        }
        throw new Exception("No existe un precio con ese id");
    }

    public static void eliminar(String id) throws Exception{
        boolean existe = false;
        for(PrecioVenta precio: listarTodo()){
            if(precio.id == Integer.parseInt(id)){
                listaPrecioVenta.remove(precio);
                guardar();
                existe = true;
                break;
            }
        }
        if(!existe){
            throw new Exception("No existe un precio con ese id");
        }
    }

    public static ArrayList<PrecioVenta> listarTodo(){
        try {
            cargarDatos();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listaPrecioVenta;
    }

    public static Integer contar() throws FileNotFoundException {
        return listarTodo().size();
    }

    public static void guardar() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaPrecioVenta);
        Datos.escribirArchivo("databaseJson/PrecioVenta.json", json);
    }

    public static void cargarDatos() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader lector = new FileReader("databaseJson/PrecioVenta.json");
        Type precioTypeList = new TypeToken<ArrayList<PrecioVenta>>(){}.getType();
        listaPrecioVenta = gson.fromJson(lector, precioTypeList);
    }
}

package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.Ventas;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class VentasCRUD {

    public static ArrayList<Ventas> listaVentas = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        agregar(new Ventas());
        System.out.println("Ventas existentes: "+contar());
    }

    public static void agregar(Ventas ventas) throws Exception{
       cargarDatos();
        for(Ventas venta: listaVentas){
            if(venta.registroVenta.equals(ventas.registroVenta)){
                throw new Exception("Ya existe una venta con ese registro");
            }
        }
        listaVentas.add(ventas);
        guardar();
    }

    public static Ventas buscar(String registroVenta) throws Exception{
        cargarDatos();
        for(Ventas venta: listaVentas){
            if(venta.registroVenta.equals(registroVenta)){
                return venta;
            }
        }
        throw new Exception("No existe una venta con ese registro");
    }

    public static void eliminar(String registroVenta) throws Exception{
        cargarDatos();
        for(Ventas venta: listaVentas){
            if(venta.registroVenta.equals(registroVenta)){
                listaVentas.remove(venta);
                guardar();
                return;
            }
        }
        throw new Exception("No existe una venta con ese registro");
    }

    public static ArrayList<Ventas> listarTodo(){
        try {
            cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaVentas;
    }

    public static Integer contar() throws Exception{
        return listarTodo().size();
    }

    public static void guardar() throws Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaVentas);
        Datos.escribirArchivo("databaseJson/Ventas.json", json);
    }

    public static void cargarDatos() throws Exception{
        Gson gson = new Gson();
        File archivo = new File("databaseJson/Ventas.json");

        if (!archivo.exists()) {
            listaVentas = new ArrayList<>();
            return;
        }
        FileReader lector = new FileReader(archivo);
        Type ventasTypeList = new TypeToken<ArrayList<Ventas>>(){}.getType();
        listaVentas = gson.fromJson(lector, ventasTypeList);
    }
}

package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.Bodega;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class BodegaCRUD {
    public static ArrayList<Bodega> listaBodegas = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        try{
            Bodega encontrada = buscar("ternera");
            editar( encontrada );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void agregar(Bodega objeto) throws Exception {
        getBodegas();
        for (Bodega b : listaBodegas) {
            if(b.nombre.equals(objeto.nombre)){
                throw new Exception("Bodega ya existe");
            }
        }
        listaBodegas.add(objeto);
        guardar();
    }

    public static Bodega buscar(String nombre) throws FileNotFoundException {
        getBodegas();
        for(Bodega b : listaBodegas){
            if(b.nombre.equals(nombre)){
                return b;
            }
        }
        throw new IllegalArgumentException("Bodega no encontrada");
    }

    public static void editar(Bodega objeto) throws FileNotFoundException {
        getBodegas();
        Scanner entrada = new Scanner(System.in);

        Bodega encontrada = objeto;
        if(encontrada != null){
            System.out.println("Nombre actual: " + encontrada.nombre);
            System.out.println("Nuevo nombre: ");
            String nombre = entrada.nextLine();
            System.out.println("Ubicación actual: "+encontrada.ubicacion);
            System.out.println("Nueva ubicación: ");
            String ubicacion = entrada.nextLine();
            objeto.nombre = nombre;
            objeto.ubicacion = ubicacion;
            int index = 0;
            int i=0;
            for(Bodega b : listaBodegas){
                if(b.nombre.equals(encontrada.nombre)){
                    i = index;
                    break;
                }
                index++;
            }
            Bodega b = listaBodegas.get(i);
            b.nombre = nombre;
            b.ubicacion = ubicacion;
            b.VehiculosDisponiblesInSitu = objeto.VehiculosDisponiblesInSitu;
            listaBodegas.set(i, objeto);
            guardar();
        }
    }

    public static void eliminar(Bodega objeto) throws Exception {
        getBodegas();
        boolean encontrado = false;
        for(Bodega b : listaBodegas){
            if(b.nombre.equals(objeto.nombre)){
                listaBodegas.remove(b);
                encontrado = true;
                break;
            }
        }
        if(!encontrado){
            throw new Exception("Bodega no existe");
        }else{
            guardar();
        }
    }

    public ArrayList<Bodega> listarTodo() throws FileNotFoundException {
        getBodegas();
        return listaBodegas;
    }

    public static Integer contar() throws FileNotFoundException {
        getBodegas();

        Integer total= listaBodegas.size();
        return total;
    }

    public static void guardar(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaBodegas);
        Datos.escribirArchivo("databaseJson/Bodegas.json", json);
    }

    public static void getBodegas() throws FileNotFoundException {
        Gson gson = new Gson();
        Type BodegaListType = new TypeToken<ArrayList<Bodega>>(){}.getType();
        FileReader lector = new FileReader("databaseJson/Bodegas.json");
        listaBodegas = gson.fromJson(lector, BodegaListType);
    }

}

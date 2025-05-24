package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.MetodoPago;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class MetodoPagoCRUD {
    public static ArrayList<MetodoPago> listaMetodos = new ArrayList<>();

    public static void main(String[] args) throws Exception{
       editar(buscar(2));
    }

    public static void agregar(MetodoPago metodo) throws Exception {
        cargarMetodos();
        if (listaMetodos == null) {
            listaMetodos = new ArrayList<>();
        }
        boolean encontrado = false;
        for(MetodoPago met: listaMetodos){
            if(met.id == metodo.id){
                encontrado = true;
                break;
            }
        }
        if(encontrado){
            throw new Exception("Metodo de pago ya existe");
        }
        listaMetodos.add(metodo);
        guardar();
    }

    public static MetodoPago buscar(int id) throws Exception{
        boolean encontrado = false;
        for(MetodoPago met: listarTodo()){
            if(met.id == id){
                encontrado = true;
                return met;
            }
        }
        throw new Exception("Metodo no encontrado");
    }

    public static void editar(MetodoPago metodo) throws Exception {
        Scanner entrada = new Scanner(System.in);
        boolean encontrado = false;
        int index=0;
        for(MetodoPago metodo1 : listarTodo()){
            if (metodo1.id == metodo.id){
                encontrado = true;
                index = listaMetodos.indexOf(metodo1);
                break;
            }
        }

        if(!encontrado){
            throw new Exception("Metodo no existe");
        }

        System.out.println("Editar metodo de pago");
        System.out.println("1) pago contado: "+metodo.contado.longValue());
        System.out.println("2) pago a credito: "+metodo.credito.longValue());
        System.out.println("3) tipo de pago: "+ metodo.tipoDePago);
        String opcion = entrada.nextLine();
        switch (opcion){
            case "1":
                System.out.println("Pago corregido: ");
                double contado = Double.parseDouble(entrada.nextLine());
                metodo.contado = contado;
                listaMetodos.set(index,metodo);
                guardar();
                break;
            case "2":
                System.out.println("Pago credito corregido: ");
                double credito = Double.parseDouble(entrada.nextLine());
                metodo.credito = credito;
                listaMetodos.set(index,metodo);
                guardar();
                break;
            case "3":
                System.out.println("Tipo de pago corregido: ");
                String tipoDePago = entrada.nextLine();
                metodo.tipoDePago = tipoDePago;
                listaMetodos.set(index,metodo);
                guardar();
                break;
            default:
                throw new Exception("Opcion no valida");
        }

    }

    public static void eliminar(MetodoPago metodoPago) throws Exception {
        throw new Exception("No puedes eliminar el metodo de pago");
    }

    public static ArrayList<MetodoPago> listarTodo() throws Exception {
        cargarMetodos();
        return listaMetodos;
    }

    public static Integer contar() throws Exception {
        return listarTodo().size();
    }

    public static void cargarMetodos() throws Exception{
        try(FileReader lector = new FileReader("databaseJson/MetodosDePago.json");){
            Gson gson = new Gson();
            Type metodoListType = new TypeToken<ArrayList<MetodoPago>>() {
            }.getType();
            listaMetodos = gson.fromJson(lector, metodoListType);
        }catch (FileNotFoundException e){
            listaMetodos = new ArrayList<>();
        }catch (Exception e){
            throw new Exception("Error al cargar metodos: "+e.getMessage());
        }
    }

    public static void guardar(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaMetodos);
        Datos.escribirArchivo("databaseJson/MetodosDePago.json",json);
    }
}

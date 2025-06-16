package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.Cliente;
import co.edu.udc.poo.concesionario.modelo.entidades.Satisfaccion;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteCRUD {

    public static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Cliente encontrado = buscar("1001973042");
        System.out.println("Cliente encontrado: " + encontrado.nombre);
    }

    public static void agregar(Cliente objeto) throws Exception {
        cargarDatos();
        for (Cliente b : clientes) {
            if (b.cedula.equals(objeto.cedula)) {
                throw new Exception("Cliente ya existe");
            }
        }
        clientes.add(objeto);
        guardar();
    }

    public static Cliente buscar(String cedula) throws Exception {
        cargarDatos();
        int cedulaGood = Integer.parseInt(cedula);
        Boolean condicion = cedulaGood != 0;
        if (!condicion) {
            throw new IllegalArgumentException("Cedula no valida, solo puede ingresar numeros");
        }
        for (Cliente b : clientes) {
            if (b.cedula.equals(cedula)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Cliente no encontrado");
    }

    public static void editar(Cliente objeto) throws Exception {
        cargarDatos();
        for(Cliente b: clientes){
            if(b.cedula.equals(objeto.cedula)){
                b.nombre = objeto.nombre;
                b.ciudad = objeto.ciudad;
                b.direccion = objeto.direccion;
                b.contactoInicial = objeto.contactoInicial;
                b.correo = objeto.correo;
                b.telefono = objeto.telefono;

            }
        }
    }

    public static void eliminar(String cedula) throws Exception {
        cargarDatos();
        Boolean condicion = false;
        for (Cliente b : clientes) {
            if (b.cedula.equals(cedula)) {
                condicion = true;
                clientes.remove(b);
                guardar();
                break;
            }
        }
        if (!condicion) {
            throw new Exception("el cliente no existe");
        }
    }

    public static ArrayList<Cliente> listarTodo() throws FileNotFoundException {
        cargarDatos();
        return clientes;
    }

    public static Integer contar() throws FileNotFoundException {
        return listarTodo().size();
    }

    public static void cargarDatos() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader lector = new FileReader("databaseJson/Clientes.json");
        Type ClientesTypeList = new TypeToken<ArrayList<Cliente>>() {
        }.getType();
        clientes = gson.fromJson(lector, ClientesTypeList);
    }

    public static void guardar() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clientes);
        Datos.escribirArchivo("databaseJson/Clientes.json", json);
    }
}

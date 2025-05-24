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
        eliminar("123456789");
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
        int cedulaGood = Integer.parseInt(cedula);
        Boolean condicion = cedulaGood != 0;
        if (!condicion) {
            throw new IllegalArgumentException("Cedula no valida, solo puede ingresar numeros");
        }
        cargarDatos();
        for (Cliente b : clientes) {
            if (b.cedula.equals(cedula)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Cliente no encontrado");
    }

    public static void editar(Cliente objeto) throws Exception {
        cargarDatos();
        Scanner entrada = new Scanner(System.in);
        int index;
        for (Cliente b : clientes) {
            if (b.cedula.equals(objeto.cedula)) {
                index = clientes.indexOf(b);
                System.out.println("que quieres editar");

                System.out.println("1)Nombre: " + objeto.nombre);
                System.out.println("2)Cédula: " + objeto.cedula);
                System.out.println("3)Departamento: " + objeto.departamento);
                System.out.println("4)Ciudad: " + objeto.ciudad);
                System.out.println("5)Dirección: " + objeto.direccion);
                System.out.println("6)Teléfono: " + objeto.telefono);
                System.out.println("7)Correo: " + objeto.correo);
                System.out.println("8)Referido Por: " + objeto.referidoPor);
                System.out.println("9)Teléfono del Referido: " + objeto.referidoTel);
                System.out.println("10)Nivel de Satisfacción: " + objeto.nivelSatisfaccion);
                int opcion = Integer.parseInt(System.console().readLine());
                switch (opcion) {
                    case 1:
                        System.out.println("nuevo nombre: ");
                        String nombre = entrada.nextLine();
                        objeto.nombre = nombre;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 2:
                        System.out.println("nueva cedula:");
                        String cedula = entrada.nextLine();
                        for (Cliente b1 : clientes) {
                            if (b1.cedula.equals(cedula)) {
                                throw new Exception("cedula pertenece a otro cliente");
                            }
                        }
                        objeto.cedula = cedula;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 3:
                        System.out.println("nuevo departamento: ");
                        String departamento = entrada.nextLine();
                        objeto.departamento = departamento;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 4:
                        System.out.println("nueva ciudad: ");
                        String ciudad = entrada.nextLine();
                        objeto.ciudad = ciudad;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 5:
                        System.out.println("nueva direccion: ");
                        String direccion = entrada.nextLine();
                        objeto.direccion = direccion;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 6:
                        System.out.println("nuevo telefono: ");
                        String telefono = entrada.nextLine();
                        objeto.telefono = telefono;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 7:
                        System.out.println("nuevo correo: ");
                        String correo = entrada.nextLine();
                        objeto.correo = correo;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 8:
                        System.out.println("nuevo referido por: ");
                        String referidoPor = entrada.nextLine();
                        objeto.referidoPor = referidoPor;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 9:
                        System.out.println("nuevo telefono del referido: ");
                        String referidoTel = entrada.nextLine();
                        objeto.referidoTel = referidoTel;
                        clientes.set(index, objeto);
                        guardar();
                        break;
                    case 10:
                        System.out.println("Nuevo nivel de satisfaccion");
                        int satisfaccion = 1;
                        for (Satisfaccion s : Satisfaccion.values()) {
                            System.out.println(satisfaccion + ") " + s.toString());
                            satisfaccion++;
                        }
                        satisfaccion = Integer.parseInt(entrada.nextLine());
                        objeto.nivelSatisfaccion = satisfaccion;
                        clientes.set(index, objeto);
                        guardar();
                        break;

                }
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

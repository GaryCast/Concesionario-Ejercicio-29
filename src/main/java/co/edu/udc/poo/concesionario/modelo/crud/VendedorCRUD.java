package co.edu.udc.poo.concesionario.modelo.crud;


import co.edu.udc.poo.concesionario.modelo.entidades.Vendedor;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class VendedorCRUD {
    public ArrayList<Vendedor>listaVendedores = new ArrayList<>();

    public void agregar(Vendedor vendedor) throws Exception{
        cargarDatos();
        boolean existe = false;
            for(Vendedor vendedor1: listaVendedores){
                if (vendedor1.nif.equals(vendedor.nif)){
                    existe = true;
                }
            }
            if (existe){
                throw new Exception("Ya existe un vendedor con ese nif");
            }else{
                cargarDatos();
                listaVendedores.add(vendedor);
                guardarDatos();
            }

    }
    
    public Vendedor buscar(String nif) throws Exception{
        cargarDatos();
        for(Vendedor vendedor1: listaVendedores){
            if (vendedor1.nif.equals(nif)){
                return vendedor1;
            }
        }
        throw new Exception("No existe un vendedor con ese nif");
    }

    public void editar(Vendedor vendedor) throws Exception{
        cargarDatos();
        for(Vendedor vende: listaVendedores){
            if(vende.nif.equals(vendedor.nif)){
                Scanner entrada = new Scanner(System.in);
                System.out.println("editar vendedor");
                System.out.println("1) nombre " + vende.nombre);

                System.out.println("Seleccione el atributo a editar:");
                System.out.println("1) Nombre");
                System.out.println("2) Sexo");
                System.out.println("3) Edad");
                System.out.println("4) Dirección");
                System.out.println("5) Teléfono");
                System.out.println("6) Correo");
                System.out.println("7) Ventas Realizadas");

                int opcion = Integer.parseInt(entrada.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre: ");
                        vende.nombre = entrada.nextLine();
                        listaVendedores.set(listaVendedores.indexOf(vende), vende);
                        guardarDatos();
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo sexo: ");
                        vende.sexo = entrada.nextLine();
                        listaVendedores.set(listaVendedores.indexOf(vende), vende);
                        guardarDatos();
                        break;
                    case 3:
                        System.out.println("Ingrese la nueva edad: ");
                        vende.edad = entrada.nextLine();
                        break;
                    case 4:
                        System.out.println("Ingrese la nueva dirección: ");
                        vende.direccion = entrada.nextLine();
                        listaVendedores.set(listaVendedores.indexOf(vende), vende);
                        guardarDatos();
                        break;
                    case 5:
                        System.out.println("Ingrese el nuevo teléfono: ");
                        vende.telefono = entrada.nextLine();
                        listaVendedores.set(listaVendedores.indexOf(vende), vende);
                        guardarDatos();
                        break;
                    case 6:
                        System.out.println("Ingrese el nuevo correo: ");
                        vende.correo = entrada.nextLine();
                        listaVendedores.set(listaVendedores.indexOf(vende), vende);
                        guardarDatos();
                        break;
                    case 7:
                        System.out.println("Ingrese el nuevo número de ventas realizadas: ");
                        vende.ventasRealizadas = Integer.parseInt(entrada.nextLine());
                        listaVendedores.set(listaVendedores.indexOf(vende), vende);
                        guardarDatos();
                        break;
                    default:
                        System.out.println("Opción no válida");
                        return;
                }

                guardarDatos();
                System.out.println("¡Vendedor actualizado con éxito!");
                return;
            }
        }
        throw new Exception("No existe un vendedor con ese nif");
    }
    
    public void eliminar(Vendedor vendedor) throws Exception{
        cargarDatos();
        boolean existe = false;
        for (Vendedor vendedor1: listaVendedores){
            if (vendedor1.nif.equals(vendedor.nif)){
                existe = true;
                listaVendedores.remove(vendedor1);
                guardarDatos();
                break;
            }
        }
        if (!existe){
            throw new Exception("No existe un vendedor con ese nif");
        }
    }
    
    public ArrayList<Vendedor>listarTodo() throws Exception{
        cargarDatos();
        return listaVendedores;
    }

    public Integer contar() throws Exception{
        return listarTodo().size();
    }
    
    public void cargarDatos() throws Exception{
        Gson gson = new Gson();
        File archivo = new File("databaseJson/Ventas.json");

        if (!archivo.exists()) {
            listaVendedores = new ArrayList<>();
            return;
        }
        FileReader lector = new FileReader(archivo);
        Type vendedorTypeList = new TypeToken<ArrayList<Vendedor>>(){}.getType();
        listaVendedores = gson.fromJson(lector, vendedorTypeList);
    }
    public void guardarDatos() throws Exception{
        Gson gson = new Gson();
        String json = gson.toJson(listaVendedores);
        Datos.escribirArchivo("databaseJson/Vendedores.json", json);
    }


}

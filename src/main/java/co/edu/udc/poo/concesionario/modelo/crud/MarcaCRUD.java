package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.Carroceria;
import co.edu.udc.poo.concesionario.modelo.entidades.Marca;
import co.edu.udc.poo.concesionario.modelo.entidades.Modelo;
import co.edu.udc.poo.concesionario.modelo.entidades.TipoCarroceria;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class MarcaCRUD {
    private static ArrayList<Marca>listaMarcas = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        try {
            Marca encontrada = buscar("Rexai");
            editar( encontrada );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void agregar(Marca objeto) throws Exception{
        getMarcas();
        for (Marca b : listaMarcas){
            if(b.marca.equals(objeto.marca)){
                throw new Exception("La marca ya existe");
            }
        }
        listaMarcas.add(objeto);
        guardar();
    }

    private static void getMarcas() throws FileNotFoundException{

        try(FileReader lector = new FileReader("databaseJson/Marca.json");){
            Gson gson = new Gson();
            Type marcaListType = new TypeToken<ArrayList<Marca>>(){}.getType();
            listaMarcas = gson.fromJson(lector, marcaListType);
        }catch (FileNotFoundException e){
            listaMarcas = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Error al listar marcas: "+e.getMessage());
        }
    }

    private static void guardar() throws FileNotFoundException{
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(listaMarcas);
            Datos.escribirArchivo("databaseJson/Marca.json", json);
        }catch (Exception e){
            System.out.println("Error al guardar marcas: "+e.getMessage());
        }

    }

    public static Marca buscar(String marca) throws FileNotFoundException {
        try {
            getMarcas();
            for (Marca b : listaMarcas) {
                if (b.marca.equals(marca)) {
                    return b;
                }
            }
            throw new IllegalArgumentException();
        }catch (Exception e){
            throw new IllegalArgumentException("Error al buscar marca: "+e.getMessage());
        }
    }

    public static void editar(Marca objeto) throws Exception {
        getMarcas();
        int index = 0;
        for(Marca b : listaMarcas){
            if(b.marca.equals(objeto.marca)){
                break;
            }
            index++;
        }
        Scanner entrada = new Scanner(System.in);
        Marca encontrada = objeto;
        if (encontrada != null){
            boolean condicion = true;
            while (condicion){
                System.out.println("1) nombre de la marca");
                System.out.println("2) lista de carroceria");
                System.out.println("elija que quiere editar: 1/2");
                String opcion1 = entrada.nextLine();
                switch (opcion1){
                    case "1":
                        System.out.println("escriba el nuevo nombre de la marca");
                        String nuevoNombre = entrada.nextLine();
                        encontrada.marca = nuevoNombre;
                        listaMarcas.set(index, encontrada);
                        guardar();
                        condicion = false;
                        break;
                    case "2":
                        System.out.println("1) Agregar carroceria");
                        System.out.println("2) Eliminar carroceria");
                        String opcion2 = entrada.nextLine();
                        if (opcion2.equals("1")){
                            for (TipoCarroceria tipo : TipoCarroceria.values()){
                                System.out.println(tipo.ordinal()+1+") "+tipo.toString());
                            }
                            TipoCarroceria tipoCarroceria = TipoCarroceria.values()[Integer.parseInt(entrada.nextLine())];
                            for(Carroceria carroceria : encontrada.Carroceria){
                                if(carroceria.tipo.equals(tipoCarroceria)){
                                    throw new IllegalArgumentException("La carroceria ya existe");
                                }
                            }
                            Carroceria nuevaCarroceria = new Carroceria(tipoCarroceria);
                            encontrada.Carroceria.add(nuevaCarroceria);
                            listaMarcas.set(index, encontrada);
                            guardar();
                        }else if (opcion2.equals("2")){
                            System.out.println("Carrocerias: ");
                            for (Carroceria carroceria : encontrada.Carroceria){
                                System.out.println(carroceria.tipo.ordinal()+1+") "+carroceria.tipo.toString());
                            }
                            System.out.println("Elija la carroceria que desea eliminar: ");
                            String opcion3 = entrada.nextLine();
                            int i = Integer.parseInt(opcion3);
                            encontrada.Carroceria.remove(i-1);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void eliminar(Marca objeto) throws FileNotFoundException {
        getMarcas();
        for(Marca b : listaMarcas){
            if(b.marca.equals(objeto.marca)){
                listaMarcas.remove(b);
                guardar();
                break;
            }
        }
        throw new IllegalArgumentException("La marca no existe");
    }

    public static ArrayList<Marca> listarTodo() throws Exception{
        try {
            getMarcas();
            return listaMarcas;
        }catch (FileNotFoundException e){
            throw new Exception("Error ListarTodo: "+e.getMessage());
        }
    }

    public static Integer contar() throws Exception {
       return listarTodo().size();
    }
}

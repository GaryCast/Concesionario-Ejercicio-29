package co.edu.udc.poo.concesionario.util;
import java.io.*;

public class Datos {

    public static void crearArchivo(String nombreArchivo){
        try{
            File archivo = new File(nombreArchivo);
            PrintWriter pw = new PrintWriter(archivo);
            pw.close();
            System.console().flush();
            System.out.println("Se creó el archivo "+nombreArchivo);
        }catch (FileNotFoundException e){
            System.out.println("Error al crear el archivo: "+ e.getMessage());
        }
    }

    public static void escribirArchivo(String nombreArchivo, String texto){
        try {
            File archivo = new File(nombreArchivo);
            PrintWriter pw = new PrintWriter(archivo);
            pw.println(texto);
            pw.close();
            System.console().flush();
            System.out.println("Se escribio en el archivo "+nombreArchivo);
        }catch (FileNotFoundException e){
            System.out.println("Error al escribir en el archivo: "+ e.getMessage());
        }
    }

    public static void leerArchivo(String nombreArchivo){
        try{
            File archivo = new File(nombreArchivo);
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            String contenido = "";
        }catch (FileNotFoundException e){
            System.out.println("Error al leer el archivo: "+ e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

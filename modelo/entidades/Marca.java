package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Marca {

    public String marca;
    public List<String> Modelos;
    public List<Carroceria> Carroceria = new ArrayList<>();

    public Marca() {
        marca = "RENAULT";
        Modelos = new ArrayList<>();
        Carroceria = new ArrayList<>();

    }

    public Marca(String marca) {
        this.marca = marca;
        Modelos = new ArrayList<>();
        Carroceria = new ArrayList<>();
    }


}




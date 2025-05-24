package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Marca {

    public String marca;
    public List<Carroceria> Carroceria = new ArrayList<>();

    public Marca() {
        marca = "RENAULT";
        Carroceria = new ArrayList<>();

    }

    public Marca(String marca) {
        this.marca = marca;
        Carroceria = new ArrayList<>();
    }


}




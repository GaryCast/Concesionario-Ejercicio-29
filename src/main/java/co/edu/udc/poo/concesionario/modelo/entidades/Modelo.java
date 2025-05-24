package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Modelo {
    public String TipoModelo;
    public List<Caracteristicas> Sencillo;
    public List<Caracteristicas> Premium ;
    
    public Modelo() {
        TipoModelo = "logan";
        Sencillo = new ArrayList<>();
        Premium = new ArrayList<>();
    }

    public Modelo(String tipoModelo) {
        TipoModelo = tipoModelo;
        Sencillo = new ArrayList<>();
        Premium = new ArrayList<>();
    }

    public void setTipoModelo(String tipoModelo){
        TipoModelo = tipoModelo;
    }
    
}



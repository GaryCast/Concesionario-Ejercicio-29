package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Carroceria {
    public TipoCarroceria tipo;
    public List<Modelo> modelos;


    public Carroceria() {
        tipo = TipoCarroceria.SEDAN;
        modelos = new ArrayList<>();
    }


    public Carroceria(TipoCarroceria tipo) {
        this.tipo = tipo;
        modelos = new ArrayList<>();
    }

    public TipoCarroceria getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarroceria tipo) {
        this.tipo = tipo;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}


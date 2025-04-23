package co.edu.udc.poo.concesionario.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Carroceria {
    public TipoCarroceria tipo;
    public List<Modelo> tiposdeModelos;


    public Carroceria() {
        tipo = TipoCarroceria.SEDAN;
        tiposdeModelos = new ArrayList<>();
    }


    public Carroceria(TipoCarroceria tipo) {
        this.tipo = tipo;
        tiposdeModelos = new ArrayList<>();
    }


}


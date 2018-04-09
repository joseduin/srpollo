package com.patricia.srpollo.modelo;

import java.io.Serializable;

/**
 * Created by Jose on 5/3/2018.
 */

@SuppressWarnings("serial")
public class Almacen implements Serializable {

    private int id;
    private String nombre;

    public Almacen(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Almacen() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/24/2018.
 */

public class Lista {
    long id;
    String nombre;

    public Lista(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

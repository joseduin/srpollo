package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 5/3/2018.
 */

public class Cargo {

    private int id;
    private String descripcion;

    public Cargo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Cargo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

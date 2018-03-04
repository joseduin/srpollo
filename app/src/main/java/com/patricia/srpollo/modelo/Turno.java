package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 24/2/2018.
 */

public class Turno {

    int id;
    String descripcion;

    public Turno(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    public  Turno(){}

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

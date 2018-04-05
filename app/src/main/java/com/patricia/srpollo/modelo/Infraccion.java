package com.patricia.srpollo.modelo;

/**
 * Created by SPF1 on 4/5/2018.
 */

public class Infraccion {

    private int id;
    private String descripcion;

    public Infraccion(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Infraccion() {}

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

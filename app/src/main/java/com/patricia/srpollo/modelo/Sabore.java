package com.patricia.srpollo.modelo;

import java.io.Serializable;

/**
 * Created by Jose on 4/3/2018.
 */

@SuppressWarnings("serial")
public class Sabore implements Serializable {

    private int id;
    private String descripcion;
    private Soda soda;

    public Sabore(int id, String descripcion, Soda soda) {
        this.id = id;
        this.descripcion = descripcion;
        this.soda = soda;
    }

    public Sabore() {}

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

    public Soda getSoda() {
        return soda;
    }

    public void setSoda(Soda soda) {
        this.soda = soda;
    }
}

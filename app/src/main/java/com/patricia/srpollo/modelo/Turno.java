package com.patricia.srpollo.modelo;

import java.io.Serializable;

/**
 * Created by Jose on 24/2/2018.
 */

@SuppressWarnings("serial")
public class Turno implements Serializable {

    private int id;
    private String descripcion;
    private String inicio;
    private String fin;

    public Turno(int id, String descripcion, String inicio, String fin) {
        this.id = id;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
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

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}

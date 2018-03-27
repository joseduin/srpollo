package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/25/2018.
 */

public class Responsable {

    String nombre;
    double multa;

    public Responsable(String nombre, double multa) {
        this.nombre = nombre;
        this.multa = multa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
}

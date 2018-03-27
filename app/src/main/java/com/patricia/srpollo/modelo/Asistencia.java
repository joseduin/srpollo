package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/24/2018.
 */

public class Asistencia {

    long id;
    String cargo;
    boolean asistio;

    public Asistencia(long id, String cargo, boolean asistio) {
        this.id = id;
        this.cargo = cargo;
        this.asistio = asistio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}



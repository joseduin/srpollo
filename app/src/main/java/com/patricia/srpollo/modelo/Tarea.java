package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 4/26/2018.
 */

public class Tarea {

    private String tarea;
    private boolean primero;
    private boolean segundo;
    private String estado;

    public Tarea(String tarea, boolean primero, boolean segundo, String estado) {
        this.tarea = tarea;
        this.primero = primero;
        this.segundo = segundo;
        this.estado = estado;
    }

    public Tarea() {}

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public boolean isPrimero() {
        return primero;
    }

    public void setPrimero(boolean primero) {
        this.primero = primero;
    }

    public boolean isSegundo() {
        return segundo;
    }

    public void setSegundo(boolean segundo) {
        this.segundo = segundo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

package com.patricia.srpollo.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jose on 3/3/2018.
 */

@SuppressWarnings("serial")
public class RegistroDiario implements Serializable {

    private int id;
    private double ingreso_efectivo;
    private String fecha;
    private String created_at;
    private int almacen_id;
    private ArrayList <RegistroDiarioItem> items;

    public RegistroDiario(int id, double ingreso_efectivo, String fecha, String created_at, int almacen_id) {
        this.id = id;
        this.ingreso_efectivo = ingreso_efectivo;
        this.fecha = fecha;
        this.created_at = created_at;
        this.almacen_id = almacen_id;
        this.items = new ArrayList<>();
    }

    public RegistroDiario(){}

    public ArrayList<RegistroDiarioItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<RegistroDiarioItem> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIngreso_efectivo() {
        return ingreso_efectivo;
    }

    public void setIngreso_efectivo(double ingreso_efectivo) {
        this.ingreso_efectivo = ingreso_efectivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(int almacen_id) {
        this.almacen_id = almacen_id;
    }
}

package com.patricia.srpollo.modelo;

import java.io.Serializable;

/**
 * Created by Jose on 24/2/2018.
 */

@SuppressWarnings("serial")
public class Producto implements Serializable  {

    int id;
    String descripcion;
    double precio, costo, cantidad_paquete;

    public Producto(int id, double cantidad_paquete, String descripcion, double precio, double costo) {
        this.id = id;
        this.cantidad_paquete = cantidad_paquete;
        this.descripcion = descripcion;
        this.precio = precio;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad_paquete() {
        return cantidad_paquete;
    }

    public void setCantidad_paquete(double cantidad_paquete) {
        this.cantidad_paquete = cantidad_paquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}

package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/25/2018.
 */

public class Faltante {
    String descripcion;
    double cantidad, precio, subTotal;

    public Faltante(String descripcion, double cantidad, double precio, double subTotal) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}

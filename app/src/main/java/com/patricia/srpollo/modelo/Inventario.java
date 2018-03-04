package com.patricia.srpollo.modelo;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by patricia on 1/24/2018.
 */

public class Inventario {

    String producto;
    double minima, inventarioExistente, precio;

    public Inventario(String producto, double minima, double inventarioExistente, double precio) {
        this.producto = producto;
        this.minima = minima;
        this.inventarioExistente = inventarioExistente;
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getMinima() {
        return minima;
    }

    public void setMinima(double minima) {
        this.minima = minima;
    }

    public double getInventarioExistente() {
        return inventarioExistente;
    }

    public void setInventarioExistente(double inventarioExistente) {
        this.inventarioExistente = inventarioExistente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}

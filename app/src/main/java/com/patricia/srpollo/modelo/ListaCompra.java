package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/24/2018.
 */

public class ListaCompra {

    String producto;
    double cantcomprar, cantComprada, precio;

    public ListaCompra(String producto, double cantcomprar, double cantComprada, double precio) {
        this.producto = producto;
        this.cantcomprar = cantcomprar;
        this.cantComprada = cantComprada;
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getCantcomprar() {
        return cantcomprar;
    }

    public void setCantcomprar(double cantcomprar) {
        this.cantcomprar = cantcomprar;
    }

    public double getCantComprada() {
        return cantComprada;
    }

    public void setCantComprada(double cantComprada) {
        this.cantComprada = cantComprada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

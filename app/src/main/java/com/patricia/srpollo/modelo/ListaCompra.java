package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/24/2018.
 */

public class ListaCompra {

    private String producto;
    private double cantcomprar;
    private double cantPaquete;
    private String unidad;

    public ListaCompra(String producto, double cantcomprar, double cantPaquete, String unidad) {
        this.producto = producto;
        this.cantcomprar = cantcomprar;
        this.cantPaquete = cantPaquete;
        this.unidad = unidad;
    }

    public ListaCompra() {}

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

    public double getCantPaquete() {
        return cantPaquete;
    }

    public void setCantPaquete(double cantPaquete) {
        this.cantPaquete = cantPaquete;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}

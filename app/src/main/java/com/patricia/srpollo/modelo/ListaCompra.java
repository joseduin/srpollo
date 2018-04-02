package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/24/2018.
 */

public class ListaCompra {

    private int id;
    private String producto;
    private double cantcomprar;
    private double cantPaquete;
    private String unidad;

    private int idWeb;
    private double cantComprada;
    private double total;
    private double costo;

    public ListaCompra(int idWeb, String producto, double cantcomprar, double cantPaquete, String unidad) {
        this.idWeb = idWeb;
        this.producto = producto;
        this.cantcomprar = cantcomprar;
        this.cantPaquete = cantPaquete;
        this.unidad = unidad;
    }

    public ListaCompra() {}

    public int getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(int idWeb) {
        this.idWeb = idWeb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getCantComprada() {
        return cantComprada;
    }

    public void setCantComprada(double cantComprada) {
        this.cantComprada = cantComprada;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}

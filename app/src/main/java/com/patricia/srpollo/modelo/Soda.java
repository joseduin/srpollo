package com.patricia.srpollo.modelo;

import java.io.Serializable;

/**
 * Created by Jose on 4/3/2018.
 */

@SuppressWarnings("serial")
public class Soda implements Serializable {

    private int id;
    private String descripcion;
    private int cantidad_unidades;
    private double costo;
    private double precio;
    private Categoria categoria;

    public Soda(int id, String descripcion, int cantidad_unidades, double costo, double precio, Categoria categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad_unidades = cantidad_unidades;
        this.costo = costo;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Soda() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad_unidades() {
        return cantidad_unidades;
    }

    public void setCantidad_unidades(int cantidad_unidades) {
        this.cantidad_unidades = cantidad_unidades;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

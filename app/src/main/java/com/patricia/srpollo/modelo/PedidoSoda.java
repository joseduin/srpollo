package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/25/2018.
 */

public class PedidoSoda {

    private int id;
    private String sabore;
    private double cantcomprar;
    private int unidades_por_paquete;
    private String unidad;

    private int idWeb;
    private double cantComprada;
    private double total;
    private double costo;

    public PedidoSoda(int idWeb, String sabore, double cantcomprar, int unidades_por_paquete, String unidad) {
        this.idWeb = idWeb;
        this.sabore = sabore;
        this.cantcomprar = cantcomprar;
        this.unidades_por_paquete = unidades_por_paquete;
        this.unidad = unidad;
    }

    public PedidoSoda() {}

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

    public String getSabore() {
        return sabore;
    }

    public void setSabore(String sabore) {
        this.sabore = sabore;
    }

    public double getCantcomprar() {
        return cantcomprar;
    }

    public void setCantcomprar(double cantcomprar) {
        this.cantcomprar = cantcomprar;
    }

    public double getUnidades_por_paquete() {
        return unidades_por_paquete;
    }

    public void setUnidades_por_paquete(int unidades_por_paquete) {
        this.unidades_por_paquete = unidades_por_paquete;
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

package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 1/4/2018.
 */

public class PedidosSodasRequest {

    private double cantidad_comprada;
    private double total_unidades_compradas;
    private double costo;

    public PedidosSodasRequest(double cantidad_comprada, double total_unidades_compradas, double costo) {
        this.cantidad_comprada = cantidad_comprada;
        this.total_unidades_compradas = total_unidades_compradas;
        this.costo = costo;
    }

    public PedidosSodasRequest() {

    }

    public double getCantidad_comprada() {
        return cantidad_comprada;
    }

    public void setCantidad_comprada(double cantidad_comprada) {
        this.cantidad_comprada = cantidad_comprada;
    }

    public double getTotal_unidades_compradas() {
        return total_unidades_compradas;
    }

    public void setTotal_unidades_compradas(double total_unidades_compradas) {
        this.total_unidades_compradas = total_unidades_compradas;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}

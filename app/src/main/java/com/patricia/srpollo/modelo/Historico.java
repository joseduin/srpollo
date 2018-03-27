package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/24/2018.
 */

public class Historico {

    String producto;
    double gastoEfectivo;
    double saldoEfectivo;
    Lista lista;

    public Historico(String producto, double gastoEfectivo, double saldoEfectivo, Lista lista) {
        this.producto = producto;
        this.gastoEfectivo = gastoEfectivo;
        this.saldoEfectivo = saldoEfectivo;
        this.lista = lista;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getGastoEfectivo() {
        return gastoEfectivo;
    }

    public void setGastoEfectivo(double gastoEfectivo) {
        this.gastoEfectivo = gastoEfectivo;
    }

    public double getSaldoEfectivo() {
        return saldoEfectivo;
    }

    public void setSaldoEfectivo(double saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
}

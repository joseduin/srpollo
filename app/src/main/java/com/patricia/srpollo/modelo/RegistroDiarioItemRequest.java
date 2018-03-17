package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 4/3/2018.
 */

public class RegistroDiarioItemRequest {

    private String producto_id;
    private String sabore_id;
    private double cantidad_ingreso;
    private double gasto_efectivo;
    private double caja_chica;
    private double saldo_caja_chica;

    public RegistroDiarioItemRequest(String producto_id, String sabore_id, double cantidad_ingreso, double gasto_efectivo, double caja_chica, double saldo_caja_chica) {
        this.producto_id = producto_id;
        this.sabore_id = sabore_id;
        this.cantidad_ingreso = cantidad_ingreso;
        this.gasto_efectivo = gasto_efectivo;
        this.caja_chica = caja_chica;
        this.saldo_caja_chica = saldo_caja_chica;
    }

    public RegistroDiarioItemRequest() {}

    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
    }

    public String getSabore_id() {
        return sabore_id;
    }

    public void setSabore_id(String sabore_id) {
        this.sabore_id = sabore_id;
    }

    public double getCantidad_ingreso() {
        return cantidad_ingreso;
    }

    public void setCantidad_ingreso(double cantidad_ingreso) {
        this.cantidad_ingreso = cantidad_ingreso;
    }

    public double getGasto_efectivo() {
        return gasto_efectivo;
    }

    public void setGasto_efectivo(double gasto_efectivo) {
        this.gasto_efectivo = gasto_efectivo;
    }

    public double getCaja_chica() {
        return caja_chica;
    }

    public void setCaja_chica(double caja_chica) {
        this.caja_chica = caja_chica;
    }

    public double getSaldo_caja_chica() {
        return saldo_caja_chica;
    }

    public void setSaldo_caja_chica(double saldo_caja_chica) {
        this.saldo_caja_chica = saldo_caja_chica;
    }
}

package com.patricia.srpollo.modelo;

import java.io.Serializable;

/**
 * Created by Jose on 3/3/2018.
 */

@SuppressWarnings("serial")
public class RegistroDiarioItem implements Serializable {

    private int id;
    private int turno_id;
    private int producto_id;
    private int sabore_id;
    private double cantidad_ingreso;
    private double gasto_efectivo;
    private double caja_chica;
    private double saldo_caja_chica;
    private int registro_diario;
    private String created_at;

    private Sabore sabore;
    private Producto producto;
    private Turno turno;

    public RegistroDiarioItem(int id, int turno_id, int producto_id, int sabore_id, double cantidad_ingreso, double gasto_efectivo, double caja_chica, double saldo_caja_chica, int registro_diario, String created_at) {
        this.id = id;
        this.turno_id = turno_id;
        this.producto_id = producto_id;
        this.sabore_id = sabore_id;
        this.cantidad_ingreso = cantidad_ingreso;
        this.gasto_efectivo = gasto_efectivo;
        this.caja_chica = caja_chica;
        this.saldo_caja_chica = saldo_caja_chica;
        this.registro_diario = registro_diario;
        this.created_at = created_at;
    }
    public RegistroDiarioItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        this.turno_id = turno_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getSabore_id() {
        return sabore_id;
    }

    public void setSabore_id(int sabore_id) {
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

    public int getRegistro_diario() {
        return registro_diario;
    }

    public void setRegistro_diario(int registro_diario) {
        this.registro_diario = registro_diario;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Sabore getSabore() {
        return sabore;
    }

    public void setSabore(Sabore sabore) {
        this.sabore = sabore;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}

package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 4/3/2018.
 */

public class RegistroDiarioRequest {

    private double ingreso_efectivo;
    private int almacen_id;

    public RegistroDiarioRequest(double ingreso_efectivo, int almacen_id) {
        this.ingreso_efectivo = ingreso_efectivo;
        this.almacen_id = almacen_id;
    }
    public RegistroDiarioRequest(){}

    public double getIngreso_efectivo() {
        return ingreso_efectivo;
    }

    public void setIngreso_efectivo(double ingreso_efectivo) {
        this.ingreso_efectivo = ingreso_efectivo;
    }

    public int getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(int almacen_id) {
        this.almacen_id = almacen_id;
    }
}

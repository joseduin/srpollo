package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 7/4/2018.
 */

public class AsistenciaRequest {

    private int trabajador_id;
    private String observacion;

    public AsistenciaRequest(int trabajador_id) {
        this.trabajador_id = trabajador_id;
        this.observacion = "";
    }

    public AsistenciaRequest() {}

    public int getTrabajador_id() {
        return trabajador_id;
    }

    public void setTrabajador_id(int trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

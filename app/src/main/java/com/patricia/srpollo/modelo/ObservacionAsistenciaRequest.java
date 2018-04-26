package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 9/4/2018.
 */

public class ObservacionAsistenciaRequest {

    private int trabajador_id;
    private String reemplazo;
    private int tipo_reemplazo;

    public ObservacionAsistenciaRequest(int trabajador_id, String reemplazo, int tipo_reemplazo) {
        this.trabajador_id = trabajador_id;
        this.reemplazo = reemplazo;
        this.tipo_reemplazo = tipo_reemplazo;
    }

    public ObservacionAsistenciaRequest() {}

    public int getTrabajador_id() {
        return trabajador_id;
    }

    public void setTrabajador_id(int trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    public String getReemplazo() {
        return reemplazo;
    }

    public void setReemplazo(String reemplazo) {
        this.reemplazo = reemplazo;
    }

    public int getTipo_reemplazo() {
        return tipo_reemplazo;
    }

    public void setTipo_reemplazo(int tipo_reemplazo) {
        this.tipo_reemplazo = tipo_reemplazo;
    }
}

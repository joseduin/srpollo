package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 6/4/2018.
 */

public class SancionRequest {

    private int infraccion_id;
    private int trabajador_id;

    public SancionRequest(int infraccion_id, int trabajador_id) {
        this.infraccion_id = infraccion_id;
        this.trabajador_id = trabajador_id;
    }

    public SancionRequest() {}

    public int getInfraccion_id() {
        return infraccion_id;
    }

    public void setInfraccion_id(int infraccion_id) {
        this.infraccion_id = infraccion_id;
    }

    public int getTrabajador_id() {
        return trabajador_id;
    }

    public void setTrabajador_id(int trabajador_id) {
        this.trabajador_id = trabajador_id;
    }
}

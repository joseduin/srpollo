package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 4/3/2018.
 */

public class TrabajadorRequest {

    private String usuario;
    private String clave;

    public TrabajadorRequest(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public TrabajadorRequest() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}

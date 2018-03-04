package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Error;
import com.patricia.srpollo.modelo.RegistroDiario;

/**
 * Created by Jose on 3/3/2018.
 */

public class RegistroDiarioResponse {

    private RegistroDiario registroDiario = new RegistroDiario();
    private Error error = new Error();

    public RegistroDiario getRegistroDiario() {
        return registroDiario;
    }

    public void setRegistroDiario(RegistroDiario registroDiario) {
        this.registroDiario = registroDiario;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

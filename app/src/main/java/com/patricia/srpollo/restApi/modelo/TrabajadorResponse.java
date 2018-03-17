package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Error;
import com.patricia.srpollo.modelo.Trabajador;

import java.util.ArrayList;

/**
 * Created by Jose on 14/3/2018.
 */

public class TrabajadorResponse {

    private Trabajador trabajador;
    private ArrayList<Trabajador> trabajadors;
    private Error error = new Error();

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public ArrayList<Trabajador> getTrabajadors() {
        return trabajadors;
    }

    public void setTrabajadors(ArrayList<Trabajador> trabajadors) {
        this.trabajadors = trabajadors;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

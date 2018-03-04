package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Turno;

import java.util.ArrayList;

/**
 * Created by Jose on 24/2/2018.
 */

public class TurnoResponse {

    ArrayList<Turno> turno = new ArrayList<>();

    public ArrayList<Turno> getTurno() {
        return turno;
    }

    public void setTurno(ArrayList<Turno> turno) {
        this.turno = turno;
    }
}

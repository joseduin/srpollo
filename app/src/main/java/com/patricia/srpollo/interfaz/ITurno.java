package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.modelo.Turno;

import java.util.HashMap;

/**
 * Created by Jose on 24/2/2018.
 */

public interface ITurno {

    public void cargarComboTurno(String[] turnos);
    public void cargarMapTurno(HashMap<String, Turno> map);

}

package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.modelo.Infraccion;
import com.patricia.srpollo.modelo.Trabajador;

import java.util.HashMap;

/**
 * Created by Jose on 24/2/2018.
 */

public interface ITrabajador {

    public void cargarComboTrabajador(String[] lista);
    public void cargarMapTrabajador(HashMap<String, Trabajador> map);

}

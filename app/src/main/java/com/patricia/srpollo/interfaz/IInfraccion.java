package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.modelo.Infraccion;
import com.patricia.srpollo.modelo.Producto;

import java.util.HashMap;

/**
 * Created by Jose on 24/2/2018.
 */

public interface IInfraccion {

    public void cargarComboInfraccion(String[] lista);
    public void cargarMapInfraccion(HashMap<String, Infraccion> map);

}

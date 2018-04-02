package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.modelo.Almacen;
import com.patricia.srpollo.modelo.Producto;

import java.util.HashMap;

/**
 * Created by Jose on 24/2/2018.
 */

public interface IAlmacen {

    public void cargarComboAlmacen(String[] lista);
    public void cargarMapAlmacen(HashMap<String, Almacen> map);

}

package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.modelo.Producto;

import java.util.HashMap;

/**
 * Created by Jose on 24/2/2018.
 */

public interface IProducto {

    public void cargarComboProducto(String[] productos);
    public void cargarMapProductos(HashMap<String, Producto> map);

}

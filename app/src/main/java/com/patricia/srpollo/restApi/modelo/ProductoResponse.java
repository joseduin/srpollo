package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Producto;

import java.util.ArrayList;

/**
 * Created by Jose on 24/2/2018.
 */

public class ProductoResponse  {

    ArrayList<Producto> producto = new ArrayList<>();

    public ArrayList<Producto> getProducto() {
        return producto;
    }

    public void setProducto(ArrayList<Producto> producto) {
        this.producto = producto;
    }

}

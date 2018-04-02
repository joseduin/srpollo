package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Almacen;

import java.util.ArrayList;

/**
 * Created by Jose on 31/3/2018.
 */

public class AlmacenResponse {

    private ArrayList<Almacen> almacens = new ArrayList<>();

    public ArrayList<Almacen> getAlmacens() {
        return almacens;
    }

    public void setAlmacens(ArrayList<Almacen> almacens) {
        this.almacens = almacens;
    }
}

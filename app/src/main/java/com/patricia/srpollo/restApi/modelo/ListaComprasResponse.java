package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;

import java.util.ArrayList;

/**
 * Created by SPF1 on 3/27/2018.
 */

public class ListaComprasResponse {

    private ArrayList<ListaCompra> listaCompra = new ArrayList<>();
    private Configuracion configuracion;

    public ArrayList<ListaCompra> getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(ArrayList<ListaCompra> listaCompra) {
        this.listaCompra = listaCompra;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
}

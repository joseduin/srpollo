package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;

import java.util.ArrayList;

/**
 * Created by SPF1 on 3/27/2018.
 */

public class PedidosSodasResponse {

    private ArrayList<PedidoSoda> pedidoSoda = new ArrayList<>();
    private Configuracion configuracion;

    public ArrayList<PedidoSoda> getPedidoSoda() {
        return pedidoSoda;
    }

    public void setPedidoSoda(ArrayList<PedidoSoda> pedidoSoda) {
        this.pedidoSoda = pedidoSoda;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
}

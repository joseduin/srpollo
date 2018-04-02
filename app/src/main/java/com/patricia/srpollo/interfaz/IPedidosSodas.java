package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.adaptador.AdaptadorPedidoSoda;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;

import java.util.ArrayList;

/**
 * Created by Jose on 29/3/2018.
 */

public interface IPedidosSodas {

    public void generarLayoutVertical();

    public AdaptadorListaCompra crearAdaptador(ArrayList<PedidoSoda> list);

    public void inicializarAdaptadorRV(AdaptadorPedidoSoda adapter);

    public void configuraciones(Configuracion configuracion);

}

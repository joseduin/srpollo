package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;

import java.util.ArrayList;

/**
 * Created by Jose on 29/3/2018.
 */

public interface IListaCompras {

    public void generarLayoutVertical();

    public AdaptadorListaCompra crearAdaptador(ArrayList<ListaCompra> list);

    public void inicializarAdaptadorRV(AdaptadorListaCompra adapter);

    public void configuraciones(Configuracion configuracion);

}

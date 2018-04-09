package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.adaptador.AdaptadorAsistencia;
import com.patricia.srpollo.adaptador.AdaptadorPedidoSoda;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.modelo.Trabajador;

import java.util.ArrayList;

/**
 * Created by Jose on 29/3/2018.
 */

public interface IAsistencia {

    public void generarLayoutVertical();

    public AdaptadorAsistencia crearAdaptador(ArrayList<Trabajador> list);

    public void inicializarAdaptadorRV(AdaptadorAsistencia adapter);

}

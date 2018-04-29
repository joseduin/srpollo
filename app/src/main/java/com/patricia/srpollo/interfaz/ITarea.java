package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.adaptador.AdaptadorTarea;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.Tarea;

import java.util.ArrayList;

/**
 * Created by Jose on 29/3/2018.
 */

public interface ITarea {

    public void generarLayoutVertical();

    public AdaptadorTarea crearAdaptador(ArrayList<Tarea> list);

    public void inicializarAdaptadorRV(AdaptadorTarea adapter);

}

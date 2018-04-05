package com.patricia.srpollo.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.bd.BdContructor;
import com.patricia.srpollo.interfaz.IListaCompras;
import com.patricia.srpollo.interfaz.IPedidosSodas;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.ListaComprasResponse;
import com.patricia.srpollo.restApi.modelo.PedidosSodasResponse;
import com.patricia.srpollo.sesion.SessionManager;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 29/3/2018.
 */

public class PedidoSodaPresentador implements IPedidoSodaPresentador {

    private IPedidosSodas iPedidosSodas;
    private Context context;
    private SessionManager session;
    private BdContructor bd;

    private ArrayList<PedidoSoda> pedidoSodas = new ArrayList<>();
    private Configuracion configuracion;
    private ProgressDialog progressDialog;

    public PedidoSodaPresentador(IPedidosSodas iPedidosSodas, Context context, boolean searchInDataBase) {
        this.iPedidosSodas = iPedidosSodas;
        this.context = context;
        session = new SessionManager(context);
        bd = new BdContructor(context);

        if (!searchInDataBase) {
            buscarListado();
        } else {
            mostrarRecicler();
        }
    }

    @Override
    public void buscarListado() {
        progressDialog = Mensaje.progressConsultar(this.context);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.pedidosSodas();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<PedidosSodasResponse> call = endPointsApi.listaPedidosSodas(session.getActivo().getAlmacen_id());
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<PedidosSodasResponse>() {
            @Override
            public void onResponse(Call<PedidosSodasResponse> call, Response<PedidosSodasResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    PedidosSodasResponse r = response.body();

                    // Guardar en bd
                    bd.pedidoSodaEliminarTodos();
                    for (PedidoSoda l : r.getPedidoSoda())
                        bd.pedidoSodaInsertar(l);
                    bd.configuracionInsertar(r.getConfiguracion());

                    mostrarRecicler();
                }
            }

            @Override
            public void onFailure(Call<PedidosSodasResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void mostrarRecicler() {
        pedidoSodas = bd.pedidosSodas();
        configuracion = bd.configuracion();

        iPedidosSodas.inicializarAdaptadorRV(iPedidosSodas.crearAdaptador(pedidoSodas));
        iPedidosSodas.generarLayoutVertical();

        iPedidosSodas.configuraciones(configuracion);
    }

}

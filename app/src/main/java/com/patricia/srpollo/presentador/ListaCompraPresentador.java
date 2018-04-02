package com.patricia.srpollo.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.InicioActivity;
import com.patricia.srpollo.RegistroDiarioActivity;
import com.patricia.srpollo.bd.BdContructor;
import com.patricia.srpollo.interfaz.IListaCompras;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.ListaComprasResponse;
import com.patricia.srpollo.sesion.SessionManager;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 29/3/2018.
 */

public class ListaCompraPresentador implements IListaCompraPresentador {

    private IListaCompras iListaCompras;
    private Context context;
    private SessionManager session;
    private BdContructor bd;

    private ArrayList<ListaCompra> listaCompras = new ArrayList<>();
    private Configuracion configuracion;
    private ProgressDialog progressDialog;

    public ListaCompraPresentador(IListaCompras iListaCompras, Context context, boolean searchInDataBase) {
        this.iListaCompras = iListaCompras;
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
        Gson gson = restApiAdapter.listaCompras();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<ListaComprasResponse> call = endPointsApi.listaComprasGenerales(session.getActivo().getAlmacen_id());
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<ListaComprasResponse>() {
            @Override
            public void onResponse(Call<ListaComprasResponse> call, Response<ListaComprasResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    ListaComprasResponse r = response.body();

                    // Guardar en bd
                    bd.listaCompraEliminarTodos();
                    for (ListaCompra l : r.getListaCompra())
                        bd.listaCompraInsertar(l);
                    bd.configuracionInsertar(r.getConfiguracion());

                    mostrarRecicler();
                }
            }

            @Override
            public void onFailure(Call<ListaComprasResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void mostrarRecicler() {
        listaCompras = bd.listaCompras();
        configuracion = bd.configuracion();

        iListaCompras.inicializarAdaptadorRV(iListaCompras.crearAdaptador(listaCompras));
        iListaCompras.generarLayoutVertical();

        iListaCompras.configuraciones(configuracion);
    }

}

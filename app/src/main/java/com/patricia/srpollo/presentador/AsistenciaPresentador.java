package com.patricia.srpollo.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IAsistencia;
import com.patricia.srpollo.interfaz.ITrabajador;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.TrabajadorResponse;
import com.patricia.srpollo.sesion.SessionManager;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 24/2/2018.
 */

public class AsistenciaPresentador implements IAsistenciaPresentador {

    private final Context context;
    private final IAsistencia iInterfaz;
    private SessionManager session;

    private ArrayList<Trabajador> lista = new ArrayList<>();
    private ProgressDialog progressDialog;

    public AsistenciaPresentador(Context context, IAsistencia iInterfaz) {
        this.context = context;
        this.iInterfaz = iInterfaz;
        this.session = new SessionManager(context);

        buscarListado();
    }
    @Override
    public void buscarListado() {
        progressDialog = Mensaje.progressConsultar(this.context);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.login();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<TrabajadorResponse> call = endPointsApi.buscarTrabajadorFaltantesPorAsistencia(session.getActivo().getAlmacen_id());
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<TrabajadorResponse>() {
            @Override
            public void onResponse(Call<TrabajadorResponse> call, Response<TrabajadorResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    TrabajadorResponse r = response.body();
                    lista = r.getTrabajadors();

                    mostrarRecicler();
                }
            }

            @Override
            public void onFailure(Call<TrabajadorResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void mostrarRecicler() {
        iInterfaz.inicializarAdaptadorRV(iInterfaz.crearAdaptador(lista));
        iInterfaz.generarLayoutVertical();
    }

}

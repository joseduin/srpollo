package com.patricia.srpollo.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IInfraccion;
import com.patricia.srpollo.interfaz.ITrabajador;
import com.patricia.srpollo.modelo.Infraccion;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.InfraccionResponss;
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

public class TrabajadorPresentador implements ITrabajadorPresentador {

    private final Context context;
    private final ITrabajador iInterfaz;
    private SessionManager session;

    private ArrayList<Trabajador> lista = new ArrayList<>();

    public TrabajadorPresentador(Context context, ITrabajador iInterfaz) {
        this.context = context;
        this.iInterfaz = iInterfaz;
        this.session = new SessionManager(context);

        obtenerTrabajdor();
    }

    @Override
    public void obtenerTrabajdor() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.login();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<TrabajadorResponse> call = endPointsApi.buscarTrabajadorSanciones(session.getActivo().getAlmacen_id());
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<TrabajadorResponse>() {
            @Override
            public void onResponse(Call<TrabajadorResponse> call, Response<TrabajadorResponse> response) {
                Log.d("RESPONSE", response.code() + "");

                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString());
                    TrabajadorResponse r = response.body();
                    lista.addAll(r.getTrabajadors());
                    Log.d("RESPONSE P", lista.size() + "");

                    prepararComboTrabajador();

                } else { Mensaje.mensajeCorto(context, Mensaje.NO_CONTENT); }
            }


            @Override
            public void onFailure(Call<TrabajadorResponse> call, Throwable t) {
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void prepararComboTrabajador() {
        String[] spinnerArray = new String[lista.size()];
        HashMap<String, Trabajador> spinnerMap = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            Trabajador t = lista.get(i);

            String name = t.getNombre() + " " + t.getApellido () + " (" + t.getUsuario() + ") - " + t.getCargo().getDescripcion();
            spinnerMap.put(name, t);
            spinnerArray[i] = name;
        }
        mostrarComboTrabajador(spinnerArray);
        iInterfaz.cargarMapTrabajador(spinnerMap);
    }

    @Override
    public void mostrarComboTrabajador(String[] spinnerArray) {
        iInterfaz.cargarComboTrabajador(spinnerArray);
    }

}

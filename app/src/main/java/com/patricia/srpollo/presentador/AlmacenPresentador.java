package com.patricia.srpollo.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IAlmacen;
import com.patricia.srpollo.interfaz.ITurno;
import com.patricia.srpollo.modelo.Almacen;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.AlmacenResponse;
import com.patricia.srpollo.restApi.modelo.TurnoResponse;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 24/2/2018.
 */

public class AlmacenPresentador implements IAlmacenPresentador {

    private final Context context;
    private final IAlmacen iAlmacen;

    private ArrayList<Almacen> almacens = new ArrayList<>();

    public AlmacenPresentador(Context context, IAlmacen iAlmacen) {
        this.context = context;
        this.iAlmacen = iAlmacen;

        obtenerAlmacen();
    }

    @Override
    public void obtenerAlmacen() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.almacen();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<AlmacenResponse> call = endPointsApi.buscarAlmacenes();
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<AlmacenResponse>() {
            @Override
            public void onResponse(Call<AlmacenResponse> call, Response<AlmacenResponse> response) {
                Log.d("RESPONSE", response.code() + "");

                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString());
                    AlmacenResponse r = response.body();
                    almacens.addAll(r.getAlmacens());

                    prepararComboAlmacen();

                } else { Mensaje.mensajeCorto(context, Mensaje.NO_CONTENT); }
            }

            @Override
            public void onFailure(Call<AlmacenResponse> call, Throwable t) {
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void prepararComboAlmacen() {
        String[] spinnerArray = new String[almacens.size()];
        HashMap<String, Almacen> spinnerMap = new HashMap<>();
        for (int i = 0; i < almacens.size(); i++) {
            Almacen t = almacens.get(i);

            spinnerMap.put(t.getNombre(), t);
            spinnerArray[i] = t.getNombre();
        }
        mostrarComboAlmacen(spinnerArray);
        iAlmacen.cargarMapAlmacen(spinnerMap);
    }

    @Override
    public void mostrarComboAlmacen(String[] spinnerArray) {
        iAlmacen.cargarComboAlmacen(spinnerArray);
    }

}

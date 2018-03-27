package com.patricia.srpollo.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.ITurno;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
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

public class TurnoPresentador implements ITurnoPresentador {

    private final Context context;
    private final ITurno iRegistroDiario;

    private ArrayList<Turno> turnos = new ArrayList<>();

    public TurnoPresentador(Context context, ITurno iRegistroDiario) {
        this.context = context;
        this.iRegistroDiario = iRegistroDiario;

        obtenerTurnos();
    }

    @Override
    public void obtenerTurnos() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buscarTurnos();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<TurnoResponse> call = endPointsApi.buscarTurnos();
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<TurnoResponse>() {
            @Override
            public void onResponse(Call<TurnoResponse> call, Response<TurnoResponse> response) {
                Log.d("RESPONSE", response.code() + "");

                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString());
                    TurnoResponse r = response.body();
                    turnos.addAll(r.getTurno());

                    prepararComboTurno();

                } else { Mensaje.mensajeCorto(context, Mensaje.NO_CONTENT); }
            }

            @Override
            public void onFailure(Call<TurnoResponse> call, Throwable t) {
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void prepararComboTurno() {
        String[] spinnerArray = new String[turnos.size()];
        HashMap<String, Turno> spinnerMap = new HashMap<>();
        for (int i = 0; i < turnos.size(); i++) {
            Turno t = turnos.get(i);

            spinnerMap.put(t.getDescripcion(), t);
            spinnerArray[i] = t.getDescripcion();
        }
        mostrarComboTurno(spinnerArray);
        iRegistroDiario.cargarMapTurno(spinnerMap);
    }

    @Override
    public void mostrarComboTurno(String[] spinnerArray) {
        iRegistroDiario.cargarComboTurno(spinnerArray);
    }
}

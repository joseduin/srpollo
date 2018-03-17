package com.patricia.srpollo.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IProducto;
import com.patricia.srpollo.interfaz.ISabore;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.modelo.Sabore;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
import com.patricia.srpollo.restApi.modelo.SaboreResponse;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 24/2/2018.
 */

public class SaborePresentador implements ISaborePresentador {

    private final Context context;
    private final ISabore iSabore;

    private ArrayList<Sabore> sabores = new ArrayList<>();

    public SaborePresentador(Context context, ISabore iSabore) {
        this.context = context;
        this.iSabore = iSabore;

        obtenerSabores();
    }

    @Override
    public void obtenerSabores() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buscarSabores();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<SaboreResponse> call = endPointsApi.buscarSabores();
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<SaboreResponse>() {
            @Override
            public void onResponse(Call<SaboreResponse> call, Response<SaboreResponse> response) {
                Log.d("RESPONSE", response.code() + "");

                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString());
                    SaboreResponse r = response.body();
                    sabores.addAll(r.getSabore());
                    Log.d("RESPONSE P", sabores.size() + "");

                    prepararComboSabores();

                } else { Mensaje.mensajeCorto(context, Mensaje.NO_CONTENT); }
            }

            @Override
            public void onFailure(Call<SaboreResponse> call, Throwable t) {
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void prepararComboSabores() {
        String[] spinnerArray = new String[sabores.size()];
        String separator = " - ";
        HashMap<String, Sabore> spinnerMap = new HashMap<>();
        for (int i = 0; i < sabores.size(); i++) {
            Sabore t = sabores.get(i);

            String descripcion = t.getSoda().getCategoria().getDescripcion() + separator +
                    t.getSoda().getDescripcion() + separator +
                    t.getDescripcion();

            spinnerMap.put(descripcion, t);
            spinnerArray[i] = descripcion;
        }
        mostrarComboSabores(spinnerArray);
        iSabore.cargarMapSabore(spinnerMap);
    }

    @Override
    public void mostrarComboSabores(String[] spinnerArray) {
        iSabore.cargarComboSabore(spinnerArray);
    }
}

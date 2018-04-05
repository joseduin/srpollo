package com.patricia.srpollo.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IInfraccion;
import com.patricia.srpollo.interfaz.IProducto;
import com.patricia.srpollo.modelo.Infraccion;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.InfraccionResponss;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 24/2/2018.
 */

public class InfraccionPresentador implements IInfraccionPresentador {

    private final Context context;
    private final IInfraccion iInterfaz;

    private ArrayList<Infraccion> lista = new ArrayList<>();

    public InfraccionPresentador(Context context, IInfraccion iInterfaz) {
        this.context = context;
        this.iInterfaz = iInterfaz;

        obtenerInfraccion();
    }

    @Override
    public void obtenerInfraccion() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.infraccion();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<InfraccionResponss> call = endPointsApi.buscarInfracciones();
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<InfraccionResponss>() {
            @Override
            public void onResponse(Call<InfraccionResponss> call, Response<InfraccionResponss> response) {
                Log.d("RESPONSE", response.code() + "");

                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString());
                    InfraccionResponss r = response.body();
                    lista.addAll(r.getInfraccions());
                    Log.d("RESPONSE P", lista.size() + "");

                    prepararComboInfracciones();

                } else { Mensaje.mensajeCorto(context, Mensaje.NO_CONTENT); }
            }


            @Override
            public void onFailure(Call<InfraccionResponss> call, Throwable t) {
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void prepararComboInfracciones() {
        String[] spinnerArray = new String[lista.size()];
        HashMap<String, Infraccion> spinnerMap = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            Infraccion t = lista.get(i);

            spinnerMap.put(t.getDescripcion(), t);
            spinnerArray[i] = t.getDescripcion();
        }
        mostrarComboInfracciones(spinnerArray);
        iInterfaz.cargarMapInfraccion(spinnerMap);
    }

    @Override
    public void mostrarComboInfracciones(String[] spinnerArray) {
        iInterfaz.cargarComboInfraccion(spinnerArray);
    }
}

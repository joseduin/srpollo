package com.patricia.srpollo.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IProducto;
import com.patricia.srpollo.interfaz.ITurno;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
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

public class ProductoPresentador implements IProductoPresentador {

    private final Context context;
    private final IProducto iProducto;

    private ArrayList<Producto> productos = new ArrayList<>();

    public ProductoPresentador(Context context, IProducto iProducto) {
        this.context = context;
        this.iProducto = iProducto;

        obtenerProductos();
    }

    @Override
    public void obtenerProductos() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buscarProductos();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<ProductoResponse> call = endPointsApi.buscarProductos();
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
            Log.d("RESPONSE", response.code() + "");

                if (response.code() == 200) {
                Log.d("RESPONSE", response.body().toString());
                ProductoResponse r = response.body();
                productos.addAll(r.getProducto());
                Log.d("RESPONSE P", productos.size() + "");

                prepararComboProductos();

            } else { Mensaje.mensajeCorto(context, Mensaje.NO_CONTENT); }
            }


            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                Mensaje.mensajeCorto(context, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void prepararComboProductos() {
        String[] spinnerArray = new String[productos.size()];
        HashMap<String, Producto> spinnerMap = new HashMap<String, Producto>();
        for (int i = 0; i < productos.size(); i++) {
            Producto t = productos.get(i);

            spinnerMap.put(t.getDescripcion(), t);
            spinnerArray[i] = t.getDescripcion();
        }
        mostrarComboProductos(spinnerArray);
        iProducto.cargarMapProductos(spinnerMap);
    }

    @Override
    public void mostrarComboProductos(String[] spinnerArray) {
        iProducto.cargarComboProducto(spinnerArray);
    }
}

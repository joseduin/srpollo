package com.patricia.srpollo.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.restApi.ConstantesRestApi;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.desealizador.ProductoDeserealizador;
import com.patricia.srpollo.restApi.desealizador.RegistroDiarioDeserealizador;
import com.patricia.srpollo.restApi.desealizador.TurnoDeserealizador;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.restApi.modelo.TurnoResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jose on 19/11/2017.
 */

public class RestApiAdapter {

    public EndPointsApi establecerConexionApi(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson buscarTurnos(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TurnoResponse.class, new TurnoDeserealizador());
        return gsonBuilder.create();
    }
    public Gson buscarProductos(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProductoResponse.class, new ProductoDeserealizador());
        return gsonBuilder.create();
    }
    public Gson existeRegistro(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RegistroDiarioResponse.class, new RegistroDiarioDeserealizador());
        return gsonBuilder.create();
    }

}
package com.patricia.srpollo.restApi;

import com.patricia.srpollo.modelo.RegistroDiarioRequest;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.restApi.modelo.TurnoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jose on 24/2/2018.
 */

public interface EndPointsApi {

    @GET(ConstantesRestApi.URL_TURNO)
    Call<TurnoResponse> buscarTurnos();

    @GET(ConstantesRestApi.URL_PRODUCTO)
    Call<ProductoResponse> buscarProductos();

    @GET(ConstantesRestApi.URL_EXISTE_REGISTRRO)
    Call<RegistroDiarioResponse> existe_registro(@Query("almacen_id") int almacen_id);

    @Headers("Content-Type: application/json")
    @POST(ConstantesRestApi.URL_REGISTRO_DIARIO)
    Call<RegistroDiarioResponse> registroRequest(@Body RegistroDiarioRequest request);
}

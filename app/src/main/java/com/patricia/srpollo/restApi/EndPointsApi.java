package com.patricia.srpollo.restApi;

import com.patricia.srpollo.modelo.ListaCompraRequest;
import com.patricia.srpollo.modelo.PedidosSodasRequest;
import com.patricia.srpollo.modelo.RegistroDiarioItemRequest;
import com.patricia.srpollo.modelo.RegistroDiarioRequest;
import com.patricia.srpollo.modelo.TrabajadorRequest;
import com.patricia.srpollo.restApi.modelo.AlmacenResponse;
import com.patricia.srpollo.restApi.modelo.InfraccionResponss;
import com.patricia.srpollo.restApi.modelo.ListaComprasResponse;
import com.patricia.srpollo.restApi.modelo.OkResponse;
import com.patricia.srpollo.restApi.modelo.PedidosSodasResponse;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.restApi.modelo.SaboreResponse;
import com.patricia.srpollo.restApi.modelo.TrabajadorResponse;
import com.patricia.srpollo.restApi.modelo.TurnoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
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

    @GET(ConstantesRestApi.URL_SABORE)
    Call<SaboreResponse> buscarSabores();

    @Headers("Content-Type: application/json")
    @POST(ConstantesRestApi.URL_REGISTRO_DIARIO_ITEM)
    Call<RegistroDiarioResponse> registroItemRequest(@Body RegistroDiarioItemRequest request,
                                                     @Path("registro_diario_id") int registro_diario_id);

    @GET(ConstantesRestApi.URL_REGISTRO_DIARIO)
    Call<RegistroDiarioResponse> buscarHistoricoRegistroDiario(@Query("almacen_id") int almacen_id,
                                                       @Query("fecha") String fecha);

    @Headers("Content-Type: application/json")
    @POST(ConstantesRestApi.URL_LOGIN)
    Call<TrabajadorResponse> login(@Body TrabajadorRequest request);

    @GET(ConstantesRestApi.URL_LISTA_GENERAL)
    Call<ListaComprasResponse> listaComprasGenerales(@Query("almacen_id") int almacen_id);

    @Headers("Content-Type: application/json")
    @PATCH(ConstantesRestApi.URL_LISTA_GENERAL_REQUEST)
    Call<ListaComprasResponse> listaComprasGeneralesRequest(@Body ListaCompraRequest request,
                                                  @Path("id") int id);

    @GET(ConstantesRestApi.URL_PEDIDOS_SODAS)
    Call<PedidosSodasResponse> listaPedidosSodas(@Query("almacen_id") int almacen_id);

    @Headers("Content-Type: application/json")
    @PATCH(ConstantesRestApi.URL_PEDIDOS_SODAS_REQUEST)
    Call<PedidosSodasResponse> listaPedidosSodasRequest(@Body PedidosSodasRequest request,
                                                            @Path("id") int id);

    @GET(ConstantesRestApi.URL_ALMACEN)
    Call<AlmacenResponse> buscarAlmacenes();

    @GET(ConstantesRestApi.URL_INFRACCION)
    Call<InfraccionResponss> buscarInfracciones();

}

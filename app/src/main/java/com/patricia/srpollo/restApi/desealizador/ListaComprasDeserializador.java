package com.patricia.srpollo.restApi.desealizador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.ListaComprasResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by SPF1 on 3/27/2018.
 */

public class ListaComprasDeserializador implements JsonDeserializer<ListaComprasResponse> {
    @Override
    public ListaComprasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();

        ListaComprasResponse response = gson.fromJson(obj.toString(), ListaComprasResponse.class);
        JsonArray data  = obj.getAsJsonObject().getAsJsonArray(JsonKeys.LISTA);
        JsonObject dataConf  = obj.getAsJsonObject().getAsJsonObject(JsonKeys.CONFIGURACION);

        response.setListaCompra(deserializadores(data));
        response.setConfiguracion(deserializadorConf(dataConf));
        return response;
    }

    private Configuracion deserializadorConf(JsonObject data) {
        String soda_whatsaap = data.get(JsonKeys.soda_whatsaap).toString().contains("null") ? "" : data.get(JsonKeys.soda_whatsaap).getAsString();
        String lista_whatsaap = data.get(JsonKeys.lista_whatsaap).toString().contains("null") ? "" : data.get(JsonKeys.lista_whatsaap).getAsString();
        String numero_columna = data.get(JsonKeys.numero_columna).toString().contains("null") ? "" : data.get(JsonKeys.numero_columna).getAsString();

        Configuracion configuracion = new Configuracion(soda_whatsaap, lista_whatsaap);
        configuracion.setNumero_columna(numero_columna);
        return configuracion;
    }

    private ArrayList<ListaCompra> deserializadores(JsonArray data) {
        ArrayList<ListaCompra> listaCompras = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            double cantidad_comprar     = t.get(JsonKeys.cantidad_comprar).getAsDouble();

            JsonObject dataProd         = t.getAsJsonObject().getAsJsonObject(JsonKeys.producto);
            String descripcion          = dataProd.get(JsonKeys.descripcion).getAsString();
            double cantidad_paquete     = dataProd.get(JsonKeys.cantidad_paquete).getAsDouble();

            JsonObject dataUnid         = t.getAsJsonObject().getAsJsonObject(JsonKeys.unidad);
            String descripcionUnidad    = dataUnid.get(JsonKeys.descripcion).getAsString();

            ListaCompra listaCompra = new ListaCompra(descripcion, cantidad_comprar,cantidad_paquete, descripcionUnidad);
            listaCompras.add(listaCompra);
        }
        return listaCompras;
    }
}

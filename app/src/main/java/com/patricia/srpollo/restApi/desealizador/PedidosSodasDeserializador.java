package com.patricia.srpollo.restApi.desealizador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Categoria;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.modelo.Soda;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.ListaComprasResponse;
import com.patricia.srpollo.restApi.modelo.PedidosSodasResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by SPF1 on 3/27/2018.
 */

public class PedidosSodasDeserializador implements JsonDeserializer<PedidosSodasResponse> {

    @Override
    public PedidosSodasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();

        PedidosSodasResponse response = gson.fromJson(obj.toString(), PedidosSodasResponse.class);
        JsonArray data  = obj.getAsJsonObject().getAsJsonArray(JsonKeys.LISTA);
        JsonObject dataConf  = obj.getAsJsonObject().getAsJsonObject(JsonKeys.CONFIGURACION);

        response.setPedidoSoda(deserializadores(data));
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

    private ArrayList<PedidoSoda> deserializadores(JsonArray data) {
        ArrayList<PedidoSoda> pedidoSodas = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            int id                      = t.get(JsonKeys.id).getAsInt();
            double cantidad_comprar     = t.get(JsonKeys.cantidad_comprar).getAsDouble();
            int unidades_por_paquete    = t.get(JsonKeys.unidades_por_paquete).getAsInt();

            JsonObject dataSab          = t.getAsJsonObject().getAsJsonObject(JsonKeys.sabore);
            String descripcion          = dataSab.get(JsonKeys.descripcion).getAsString();
            Soda soda                   = deserializarSoda(t.getAsJsonObject().getAsJsonObject(JsonKeys.soda));
            descripcion = soda.getCategoria().getDescripcion() + " - " + soda.getDescripcion() + " - " + descripcion;

            JsonObject dataUnid         = t.getAsJsonObject().getAsJsonObject(JsonKeys.unidad);
            String descripcionUnidad    = dataUnid.get(JsonKeys.descripcion).getAsString();

            PedidoSoda pedidoSoda = new PedidoSoda(id, descripcion, cantidad_comprar, unidades_por_paquete, descripcionUnidad);
            pedidoSodas.add(pedidoSoda);
        }
        return pedidoSodas;
    }

    private Soda deserializarSoda(JsonObject data) {
        int id = data.get(JsonKeys.id).getAsInt();
        String descripcion = data.get(JsonKeys.descripcion).getAsString();
        int cantidad_unidades = data.get(JsonKeys.cantidad_unidades).getAsInt();
        double costo = data.get(JsonKeys.costo).getAsDouble();
        double precio = data.get(JsonKeys.precio).getAsDouble();
        Categoria categoria = deserializadorCategoria(data.getAsJsonObject().getAsJsonObject(JsonKeys.categoria));

        return new Soda(id, descripcion, cantidad_unidades, costo, precio, categoria);
    }

    private Categoria deserializadorCategoria(JsonObject data) {
        int id = data.get(JsonKeys.id).getAsInt();
        String descripcion = data.get(JsonKeys.descripcion).getAsString();

        return new Categoria(id, descripcion);
    }
}

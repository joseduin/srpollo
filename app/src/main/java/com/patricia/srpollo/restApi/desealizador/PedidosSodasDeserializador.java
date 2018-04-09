package com.patricia.srpollo.restApi.desealizador;

import android.util.Log;

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
        Log.d("GSON", json.toString());
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);

        Log.d("GSON", "1");
        PedidosSodasResponse response = gson.fromJson(obj.toString(), PedidosSodasResponse.class);
        JsonObject data = obj.getAsJsonObject().getAsJsonObject(JsonKeys.data);

        Log.d("GSON", "2");
        JsonArray datas  = data.getAsJsonObject().getAsJsonArray(JsonKeys.LISTA);
        Log.d("GSON", "3");
        JsonObject dataConf  = data.getAsJsonObject().getAsJsonObject(JsonKeys.CONFIGURACION);
        Log.d("GSON", "4");
        response.setPedidoSoda(deserializadores(datas));
        Log.d("GSON", "5");
        response.setConfiguracion(deserializadorConf(dataConf));
        Log.d("GSON", "6");
        return response;
    }

    private Configuracion deserializadorConf(JsonObject data) {
        Log.d("GSON", "10");
        String soda_whatsaap = data.get(JsonKeys.soda_whatsaap).toString().contains("null") ? "" : data.get(JsonKeys.soda_whatsaap).getAsString();
        Log.d("GSON", "11");
        String lista_whatsaap = data.get(JsonKeys.lista_whatsaap).toString().contains("null") ? "" : data.get(JsonKeys.lista_whatsaap).getAsString();
        Log.d("GSON", "12");
        String numero_columna = data.get(JsonKeys.numero_columna).toString().contains("null") ? "" : data.get(JsonKeys.numero_columna).getAsString();

        Log.d("GSON", "13");
        Configuracion configuracion = new Configuracion(soda_whatsaap, lista_whatsaap);
        Log.d("GSON", "14");
        configuracion.setNumero_columna(numero_columna);
        return configuracion;
    }

    private ArrayList<PedidoSoda> deserializadores(JsonArray data) {
        Log.d("GSON", "-20");
        ArrayList<PedidoSoda> pedidoSodas = new ArrayList<>();
        Log.d("GSON", "-21");

        for (int i = 0; i < data.size(); i++) {
            Log.d("GSON", "-22");
            JsonObject t = data.get(i).getAsJsonObject();

            Log.d("GSON", "20");
            int id                      = t.get(JsonKeys.id).getAsInt();
            Log.d("GSON", "21");
            double cantidad_comprar     = t.get(JsonKeys.cantidad_comprar).getAsDouble();
            Log.d("GSON", "22");
            int unidades_por_paquete    = t.get(JsonKeys.unidades_por_paquete).getAsInt();

            Log.d("GSON", "23");
            JsonObject dataSab          = t.getAsJsonObject().getAsJsonObject(JsonKeys.sabore);
            Log.d("GSON", "24");
            String descripcion          = dataSab.get(JsonKeys.descripcion).getAsString();
            Log.d("GSON", "25");
            Soda soda                   = deserializarSoda(dataSab.getAsJsonObject().getAsJsonObject(JsonKeys.soda));
            Log.d("GSON", "26");
            descripcion = soda.getCategoria().getDescripcion() + " - " + soda.getDescripcion() + " - " + descripcion;

            Log.d("GSON", "27");
            JsonObject dataUnid         = t.getAsJsonObject().getAsJsonObject(JsonKeys.unidad);
            Log.d("GSON", "28");
            String descripcionUnidad    = dataUnid.get(JsonKeys.descripcion).getAsString();

            Log.d("GSON", "29");
            PedidoSoda pedidoSoda = new PedidoSoda(id, descripcion, cantidad_comprar, unidades_por_paquete, descripcionUnidad);
            pedidoSodas.add(pedidoSoda);
        }
        return pedidoSodas;
    }

    private Soda deserializarSoda(JsonObject data) {
        Log.d("GSON", "30");
        int id = data.get(JsonKeys.id).getAsInt();
        Log.d("GSON", "31");
        String descripcion = data.get(JsonKeys.descripcion).getAsString();
        Log.d("GSON", "32");
        int cantidad_unidades = data.get(JsonKeys.cantidad_unidades).getAsInt();
        Log.d("GSON", "33");
        double costo = data.get(JsonKeys.costo).getAsDouble();
        Log.d("GSON", "34");
        double precio = data.get(JsonKeys.precio).getAsDouble();
        Log.d("GSON", "35");
        Categoria categoria = deserializadorCategoria(data.getAsJsonObject().getAsJsonObject(JsonKeys.categoria));
        Log.d("GSON", "36");
        return new Soda(id, descripcion, cantidad_unidades, costo, precio, categoria);
    }

    private Categoria deserializadorCategoria(JsonObject data) {
        Log.d("GSON", "40");
        int id = data.get(JsonKeys.id).getAsInt();
        Log.d("GSON", "41");
        String descripcion = data.get(JsonKeys.descripcion).getAsString();
        Log.d("GSON", "42");
        return new Categoria(id, descripcion);
    }

}

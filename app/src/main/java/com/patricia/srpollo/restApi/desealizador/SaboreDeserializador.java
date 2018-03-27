package com.patricia.srpollo.restApi.desealizador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Categoria;
import com.patricia.srpollo.modelo.Sabore;
import com.patricia.srpollo.modelo.Soda;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.SaboreResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jose on 4/3/2018.
 */

public class SaboreDeserializador implements JsonDeserializer<SaboreResponse> {
    @Override
    public SaboreResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);
        SaboreResponse response = gson.fromJson(obj.toString(), SaboreResponse.class);
        JsonArray data  = obj.getAsJsonObject().getAsJsonArray(JsonKeys.data);

        response.setSabore(deserializarSabore(data));

        return response;
    }

    private ArrayList<Sabore> deserializarSabore(JsonArray data) {
        ArrayList<Sabore> sabores = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            int id = t.get(JsonKeys.id).getAsInt();
            String descripcion = t.get(JsonKeys.descripcion).getAsString();
            Soda soda = deserializarSoda(t.getAsJsonObject().getAsJsonObject(JsonKeys.soda));

            sabores.add(new Sabore(id, descripcion, soda));
        }
        return sabores;
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

package com.patricia.srpollo.restApi.desealizador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Almacen;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.AlmacenResponse;
import com.patricia.srpollo.restApi.modelo.TurnoResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jose on 24/2/2018.
 */

public class AlmacenDeserealizador implements JsonDeserializer<AlmacenResponse> {

    @Override
    public AlmacenResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);

        AlmacenResponse response = gson.fromJson(obj.toString(), AlmacenResponse.class);
        JsonArray data = obj.getAsJsonObject().getAsJsonArray(JsonKeys.data);

        response.setAlmacens(deserializadores(data));
        return response;
    }

    private ArrayList<Almacen> deserializadores(JsonArray data) {
        ArrayList<Almacen> lista = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            int id              = t.get(JsonKeys.id).getAsInt();
            String nombre  = t.get(JsonKeys.nombre).getAsString();

            Almacen almacen = new Almacen(id, nombre);
            lista.add(almacen);
        }
        return lista;
    }

}

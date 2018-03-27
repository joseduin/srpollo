package com.patricia.srpollo.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.TurnoResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jose on 24/2/2018.
 */

public class TurnoDeserealizador  implements JsonDeserializer<TurnoResponse> {

    @Override
    public TurnoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);

        TurnoResponse response = gson.fromJson(obj.toString(), TurnoResponse.class);
        JsonArray data = obj.getAsJsonObject().getAsJsonArray(JsonKeys.data);

        response.setTurno(deserializadores(data));
        return response;
    }

    private ArrayList<Turno> deserializadores(JsonArray data) {
        ArrayList<Turno> turnos = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            int id              = t.get(JsonKeys.id).getAsInt();
            String descripcion  = t.get(JsonKeys.descripcion).getAsString();
            String inicio       = t.get(JsonKeys.inicio).getAsString();
            String fin          = t.get(JsonKeys.fin).getAsString();

            Turno turno = new Turno(id, descripcion, inicio, fin);
            turnos.add(turno);
        }
        return turnos;
    }

}

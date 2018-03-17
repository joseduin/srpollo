package com.patricia.srpollo.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Almacen;
import com.patricia.srpollo.modelo.Cargo;
import com.patricia.srpollo.modelo.Error;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.TrabajadorResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jose on 14/3/2018.
 */

public class TrabajadorDeserializador implements JsonDeserializer<TrabajadorResponse> {
    @Override
    public TrabajadorResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("GSON", json.toString());
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);
        TrabajadorResponse response = gson.fromJson(obj.toString(), TrabajadorResponse.class);
        JsonObject data = obj.getAsJsonObject().getAsJsonObject(JsonKeys.data);

        boolean tieneError = data.toString().contains("error");
        if (tieneError) {
            response.setError(deserializarError(data));
        } else {

            if (data.isJsonArray()) {
                JsonArray datas = data.getAsJsonObject().getAsJsonArray();
                response.setTrabajadors(deserializarTrbajadores(datas));
            } else {
                response.setTrabajador(deserializarTrabajador(data));
            }
        }
        return response;
    }

    private Error deserializarError(JsonObject data) {
        String error = data.get(JsonKeys.error).getAsString();
        return new Error(error);
    }

    private ArrayList<Trabajador> deserializarTrbajadores(JsonArray data) {
        ArrayList<Trabajador> trabajadors = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();
            trabajadors.add(deserializarTrabajador(t));
        }
        return trabajadors;
    }

    private Trabajador deserializarTrabajador(JsonObject data) {
        int id = data.get(JsonKeys.id).getAsInt();
        int rol = data.get(JsonKeys.rol).getAsInt();
        String nombre = data.get(JsonKeys.nombre).getAsString();
        String apellido = data.get(JsonKeys.apellido).getAsString();
        int cargo_id = data.get(JsonKeys.cargo_id).getAsInt();
        String identificacion = data.get(JsonKeys.identificacion).toString().contains("null") ? "" : data.get(JsonKeys.identificacion).getAsString();
        int almacen_id = data.get(JsonKeys.almacen_id).getAsInt();
        String usuario = data.get(JsonKeys.usuario).getAsString();

        Turno turno = new Turno();
        Cargo cargo = new Cargo();
        Almacen almacen = new Almacen();
        Trabajador trabajador = new Trabajador(id, rol, nombre, apellido, cargo_id, identificacion, almacen_id, usuario);
        if (rol == 2) {
            turno = deserializarTurno(data.getAsJsonObject().getAsJsonObject(JsonKeys.turno));
            cargo = deserializarCargo(data.getAsJsonObject().getAsJsonObject(JsonKeys.cargo));
            almacen = deserializarAlmacen(data.getAsJsonObject().getAsJsonObject(JsonKeys.almacen));

            trabajador.setTurno(turno);
            trabajador.setCargo(cargo);
            trabajador.setAlmacen(almacen);
        }

        return trabajador;
    }

    private Almacen deserializarAlmacen(JsonObject t) {
        int id              = t.get(JsonKeys.id).getAsInt();
        String descripcion  = t.get(JsonKeys.nombre).getAsString();
        return new Almacen(id, descripcion);
    }

    private Cargo deserializarCargo(JsonObject t) {
        int id              = t.get(JsonKeys.id).getAsInt();
        String descripcion  = t.get(JsonKeys.descripcion).getAsString();
        return new Cargo(id, descripcion);
    }

    private Turno deserializarTurno(JsonObject t) {
        int id              = t.get(JsonKeys.id).getAsInt();
        String descripcion  = t.get(JsonKeys.descripcion).getAsString();
        String inicio       = t.get(JsonKeys.inicio).getAsString();
        String fin          = t.get(JsonKeys.fin).getAsString();

        return new Turno(id, descripcion, inicio, fin);
    }
}

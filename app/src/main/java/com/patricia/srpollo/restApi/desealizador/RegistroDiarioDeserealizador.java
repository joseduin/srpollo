package com.patricia.srpollo.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Error;
import com.patricia.srpollo.modelo.RegistroDiario;
import com.patricia.srpollo.modelo.RegistroDiarioItem;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.utils.Convertidor;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jose on 3/3/2018.
 */

public class RegistroDiarioDeserealizador implements JsonDeserializer<RegistroDiarioResponse> {

    @Override
    public RegistroDiarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);
        RegistroDiarioResponse response = gson.fromJson(obj.toString(), RegistroDiarioResponse.class);
        JsonObject data = obj.getAsJsonObject().getAsJsonObject(JsonKeys.data);

        boolean tieneError = data.toString().contains("error");
        boolean tieneItem = data.toString().contains("registro_diario_items");

        if (tieneError) {
            response.setError(deserializarError(data));
        } else {
            response.setRegistroDiario(deserializadores(data, tieneItem));
        }
        return response;
    }

    private Error deserializarError(JsonObject data) {
        String error = data.get(JsonKeys.error).getAsString();

        Error error1 = new Error(error);
        return error1;
    }

    private RegistroDiario deserializadores(JsonObject data, boolean tieneItem) {
        int id = data.get(JsonKeys.id).getAsInt();
        double ingreso_efectivo = Convertidor.StringToDouble(data.get(JsonKeys.ingreso_efectivo).getAsString());
        String fecha = data.get(JsonKeys.fecha).getAsString();
        String created_at = data.get(JsonKeys.created_at).getAsString();
        int almacen_id = data.get(JsonKeys.almacen_id).getAsInt();

        RegistroDiario registroDiario = new RegistroDiario(id, ingreso_efectivo, fecha, created_at, almacen_id);
        if (tieneItem) {
            registroDiario.setItems(deserializadorItem(data.getAsJsonObject().getAsJsonArray(JsonKeys.registro_diario_item)));
        }
        return registroDiario;
    }

    private ArrayList<RegistroDiarioItem> deserializadorItem(JsonArray data) {
        ArrayList<RegistroDiarioItem> registroDiarioItems = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            int id                  = t.get(JsonKeys.id).getAsInt();
            int turno_id            = t.get(JsonKeys.turno_id).getAsInt();
            int producto_id         = Convertidor.StringToInt( t.get(JsonKeys.producto_id).toString().contains("null") ? null : t.get(JsonKeys.producto_id).getAsString() );
            int sabore_id           = Convertidor.StringToInt( t.get(JsonKeys.sabore_id).toString().contains("null") ? null : t.get(JsonKeys.sabore_id).getAsString() );
            double cantidad_ingreso = Convertidor.StringToDouble(t.get(JsonKeys.cantidad_ingreso).getAsString());
            double gasto_efectivo   = Convertidor.StringToDouble(t.get(JsonKeys.gasto_efectivo).getAsString());
            double caja_chica       = Convertidor.StringToDouble(t.get(JsonKeys.caja_chica).toString().contains("null") ? null : t.get(JsonKeys.caja_chica).getAsString());
            double saldo_caja_chica = Convertidor.StringToDouble(t.get(JsonKeys.saldo_caja_chica).getAsString());
            int registro_diario     = t.get(JsonKeys.registro_diario).getAsInt();
            String created_at       = t.get(JsonKeys.created_at).getAsString();

            RegistroDiarioItem registroDiarioItem = new RegistroDiarioItem(id, turno_id, producto_id, sabore_id, cantidad_ingreso, gasto_efectivo, caja_chica, saldo_caja_chica, registro_diario, created_at);
            registroDiarioItems.add(registroDiarioItem);
        }
        return registroDiarioItems;
    }


}

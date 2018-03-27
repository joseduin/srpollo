package com.patricia.srpollo.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.restApi.JsonKeys;
import com.patricia.srpollo.restApi.modelo.ProductoResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jose on 24/2/2018.
 */

public class ProductoDeserealizador implements JsonDeserializer<ProductoResponse> {
    @Override
    public ProductoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.add(JsonKeys.data, json);

        ProductoResponse response = gson.fromJson(obj.toString(), ProductoResponse.class);
        JsonArray data  = obj.getAsJsonObject().getAsJsonArray(JsonKeys.data);

        response.setProducto(deserializadores(data));
        return response;
    }

    private ArrayList<Producto> deserializadores(JsonArray data) {
        ArrayList<Producto> productos = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JsonObject t = data.get(i).getAsJsonObject();

            int id                  = t.get(JsonKeys.id).getAsInt();
            String descripcion      = t.get(JsonKeys.descripcion).getAsString();
            double cantidad_paquete = t.get(JsonKeys.cantidad_paquete).getAsDouble();
            double precio           = t.get(JsonKeys.precio).getAsDouble();
            double costo            = t.get(JsonKeys.costo).getAsDouble();

            Producto producto = new Producto(id, cantidad_paquete, descripcion, precio, costo);
            productos.add(producto);
        }
        return productos;
    }
}

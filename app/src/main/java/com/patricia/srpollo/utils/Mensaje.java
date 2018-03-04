package com.patricia.srpollo.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jose on 13/11/2017.
 */

public final class Mensaje {

    public static String CAMPOS_OBLIGATORIOS = "Todos los campos son Obligatorios";
    public static String ERROR_CONEXION = "Algo pasó en la conexión.. Intenta de nuevo";
    public static String ERROR_AUTH = "Los datos introducidos son inválidos. Por favor intente de nuevo";
    public static String NO_CONTENT = "Sin nada para mostrar";
    public static String MONEDA = " BsF";
    public static String EMPTY = "";

    public static void mensajeCorto(Context context, String mensaje) {
        toast(context, mensaje, Snackbar.LENGTH_SHORT);
    }

    public static void mensajeLargo(Context context, String mensaje) {
        toast(context, mensaje, Snackbar.LENGTH_LONG);
    }

    public static void toast(Context view, String mensaje, int duracion) {
        Toast.makeText(view, mensaje, duracion).show();
    }

    public static ProgressDialog progressConsultar(Context context) {
        return progressView(context, "Consultar...");
    }

    public static ProgressDialog progressEnvio(Context context) {
        return progressView(context, "Enviado...");
    }

    public static ProgressDialog progressView(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}

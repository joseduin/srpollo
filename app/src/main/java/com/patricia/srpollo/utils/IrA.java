package com.patricia.srpollo.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Jose on 13/11/2017.
 */

public final class IrA {

    public static void vista(Context activityActual, Class<?> activityNuevo) {
        Intent i = new Intent(activityActual, activityNuevo);
        activityActual.startActivity(i);
    }

    public static void vista(Context activityActual, Class<?> activityNuevo, String id) {
        Intent i = new Intent(activityActual, activityNuevo);
        i.putExtra("ID", id);
        activityActual.startActivity(i);
    }

    public static void vista(Context activityActual, Class<?> activityNuevo, Serializable o) {
        Intent i = new Intent(activityActual, activityNuevo);
        i.putExtra("OBJETO", o);
        activityActual.startActivity(i);
    }
}

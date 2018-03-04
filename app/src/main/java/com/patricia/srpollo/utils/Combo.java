package com.patricia.srpollo.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by Jose on 13/11/2017.
 */

public final class Combo {

    public static void cargar(Spinner sipinner, List<String> lista, Context activity) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sipinner.setAdapter(adapter);
    }

    public static void cargar(Spinner sipinner, int lista, Context activity) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, lista, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sipinner.setAdapter(adapter);
    }
}

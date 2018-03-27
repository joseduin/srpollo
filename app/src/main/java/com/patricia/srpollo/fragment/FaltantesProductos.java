package com.patricia.srpollo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.patricia.srpollo.R;
import com.patricia.srpollo.adaptador.AdaptadorFaltantes;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.modelo.Faltante;
import com.patricia.srpollo.modelo.Responsable;
import com.patricia.srpollo.utils.Combo;

import java.util.ArrayList;

/**
 * Created by patricia on 1/21/2018.
 */

public class FaltantesProductos extends Fragment {

    private RecyclerView faltantes;
    private Spinner spinner_producto;
    private EditText total;

    private ArrayList<Faltante> Faltantes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_faltantes, container, false);

        enlazarVista(v);

        cargarDatosFalsos();

        cargarLista();

        return v;
    }

    private void cargarDatosFalsos() {
        Combo.cargar(spinner_producto, Datos.PRODUCTOS, FaltantesProductos.this.getContext());
        total.setText(Datos.PRECIO + Datos.MONEDA);

        Faltantes = Datos.FALTANTES;
    }

    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(FaltantesProductos.this.getContext());
        faltantes.setLayoutManager(llm);

        AdaptadorFaltantes adapter = new AdaptadorFaltantes(Faltantes, FaltantesProductos.this.getContext());
        faltantes.setAdapter(adapter);
    }

    private void enlazarVista(View v) {
        total = v.findViewById(R.id.total);
        faltantes = v.findViewById(R.id.faltantes);
        spinner_producto = v.findViewById(R.id.spinner_producto);
    }

}

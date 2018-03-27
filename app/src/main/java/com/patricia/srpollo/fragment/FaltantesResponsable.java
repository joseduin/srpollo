package com.patricia.srpollo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.patricia.srpollo.R;
import com.patricia.srpollo.adaptador.AdaptadorResponsables;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.modelo.Responsable;
import com.patricia.srpollo.utils.Combo;

import java.util.ArrayList;

/**
 * Created by patricia on 1/21/2018.
 */

public class FaltantesResponsable extends Fragment {

    private RecyclerView responsable;
    private Spinner spinner_personal;

    private ArrayList<Responsable> responsables;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_responsable, container, false);

        enlazarVista(v);

        cargarDatosFalsos();
        cargarLista();


        return v;
    }

    private void cargarDatosFalsos() {
        Combo.cargar(spinner_personal, Datos.PERSONAL, FaltantesResponsable.this.getActivity());

        responsables = Datos.RESPONSABLES;
    }

    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(FaltantesResponsable.this.getContext());
        responsable.setLayoutManager(llm);

        AdaptadorResponsables adapter = new AdaptadorResponsables(responsables, FaltantesResponsable.this.getContext());
        responsable.setAdapter(adapter);
    }

    private void enlazarVista(View v) {
        responsable = v.findViewById(R.id.responsables);
        spinner_personal = v.findViewById(R.id.spinner_personal);
    }
}

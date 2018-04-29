package com.patricia.srpollo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.patricia.srpollo.adaptador.AdaptadorTarea;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.ITarea;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.Tarea;

import java.util.ArrayList;

public class TareaActivity extends AppCompatActivity implements IBaseActivity, ITarea {

    private Toolbar toolbar;
    private RecyclerView recycler;

    private ArrayList<Tarea> lista = new ArrayList<>();
    private AdaptadorTarea adapter;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        toolbar = findViewById(R.id.toolbar);
        recycler = findViewById(R.id.recycler);
    }

    @Override
    public void generarLayoutVertical() {


    }

    @Override
    public AdaptadorTarea crearAdaptador(ArrayList<Tarea> list) {
        return null;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorTarea adapter) {

    }
}

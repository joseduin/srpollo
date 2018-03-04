package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;

import com.patricia.srpollo.adaptador.AdaptadorAsistencia;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.modelo.Asistencia;
import com.patricia.srpollo.utils.Combo;

import java.util.ArrayList;

public class AsistenciaActivity extends AppCompatActivity implements IBaseActivity {

    private Toolbar toolbarAsistencia;
    private RecyclerView reciclerAsistencia;
    private Spinner spinner_turno;
    private Button bGuardar;

    private ArrayList<Asistencia> asistencias = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        enlazarVista();
        setSupportActionBar(toolbarAsistencia);
        getSupportActionBar().setTitle( "Asistencia" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();
        //cargarLista();

        cargarDatosFaltos();
    }

    private void cargarDatosFaltos() {
        Combo.cargar(spinner_turno, Datos.TURNO, AsistenciaActivity.this);
        asistencias = Datos.ASISTENCIA;
        cargarLista();
    }


    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(AsistenciaActivity.this);
        reciclerAsistencia.setLayoutManager(llm);

        AdaptadorAsistencia adapter = new AdaptadorAsistencia(asistencias, AsistenciaActivity.this);
        reciclerAsistencia.setAdapter(adapter);
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        spinner_turno = findViewById(R.id.spinner_turno);
        toolbarAsistencia = findViewById(R.id.toolbarAsistencia);
        reciclerAsistencia = findViewById(R.id.reciclerAsistencia);
        bGuardar = findViewById(R.id.bGuardar);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

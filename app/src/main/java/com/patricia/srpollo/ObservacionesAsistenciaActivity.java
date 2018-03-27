package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Spinner;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.utils.Combo;

public class ObservacionesAsistenciaActivity extends AppCompatActivity implements IBaseActivity{

    private Toolbar toolbarObservaciones;
    private Spinner spinner_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones_asistencia);

        enlazarVista();
        setSupportActionBar(toolbarObservaciones);
        getSupportActionBar().setTitle( "Observaciones de Asistencia" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();

        cargarDatosFaltos();
    }

    private void cargarDatosFaltos() {
        Combo.cargar(spinner_personal, Datos.PERSONAL, ObservacionesAsistenciaActivity.this);
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        spinner_personal = findViewById(R.id.spinner_personal);
        toolbarObservaciones = findViewById(R.id.toolbarObservaciones);

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

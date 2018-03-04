package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Spinner;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.utils.Combo;

public class SancionesActivity extends AppCompatActivity implements IBaseActivity {

    private Toolbar toolbarSanciones;
    private Spinner spinner_turno, spinner_cargo, spinner_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanciones);

        enlazarVista();
        setSupportActionBar(toolbarSanciones);
        getSupportActionBar().setTitle( "Sanciones" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();

        cargarDatosFaltos();
    }

    private void cargarDatosFaltos() {
        Combo.cargar(spinner_turno, Datos.TURNO, SancionesActivity.this);
        Combo.cargar(spinner_cargo, Datos.CARGO, SancionesActivity.this);
        Combo.cargar(spinner_personal, Datos.PERSONAL, SancionesActivity.this);

    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        spinner_turno = findViewById(R.id.spinner_turno);
        spinner_cargo = findViewById(R.id.spinner_cargo);
        spinner_personal = findViewById(R.id.spinner_personal);

        toolbarSanciones = findViewById(R.id.toolbarSanciones);

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

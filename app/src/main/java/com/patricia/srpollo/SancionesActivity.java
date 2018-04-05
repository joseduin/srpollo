package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IInfraccion;
import com.patricia.srpollo.modelo.Infraccion;
import com.patricia.srpollo.presentador.IInfraccionPresentador;
import com.patricia.srpollo.presentador.InfraccionPresentador;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.IrA;

import java.util.HashMap;

public class SancionesActivity extends AppCompatActivity implements IBaseActivity, IInfraccion {

    private Toolbar toolbarSanciones;
    private AutoCompleteTextView infracciones, personal;
    private Button enviar;

    private IInfraccionPresentador infraccionPresentador;
    private HashMap<String, Infraccion> mapInfraccion = new HashMap<>();
    private ArrayAdapter<String> adapterInfraccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanciones);

        enlazarVista();
        setSupportActionBar(toolbarSanciones);
        getSupportActionBar().setTitle( "Sanciones" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infraccionPresentador = new InfraccionPresentador(SancionesActivity.this, this);

        OnClick();
    }


    @Override
    public void OnClick() {
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarAlServidor();
            }
        });
    }

    private void enviarAlServidor() {

    }

    @Override
    public void enlazarVista() {
        toolbarSanciones = findViewById(R.id.toolbarSanciones);
        infracciones = findViewById(R.id.infracciones);
        personal = findViewById(R.id.personal);
        enviar = findViewById(R.id.enviar);
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

    @Override
    public void cargarComboInfraccion(String[] lista) {
        Log.d("INFRACCION", lista.length + "");
        adapterInfraccion = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, lista);
        infracciones.setAdapter(adapterInfraccion);
    }

    @Override
    public void cargarMapInfraccion(HashMap<String, Infraccion> map) {
        this.mapInfraccion = map;
    }

}

package com.patricia.srpollo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.ITrabajador;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.presentador.ITrabajadorPresentador;
import com.patricia.srpollo.presentador.TrabajadorPresentador;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.Mensaje;

import java.util.HashMap;

public class ObservacionesAsistenciaActivity extends AppCompatActivity implements IBaseActivity, ITrabajador {

    private Toolbar toolbarObservaciones;
    private CheckBox checkPersonalEmpresa, checkPersonalExterna, checkSinReemplazo;
    private EditText personaExterna, sinReemplazo;
    private AutoCompleteTextView personal;
    private Button guardar;

    private ITrabajadorPresentador iTrabajadorPresentador;
    private HashMap<String, Trabajador> mapTrabajador = new HashMap<>();
    private ArrayAdapter<String> adapterTrabajador;

    private Trabajador TRABAJADOR_ASISTENCIA;
    private int PERSONAL = -1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones_asistencia);

        enlazarVista();
        setSupportActionBar(toolbarObservaciones);
        getSupportActionBar().setTitle( "Observación de Asistencia" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iTrabajadorPresentador = new TrabajadorPresentador(ObservacionesAsistenciaActivity.this, this);

        cargarTrabajador();
        OnClick();
    }

    private void cargarTrabajador() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TRABAJADOR_ASISTENCIA = (Trabajador) extras.getSerializable("OBJETO");
        }
    }

    @Override
    public void OnClick() {
        personal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Trabajador t = mapTrabajador.get(adapterTrabajador.getItem(pos));
                Log.d("SELECT", t.getUsuario());
                PERSONAL = t.getId();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarServidor();
            }
        });
    }

    private void enviarServidor() {
        if (checkPersonalEmpresa.isChecked()) {
            if (PERSONAL == -1) {
                Mensaje.mensajeLargo(this, "Ingrese el Personal");
                return;
            }
        }
        if (checkPersonalExterna.isChecked()) {
            if (personaExterna.getText().toString().trim().isEmpty()) {
                Mensaje.mensajeLargo(this, "Ingrese el nombre y apellido del reemplazo");
                return;
            }
        }
        if (checkSinReemplazo.isChecked()) {
            if (sinReemplazo.getText().toString().trim().isEmpty()) {
                Mensaje.mensajeLargo(this, "Ingrese una observación");
                return;
            }
        }

    }

    @Override
    public void enlazarVista() {
        checkPersonalEmpresa = findViewById(R.id.checkPersonalEmpresa);
        checkPersonalExterna = findViewById(R.id.checkPersonalExterna);
        checkSinReemplazo = findViewById(R.id.checkSinReemplazo);
        personaExterna = findViewById(R.id.personaExterna);
        sinReemplazo = findViewById(R.id.sinReemplazo);
        personal = findViewById(R.id.personal);
        toolbarObservaciones = findViewById(R.id.toolbarObservaciones);
        guardar = findViewById(R.id.guardar);
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
    public void cargarComboTrabajador(String[] lista) {
        Log.d("TRABAJADR", lista.length + "");
        adapterTrabajador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, lista);
        personal.setAdapter(adapterTrabajador);
    }

    @Override
    public void cargarMapTrabajador(HashMap<String, Trabajador> map) {
        mapTrabajador = map;
    }

}

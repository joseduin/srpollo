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

import com.google.gson.Gson;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.ITrabajador;
import com.patricia.srpollo.modelo.ObservacionAsistenciaRequest;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.presentador.ITrabajadorPresentador;
import com.patricia.srpollo.presentador.TrabajadorPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.OkResponse;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String PERSONAL_STRING;
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
                String name = t.getNombre() + " " + t.getApellido () + " (" + t.getUsuario() + ") - " + t.getCargo().getDescripcion();
                PERSONAL = t.getId();
                PERSONAL_STRING = name;
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarServidor();
            }
        });
        personal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Trabajador t = mapTrabajador.get(adapterTrabajador.getItem(pos));
                PERSONAL = t.getId();
            }
        });
    }

    private void enviarServidor() {
        int tipo = -1;
        String reemplazo = "";
        if (checkPersonalEmpresa.isChecked()) {
            if (PERSONAL == -1) {
                Mensaje.mensajeLargo(this, "Ingrese el Personal");
                return;
            }
            tipo = 1;
            reemplazo = PERSONAL_STRING;
        }
        if (checkPersonalExterna.isChecked()) {
            if (personaExterna.getText().toString().trim().isEmpty()) {
                Mensaje.mensajeLargo(this, "Ingrese el nombre y apellido del reemplazo");
                return;
            }
            tipo = 2;
            reemplazo = personaExterna.getText().toString();
        }
        if (checkSinReemplazo.isChecked()) {
            if (sinReemplazo.getText().toString().trim().isEmpty()) {
                Mensaje.mensajeLargo(this, "Ingrese una observación");
                return;
            }
            tipo = 3;
            reemplazo = sinReemplazo.getText().toString();
        }

        progressDialog = Mensaje.progressEnvio(ObservacionesAsistenciaActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.existeRegistro();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        ObservacionAsistenciaRequest request = new ObservacionAsistenciaRequest(TRABAJADOR_ASISTENCIA.getId(), reemplazo, tipo);
        Call<OkResponse> call = endPointsApi.observacionRequest(request);
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<OkResponse>() {
            @Override
            public void onResponse(Call<OkResponse> call, Response<OkResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    Mensaje.mensajeLargo(ObservacionesAsistenciaActivity.this, "Asistencia Guardada");

                    // Recargar la pantalla
                    IrA.vista(ObservacionesAsistenciaActivity.this, AsistenciaActivity.class);
                    ObservacionesAsistenciaActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<OkResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(ObservacionesAsistenciaActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
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

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
import android.widget.Spinner;

import com.google.gson.Gson;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IInfraccion;
import com.patricia.srpollo.interfaz.ITrabajador;
import com.patricia.srpollo.modelo.Infraccion;
import com.patricia.srpollo.modelo.RegistroDiarioItemRequest;
import com.patricia.srpollo.modelo.SancionRequest;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.presentador.IInfraccionPresentador;
import com.patricia.srpollo.presentador.ITrabajadorPresentador;
import com.patricia.srpollo.presentador.InfraccionPresentador;
import com.patricia.srpollo.presentador.TrabajadorPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.Convertidor;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SancionesActivity extends AppCompatActivity implements IBaseActivity, IInfraccion, ITrabajador {

    private Toolbar toolbarSanciones;
    private AutoCompleteTextView infracciones, personal;
    private Button enviar;

    private IInfraccionPresentador infraccionPresentador;
    private HashMap<String, Infraccion> mapInfraccion = new HashMap<>();
    private ArrayAdapter<String> adapterInfraccion;

    private ITrabajadorPresentador iTrabajadorPresentador;
    private HashMap<String, Trabajador> mapTrabajador = new HashMap<>();
    private ArrayAdapter<String> adapterTrabajador;

    private int INFRACCION = -1;
    private int PERSONAL = -1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanciones);

        enlazarVista();
        setSupportActionBar(toolbarSanciones);
        getSupportActionBar().setTitle( "Sanciones" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infraccionPresentador = new InfraccionPresentador(SancionesActivity.this, this);
        iTrabajadorPresentador = new TrabajadorPresentador(SancionesActivity.this, this);

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

        infracciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Infraccion i = mapInfraccion.get(adapterInfraccion.getItem(pos));
                Log.d("SELECT", i.getDescripcion());
                INFRACCION = i.getId();
            }
        });

        personal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Trabajador t = mapTrabajador.get(adapterTrabajador.getItem(pos));
                Log.d("SELECT", t.getUsuario());
                PERSONAL = t.getId();
            }
        });
    }

    private void enviarAlServidor() {
        if (INFRACCION == -1 || PERSONAL == -1) {
            Mensaje.mensajeLargo(this, getResources().getString(R.string.campos_obligatorios));
            return;
        }

        progressDialog = Mensaje.progressEnvio(SancionesActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.existeRegistro();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        SancionRequest request = new SancionRequest(INFRACCION, PERSONAL);
        Call<RegistroDiarioResponse> call = endPointsApi.sancionRequest(request);
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<RegistroDiarioResponse>() {
            @Override
            public void onResponse(Call<RegistroDiarioResponse> call, Response<RegistroDiarioResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 201) {
                    Mensaje.mensajeLargo(SancionesActivity.this, "Personal Sancionado");

                    // Recargar la pantalla
                    IrA.vista(SancionesActivity.this,SancionesActivity.class);
                    SancionesActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<RegistroDiarioResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(SancionesActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
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

    @Override
    public void cargarComboTrabajador(String[] lista) {
        Log.d("TRABAJADR", lista.length + "");
        adapterTrabajador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, lista);
        personal.setAdapter(adapterTrabajador);

        if (lista.length == 0) {
            Mensaje.mensajeLargo(SancionesActivity.this, "Personal no encontrado. Tome asistencias!");
        }
    }

    @Override
    public void cargarMapTrabajador(HashMap<String, Trabajador> map) {
        mapTrabajador = map;
    }

}

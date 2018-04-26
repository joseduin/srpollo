package com.patricia.srpollo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.patricia.srpollo.adaptador.AdaptadorAsistencia;
import com.patricia.srpollo.adaptador.AdaptadorPedidoSoda;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IAsistencia;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.ITrabajador;
import com.patricia.srpollo.modelo.Asistencia;
import com.patricia.srpollo.modelo.AsistenciaRequest;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.modelo.SancionRequest;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.presentador.AsistenciaPresentador;
import com.patricia.srpollo.presentador.IAsistenciaPresentador;
import com.patricia.srpollo.presentador.IPedidoSodaPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.OkResponse;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsistenciaActivity extends AppCompatActivity implements IBaseActivity, IAsistencia, AdaptadorAsistencia.CallbackInterface {

    private Toolbar toolbarAsistencia;
    private RecyclerView reciclerAsistencia;

    private IAsistenciaPresentador iAsistenciaPresentador;
    private ArrayList<Trabajador> lista = new ArrayList<>();
    private AdaptadorAsistencia adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        enlazarVista();
        setSupportActionBar(toolbarAsistencia);
        getSupportActionBar().setTitle( "Asistencia" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iAsistenciaPresentador = new AsistenciaPresentador(AsistenciaActivity.this, this);

        OnClick();
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(AsistenciaActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerAsistencia.setLayoutManager(llm);
    }

    @Override
    public AdaptadorAsistencia crearAdaptador(ArrayList<Trabajador> list) {
        if (list.isEmpty()) {
            Mensaje.mensajeLargo(AsistenciaActivity.this, "No hay personal que tomar la asistencia");
        }

        lista = list;
        adapter = new AdaptadorAsistencia( lista, AsistenciaActivity.this );
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorAsistencia adapter) {
        reciclerAsistencia.setAdapter(adapter);
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        toolbarAsistencia = findViewById(R.id.toolbarAsistencia);
        reciclerAsistencia = findViewById(R.id.reciclerAsistencia);
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
    public void onHandleSelection(int id_trabajador) {
        enviarAsistencia(id_trabajador);
    }

    private void enviarAsistencia(int id_trabajador) {
        progressDialog = Mensaje.progressEnvio(AsistenciaActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.existeRegistro();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        AsistenciaRequest request = new AsistenciaRequest(id_trabajador);
        Call<OkResponse> call = endPointsApi.asistenciaRequest(request);
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<OkResponse>() {
            @Override
            public void onResponse(Call<OkResponse> call, Response<OkResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    Mensaje.mensajeLargo(AsistenciaActivity.this, "Asistencia Guardada");

                    // Recargar la pantalla
                    IrA.vista(AsistenciaActivity.this, AsistenciaActivity.class);
                    AsistenciaActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<OkResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(AsistenciaActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

}

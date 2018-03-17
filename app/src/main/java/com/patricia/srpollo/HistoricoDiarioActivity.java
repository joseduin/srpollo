package com.patricia.srpollo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.patricia.srpollo.adaptador.AdaptadorHistorico;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.modelo.Historico;
import com.patricia.srpollo.modelo.RegistroDiario;
import com.patricia.srpollo.modelo.RegistroDiarioItem;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.utils.Convertidor;
import com.patricia.srpollo.utils.Hora;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;
import com.patricia.srpollo.utils.Variable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricoDiarioActivity extends AppCompatActivity implements IBaseActivity, AdaptadorHistorico.CallbackInterface {

    private Toolbar toolbarHistorico;
    private RecyclerView recyclerHistorico;
    private EditText ingreso, filtro;
    private LinearLayout linearIngresos;
    private ProgressDialog progressDialog;

    private Calendar myCalendar = Calendar.getInstance();
    private ArrayList<RegistroDiarioItem> historicos = new ArrayList<>();
    private int requestPending = 0;
    private AdaptadorHistorico adaptadorReclicler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_diario);
        enlazarVista();

        setSupportActionBar(toolbarHistorico);
        getSupportActionBar().setTitle( "Historico Diario" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cargarLista();
        ocultarMostrarIngreso(View.GONE);
        OnClick();
        esconderTeclado();
        filtro.setText(Convertidor.DateEnToEs( Hora.horaActual() ));
    }

    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void ocultarMostrarIngreso(int v) {
        linearIngresos.setVisibility(v);
    }

    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(HistoricoDiarioActivity.this);
        recyclerHistorico.setLayoutManager(llm);

        adaptadorReclicler = new AdaptadorHistorico(historicos, HistoricoDiarioActivity.this);
        recyclerHistorico.setAdapter(adaptadorReclicler);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String fecha = Hora.sdf.format(myCalendar.getTime());
            filtro.setText(Convertidor.DateEnToEs( fecha ));
        }
    };

    private void buscarFecha(String fecha) {
        requestPending++;

        if (requestPending == 1) {

            progressDialog = Mensaje.progressConsultar(HistoricoDiarioActivity.this);
            progressDialog.show();

            int almacen_id = 1;

            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gson = restApiAdapter.existeRegistro();
            EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

            Call<RegistroDiarioResponse> call = endPointsApi.buscarHistoricoRegistroDiario(almacen_id, fecha);
            Log.d("RESPONSE", call.request().url().toString() + " ");

            call.enqueue(new Callback<RegistroDiarioResponse>() {
                @Override
                public void onResponse(Call<RegistroDiarioResponse> call, Response<RegistroDiarioResponse> response) {
                    progressDialog.dismiss();
                    requestPending = 0;

                    if (response.code() == 200 || response.code() == 202) {
                        RegistroDiarioResponse registroDiarioResponse = response.body();
                        RegistroDiario registroDiario = registroDiarioResponse.getRegistroDiario();

                        ingreso.setText(Convertidor.DoubleToString(registroDiario.getIngreso_efectivo(), 2) + Variable.MONEDA);
                        historicos.clear();
                        historicos.addAll(registroDiario.getItems());
                        if (historicos.size() > 0) {
                            adaptadorReclicler.updateList(historicos);
                            ocultarMostrarIngreso(View.VISIBLE);
                        } else {
                            ocultarMostrarIngreso(View.GONE);
                        }

                    } else if (response.code() == 404) {
                        historicos.clear();
                        adaptadorReclicler.updateList(historicos);
                        ocultarMostrarIngreso(View.GONE);

                        RegistroDiarioResponse registroDiarioResponse = response.body();
                        Mensaje.mensajeCorto(HistoricoDiarioActivity.this, "No existe registro asociado a la fecha buscada");
                    }
                }

                @Override
                public void onFailure(Call<RegistroDiarioResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    requestPending = 0;

                    Mensaje.mensajeCorto(HistoricoDiarioActivity.this, Mensaje.ERROR_CONEXION);
                }
            });
        }
    }

    @Override
    public void OnClick() {
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(HistoricoDiarioActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        filtro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buscarFecha( Convertidor.DateEsToEn( charSequence.toString() ));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void enlazarVista() {
        ingreso             = findViewById(R.id.ingreso);
        toolbarHistorico    = findViewById(R.id.toolbarHistorico);
        recyclerHistorico   = findViewById(R.id.recyclerHistorico);
        filtro              = findViewById(R.id.filtro);
        linearIngresos      = findViewById(R.id.linearIngresos);
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
    public void onHandleSelection(int position, String metohd) {
        // Request para eliminar el item
        // position = item.id
    }
}

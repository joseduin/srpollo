package com.patricia.srpollo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.patricia.srpollo.interfaz.IAlmacen;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.modelo.Almacen;
import com.patricia.srpollo.modelo.RegistroDiario;
import com.patricia.srpollo.modelo.RegistroDiarioItem;
import com.patricia.srpollo.modelo.RegistroDiarioRequest;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.presentador.AlmacenPresentador;
import com.patricia.srpollo.presentador.IAlmacenPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.sesion.SessionManager;
import com.patricia.srpollo.utils.Convertidor;
import com.patricia.srpollo.utils.Hora;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;
import com.patricia.srpollo.utils.Variable;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioActivity extends AppCompatActivity implements IBaseActivity, IAlmacen {

    private Button bRegistroDiario, bInventario, bAsistencia, bFaltantes, bSanciones, bListadoSodas, bTarea, bListadoProductos, cerrarSesion;
    private TextView usuario, nombreUsuario, ubicacion;
    private Spinner comboTienda;
    private ProgressDialog progressDialog;

    private IAlmacenPresentador iAlmacenPresentador;
    private HashMap<String, Almacen> mapAlmacen = new HashMap<>();
    private ArrayAdapter<String> adapterAlmacen;

    private RegistroDiario REGISTRO;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        session = new SessionManager(InicioActivity.this);
        session.checkIn();
        enlazarVista();

        setDatosToScreen();
        OnClick();
    }

    private void setDatosToScreen() {
        final Trabajador t = session.getActivo();
        nombreUsuario.setText(t.getNombre() + " " + t.getApellido());
        usuario.setText(t.getRol() == 2 ? "Trabajador" : "Administrador");

        if (t.getRol() == 1) {
            comboTienda.setVisibility(View.VISIBLE);
            ubicacion.setVisibility(View.GONE);

            iAlmacenPresentador = new AlmacenPresentador(InicioActivity.this, this);

            comboTienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    Almacen almacen = mapAlmacen.get( comboTienda.getSelectedItem().toString() );
                    t.setAlmacen(almacen);

                    session.createLoginSession(t);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}
            });

        } else {
            ubicacion.setText(t.getAlmacen().getNombre());
        }
    }

    @Override
    public void OnClick() {
        bRegistroDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                existeRegistros();
            }
        });
        bInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,InventarioActivity.class);
            }
        });
        bAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,AsistenciaActivity.class);
            }
        });
        bFaltantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,ProductosFaltantesActivity.class);
            }
        });
        bListadoSodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,PedidoSodaActivity.class);
            }
        });
        bListadoProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,ListaCompraActivity.class);
            }
        });
        bSanciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,SancionesActivity.class);
            }
        });
        bTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(InicioActivity.this,TareaActivity.class);
            }
        });
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logout();
                session.irA(LoginActivity.class);
            }
        });
    }

    public void existeRegistros() {
        progressDialog = Mensaje.progressConsultar(InicioActivity.this);
        progressDialog.show();

        int almacen_id = session.getActivo().getAlmacen_id();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.existeRegistro();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<RegistroDiarioResponse> call = endPointsApi.existe_registro(almacen_id);
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<RegistroDiarioResponse>() {
            @Override
            public void onResponse(Call<RegistroDiarioResponse> call, Response<RegistroDiarioResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200 || response.code() == 202){
                    RegistroDiarioResponse registroDiarioResponse = response.body();
                    REGISTRO = registroDiarioResponse.getRegistroDiario();

                    if (Hora.horaActual().equals(REGISTRO.getFecha())) {
                        IrA.vista(InicioActivity.this,RegistroDiarioActivity.class, REGISTRO);
                    } else {
                        double monto;
                        if (REGISTRO.getItems().size() > 0) {
                            RegistroDiarioItem item = REGISTRO.getItems().get(REGISTRO.getItems().size() - 1);
                            monto = (item.getSaldo_caja_chica() + item.getCaja_chica()) - item.getGasto_efectivo();
                        } else {
                            monto = REGISTRO.getIngreso_efectivo();
                        }
                        agregarIngreso(true,  monto);
                    }

                } else if (response.code() == 404) {
                    agregarIngreso(false, 0.0);

                    RegistroDiarioResponse registroDiarioResponse = response.body();
                    Mensaje.mensajeCorto(InicioActivity.this, registroDiarioResponse.getError().getMgs());
                }
            }

            @Override
            public void onFailure(Call<RegistroDiarioResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(InicioActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    private void agregarIngreso(final boolean viejo, final double valorViejo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(InicioActivity.this);

        builder.setTitle("Ingreso Diario");
        if (viejo) {
            builder.setMessage("Monto Anterior: " + valorViejo + Variable.MONEDA);
        }

        final EditText edit = new EditText(InicioActivity.this);
        builder.setView(edit);

        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String e = edit.getText().toString().trim();

                if (e.equals("")) {
                    Mensaje.mensajeCorto(InicioActivity.this, getResources().getString(R.string.empty));
                    return;
                }

                double monto = 0.0;
               if (viejo) {
                    monto = valorViejo + Convertidor.StringToDouble(e);
               } else {
                   monto = Convertidor.StringToDouble(e);
               }
               registroDiarioRequest(monto);
            }
        });
        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void registroDiarioRequest(double monto) {
        progressDialog = Mensaje.progressEnvio(InicioActivity.this);
        progressDialog.show();

        int almacen_id = session.getActivo().getAlmacen_id();
        double ingreso_efectivo = monto;

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.existeRegistro();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        RegistroDiarioRequest request = new RegistroDiarioRequest(ingreso_efectivo, almacen_id);
        Call<RegistroDiarioResponse> call = endPointsApi.registroRequest(request);
        call.enqueue(new Callback<RegistroDiarioResponse>() {
            @Override
            public void onResponse(Call<RegistroDiarioResponse> call, Response<RegistroDiarioResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    RegistroDiarioResponse registroDiarioResponse = response.body();
                    REGISTRO = registroDiarioResponse.getRegistroDiario();
                    IrA.vista(InicioActivity.this,RegistroDiarioActivity.class, REGISTRO);
                }
            }

            @Override
            public void onFailure(Call<RegistroDiarioResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(InicioActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void enlazarVista() {
        bRegistroDiario = findViewById(R.id.bRegistroDiario);
        bAsistencia = findViewById(R.id.bAsistencia);
        bInventario = findViewById(R.id.bInventario);
        bFaltantes = findViewById(R.id.bFaltantes);
        bSanciones = findViewById(R.id.bSanciones);
        bListadoSodas = findViewById(R.id.bListadoSodas);
        bListadoProductos = findViewById(R.id.bListadoProductos);
        usuario = findViewById(R.id.usuario);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        ubicacion = findViewById(R.id.ubicacion);
        comboTienda = findViewById(R.id.comboTienda);
        cerrarSesion = findViewById(R.id.cerrarSesion);
        bTarea = findViewById(R.id.bTarea);
    }

    @Override
    public void cargarComboAlmacen(String[] lista) {
        adapterAlmacen = new ArrayAdapter<String>(InicioActivity.this, android.R.layout.simple_spinner_item, lista);
        adapterAlmacen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboTienda.setAdapter(adapterAlmacen);
    }

    @Override
    public void cargarMapAlmacen(HashMap<String, Almacen> map) {
        mapAlmacen = map;
    }

}

package com.patricia.srpollo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IProducto;
import com.patricia.srpollo.interfaz.ISabore;
import com.patricia.srpollo.interfaz.ITurno;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.modelo.RegistroDiario;
import com.patricia.srpollo.modelo.RegistroDiarioItem;
import com.patricia.srpollo.modelo.RegistroDiarioItemRequest;
import com.patricia.srpollo.modelo.Sabore;
import com.patricia.srpollo.modelo.Turno;
import com.patricia.srpollo.presentador.IProductoPresentador;
import com.patricia.srpollo.presentador.ISaborePresentador;
import com.patricia.srpollo.presentador.ITurnoPresentador;
import com.patricia.srpollo.presentador.ProductoPresentador;
import com.patricia.srpollo.presentador.SaborePresentador;
import com.patricia.srpollo.presentador.TurnoPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.RegistroDiarioResponse;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.Convertidor;
import com.patricia.srpollo.utils.Input;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroDiarioActivity extends AppCompatActivity implements IBaseActivity, IProducto, ISabore {

    private Toolbar toolbarRegistro;
    private AutoCompleteTextView spinner_producto, soda;
    private CheckBox checkSoda;
    private LinearLayout cProducto, cSoda;
    private EditText cantidad_ingreso, gasto_efectivo, caja_chica, saldo_caja_chica;
    private Button btn_crear_registro;

    private ITurnoPresentador iRegistroDiarioPresentador;
    private HashMap<String, Turno> mapTurno = new HashMap<>();

    private IProductoPresentador iProductoPresentador;
    private HashMap<String, Producto> mapProducto = new HashMap<>();
    private ArrayAdapter<String> adapterProducto;

    private ISaborePresentador iSaborePresentador;
    private HashMap<String, Sabore> mapSabore = new HashMap<>();
    private ArrayAdapter<String> adapterSabore;

    private double COSTO;
    private int ID = -1;
    private RegistroDiario REGISTRO;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_diario);

        enlazarVista();
        setSupportActionBar(toolbarRegistro);
        getSupportActionBar().setTitle( "Registro Diario" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cargarRegistro();
        editDecimal();
        OnClick();
        esconderTeclado();
        ocultarMostrarSoda(View.GONE);
        checkeaSoda();

        iProductoPresentador = new ProductoPresentador(getApplicationContext(),this);
        iSaborePresentador = new SaborePresentador(getApplicationContext(), this);
    }

    private void cargarRegistro() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            REGISTRO = (RegistroDiario) extras.getSerializable("OBJETO");

            double saldo;
            if (REGISTRO.getItems().size() == 0) {
                saldo = REGISTRO.getIngreso_efectivo();
            } else {
                RegistroDiarioItem item = REGISTRO.getItems().get(REGISTRO.getItems().size() - 1);
                saldo = (item.getSaldo_caja_chica() + item.getCaja_chica()) - item.getGasto_efectivo();
            }
            saldo_caja_chica.setText( Convertidor.DoubleToString(saldo, 2) );
        }
    }

    private void editDecimal() {
        cantidad_ingreso.setFilters(new InputFilter[] {Input.decimalEditText(2)} );
        gasto_efectivo.setFilters(new InputFilter[] {Input.decimalEditText(2)} );
        caja_chica.setFilters(new InputFilter[] {Input.decimalEditText(2)} );
        saldo_caja_chica.setFilters(new InputFilter[] {Input.decimalEditText(2)} );
    }

    private void ocultarMostrarSoda(int valor) {
        cSoda.setVisibility(valor);
    }

    private void ocultarMostrarProducto(int valor) {
        cProducto.setVisibility(valor);
    }

    private void checkeaSoda() {
        checkSoda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
            if ( isChecked ) {
                ocultarMostrarSoda(View.VISIBLE);
                ocultarMostrarProducto(View.GONE);
            } else {
                ocultarMostrarSoda(View.GONE);
                ocultarMostrarProducto(View.VISIBLE);
            }
            }
        });
    }

    @Override
    public void cargarComboProducto(String[] productos) {
        Log.d("PRODUCTOS", productos.length + "");
        adapterProducto = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, productos);
        spinner_producto.setAdapter(adapterProducto);
    }

    @Override
    public void cargarMapProductos(HashMap<String, Producto> map) {
        this.mapProducto = map;
    }

    @Override
    public void cargarComboSabore(String[] sabores) {
        Log.d("SABORES", sabores.length + "");
        adapterSabore = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, sabores);
        soda.setAdapter(adapterSabore);
    }

    @Override
    public void cargarMapSabore(HashMap<String, Sabore> map) {
        this.mapSabore = map;
    }

    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void OnClick() {
        spinner_producto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Producto p = mapProducto.get(adapterProducto.getItem(pos));
                calcularEfectivo(p.getId(), p.getCosto());
            }
        });

        soda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Sabore s = mapSabore.get(adapterSabore.getItem(pos));
                calcularEfectivo(s.getId(), s.getSoda().getCosto());
            }
        });

        cantidad_ingreso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double cantidad = Convertidor.StringToDouble( charSequence.toString() );
                calcularCantidad(cantidad);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btn_crear_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRegistro();
            }
        });
    }

    private void guardarRegistro() {

        double cantidad = Convertidor.StringToDouble( cantidad_ingreso.getText().toString() );
        double gasto = Convertidor.StringToDouble( gasto_efectivo.getText().toString() );
        double cajaChica = Convertidor.StringToDouble( caja_chica.getText().toString() );
        double saldo = Convertidor.StringToDouble( saldo_caja_chica.getText().toString() );
        String sabore_id = "";
        String producto_id = "";

        if (ID == -1 || cantidad == 0.0 || gasto == 0.0) {
            Mensaje.mensajeLargo(this, getResources().getString(R.string.campos_obligatorios));
            return;
        }

        progressDialog = Mensaje.progressEnvio(RegistroDiarioActivity.this);
        progressDialog.show();

        if (checkSoda.isChecked())
            sabore_id = String.valueOf(ID);
        else
            producto_id = String.valueOf(ID);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.existeRegistro();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        RegistroDiarioItemRequest request = new RegistroDiarioItemRequest(producto_id, sabore_id, cantidad, gasto, cajaChica, saldo);
        Call<RegistroDiarioResponse> call = endPointsApi.registroItemRequest(request, REGISTRO.getId());
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<RegistroDiarioResponse>() {
            @Override
            public void onResponse(Call<RegistroDiarioResponse> call, Response<RegistroDiarioResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    RegistroDiarioResponse registroDiarioResponse = response.body();
                    REGISTRO = registroDiarioResponse.getRegistroDiario();
                    Mensaje.mensajeLargo(RegistroDiarioActivity.this, "Registro Guardado");

                    // Recargar la pantalla
                    IrA.vista(RegistroDiarioActivity.this,RegistroDiarioActivity.class, REGISTRO);
                    RegistroDiarioActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<RegistroDiarioResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(RegistroDiarioActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    private void calcularEfectivo(int id, double costo) {
        ID = id;
        COSTO = costo;

        double cantidad = Convertidor.StringToDouble( cantidad_ingreso.getText().toString() );
        calcularCantidad(cantidad);
    }

    private void calcularCantidad(double cantidad) {
        gasto_efectivo.setText( Convertidor.DoubleToString( (COSTO * cantidad), 2 ) );
    }

    @Override
    public void enlazarVista() {
        toolbarRegistro     = findViewById(R.id.toolbarRegistro);
        spinner_producto    = findViewById(R.id.spinner_producto);
        soda                = findViewById(R.id.soda);
        checkSoda           = findViewById(R.id.checkSoda);
        cProducto           = findViewById(R.id.cProducto);
        cSoda               = findViewById(R.id.cSoda);
        cantidad_ingreso    = findViewById(R.id.cantidad_ingreso);
        caja_chica          = findViewById(R.id.caja_chica);
        saldo_caja_chica    = findViewById(R.id.saldo_caja_chica);
        gasto_efectivo      = findViewById(R.id.gasto_efectivo);
        btn_crear_registro  = findViewById(R.id.btn_crear_registro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro_diario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.historico:
                IrA.vista(RegistroDiarioActivity.this, HistoricoDiarioActivity.class);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

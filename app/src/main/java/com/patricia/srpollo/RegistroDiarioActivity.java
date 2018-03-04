package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IProducto;
import com.patricia.srpollo.interfaz.ITurno;
import com.patricia.srpollo.modelo.Producto;
import com.patricia.srpollo.presentador.IProductoPresentador;
import com.patricia.srpollo.presentador.ITurnoPresentador;
import com.patricia.srpollo.presentador.ProductoPresentador;
import com.patricia.srpollo.presentador.TurnoPresentador;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.IrA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RegistroDiarioActivity extends AppCompatActivity implements IBaseActivity, ITurno, IProducto {

    private Toolbar toolbarRegistro;
    private TextView fecha, tProducto;
    private Spinner spinner_turno;
    private AutoCompleteTextView spinner_producto, soda;
    private CheckBox checkSoda;
    private LinearLayout cProducto;

    private ITurnoPresentador iRegistroDiarioPresentador;
    private HashMap<Integer, String> mapTurno = new HashMap<>();

    private IProductoPresentador iProductoPresentador;
    private HashMap<Integer, Producto> mapProducto = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_diario);

        enlazarVista();
        setSupportActionBar(toolbarRegistro);
        getSupportActionBar().setTitle( "Registro Diario" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();
        esconderTeclado();

        cargarDatosFaltos();

        ocultarMostrarSoda(View.GONE);


        iRegistroDiarioPresentador = new TurnoPresentador(getApplicationContext(),this);
        iProductoPresentador = new ProductoPresentador(getApplicationContext(),this);
        checkeaSoda();
    }
    private void ocultarMostrarSoda(int valor) {
        soda.setVisibility(valor);
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
    public void cargarComboTurno(String[] turnos) {
        Log.d("TURNO", turnos.length + "");
        ArrayList<String> m = new ArrayList<String>(Arrays.asList(turnos));
        Combo.cargar(spinner_turno, m, RegistroDiarioActivity.this);
    }

    @Override
    public void cargarMapTurno(HashMap<Integer, String> map) {
        this.mapTurno = map;
    }

    @Override
    public void cargarComboProducto(String[] productos) {
        Log.d("PRODUCTOS", productos.length + "");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, productos);
        spinner_producto.setAdapter(adapter);
    }

    @Override
    public void cargarMapProductos(HashMap<Integer, Producto> map) {
        this.mapProducto = map;
    }

    private void cargarDatosFaltos() {
        fecha.setText(Datos.HOY);

    }

    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        fecha = findViewById(R.id.fecha);
        toolbarRegistro = findViewById(R.id.toolbarRegistro);
        spinner_producto = findViewById(R.id.spinner_producto);
        spinner_turno = findViewById(R.id.spinner_turno);
        soda = findViewById(R.id.soda);
        checkSoda = findViewById(R.id.checkSoda);
        tProducto = findViewById(R.id.tProducto);
        cProducto = findViewById(R.id.cProducto);
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.modelo.Inventario;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity implements IBaseActivity {

    private Toolbar toolbarInventario;
    EditText  minima, inventarioExistente, precio, soda;
    CheckBox checkSoda;
    private LinearLayout cProducto;

    private ArrayList<Inventario> inventarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        enlazarVista();
        setSupportActionBar(toolbarInventario);
        getSupportActionBar().setTitle( "Inventario" );
        // boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();

        cargarDatosFaltos();

        ocultarMostrarSoda(View.GONE);

        checkeaSoda();
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

    private void ocultarMostrarSoda(int valor) {
        soda.setVisibility(valor);

    }

    private void cargarDatosFaltos() {
        inventarios = Datos.INVENTARIO;

    }



    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {

        toolbarInventario = findViewById(R.id.toolbarInventario);
        soda = findViewById(R.id.soda);
        checkSoda = findViewById(R.id.checkSoda);
        minima = findViewById(R.id.minima);
        inventarioExistente = findViewById(R.id.inventarioExistente);
        precio = findViewById(R.id.precio);
        cProducto = findViewById(R.id.cProducto);

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

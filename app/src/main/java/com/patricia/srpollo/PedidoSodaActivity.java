package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;

import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.adaptador.AdaptadorPedidoSoda;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IPedidosSodas;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.utils.Combo;

import java.util.ArrayList;

public class PedidoSodaActivity extends AppCompatActivity implements IBaseActivity, IPedidosSodas {

    private Toolbar toolbarSoda;
    private RecyclerView soda;
    private Spinner spinner_categoria, spinner_soda;
    private Button bEnviar;

    private ArrayList<PedidoSoda> pedidoSodas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_soda);

        enlazarVista();
        setSupportActionBar(toolbarSoda);
        getSupportActionBar().setTitle( "Pedidos de Sodas" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();

        cargarDatosFalsos();

        cargarLista();
    }

    private void cargarDatosFalsos() {
        Combo.cargar(spinner_soda, Datos.SODA, PedidoSodaActivity.this);
        Combo.cargar(spinner_categoria, Datos.CATEGORIA, PedidoSodaActivity.this);
       // pedidoSodas = Datos.PEDIDO_SODA;
    }

    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(PedidoSodaActivity.this);
        soda.setLayoutManager(llm);

        AdaptadorPedidoSoda adapter = new AdaptadorPedidoSoda(pedidoSodas, PedidoSodaActivity.this);
        soda.setAdapter(adapter);
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
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        spinner_categoria = findViewById(R.id.spinner_categoria);
        toolbarSoda = findViewById(R.id.toolbarSoda);
        soda = findViewById(R.id.soda);
        spinner_soda = findViewById(R.id.spinner_soda);
    }

    @Override
    public void generarLayoutVertical() {

    }

    @Override
    public AdaptadorListaCompra crearAdaptador(ArrayList<PedidoSoda> list) {
        return null;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorPedidoSoda adapter) {

    }

    @Override
    public void configuraciones(Configuracion configuracion) {

    }
}

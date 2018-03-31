package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.patricia.srpollo.adaptador.AdaptadorAsistencia;
import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IListaCompras;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.presentador.IListaCompraPresentador;
import com.patricia.srpollo.presentador.ListaCompraPresentador;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

public class ListaCompraActivity extends AppCompatActivity implements IBaseActivity, IListaCompras {

    private Toolbar toolbarListaCompra;
    private RecyclerView recyclerListaCompra;
    private Button bGuardar, bEnviar;

    private IListaCompraPresentador iListaCompraPresentador;
    private ArrayList<ListaCompra> listaCompras = new ArrayList<>();
    private AdaptadorListaCompra adapter;
    private Configuracion configuracion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        enlazarVista();
        setSupportActionBar(toolbarListaCompra);
        getSupportActionBar().setTitle( "Lista de Compras" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();
        esconderTeclado();
    }

    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(ListaCompraActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerListaCompra.setLayoutManager(llm);
    }

    @Override
    public AdaptadorListaCompra crearAdaptador(ArrayList<ListaCompra> list) {
        listaCompras = list;
        adapter = new AdaptadorListaCompra( listaCompras, ListaCompraActivity.this );
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorListaCompra adapter) {
        recyclerListaCompra.setAdapter(adapter);
    }

    @Override
    public void configuraciones(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        toolbarListaCompra = findViewById(R.id.toolbarListaCompra);
        recyclerListaCompra = findViewById(R.id.recyclerListaCompra);
        bEnviar = findViewById(R.id.bEnviar);
        bGuardar = findViewById(R.id.bGuardar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pedidos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.sincronizar:
                iListaCompraPresentador = new ListaCompraPresentador(this, getApplicationContext());
                return true;
            case R.id.enviar_whts:
                if (configuracion == null) {
                    Mensaje.mensajeCorto(ListaCompraActivity.this, "Sin nada que enviar");
                } else {

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

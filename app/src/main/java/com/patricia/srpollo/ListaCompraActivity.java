package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.patricia.srpollo.adaptador.AdaptadorAsistencia;
import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.modelo.ListaCompra;

import java.util.ArrayList;

public class ListaCompraActivity extends AppCompatActivity implements IBaseActivity{

    private Toolbar toolbarListaCompra;
    private RecyclerView recyclerListaCompra;
    private Button bGuardar, bEnviar;

    private ArrayList<ListaCompra> listaCompras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        enlazarVista();
        setSupportActionBar(toolbarListaCompra);
        getSupportActionBar().setTitle( "Lista de Compras" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        OnClick();

        cargarDatosFalsos();

        esconderTeclado();

        cargarLista();
    }
    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void cargarDatosFalsos() {
        listaCompras = Datos.LISTA_COMPRA;
    }

    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(ListaCompraActivity.this);
        recyclerListaCompra.setLayoutManager(llm);

        AdaptadorListaCompra adapter = new AdaptadorListaCompra( listaCompras, ListaCompraActivity.this);
        recyclerListaCompra.setAdapter(adapter);
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

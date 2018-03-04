package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import com.patricia.srpollo.adaptador.AdaptadorHistorico;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.modelo.Historico;
import com.patricia.srpollo.utils.IrA;

import java.util.ArrayList;

public class HistoricoDiarioActivity extends AppCompatActivity implements IBaseActivity {

    private Toolbar toolbarHistorico;
    private RecyclerView recyclerHistorico;
    private EditText ingreso;

    private ArrayList<Historico> historicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_diario);

        enlazarVista();
        setSupportActionBar(toolbarHistorico);
        getSupportActionBar().setTitle( "Historico Diario" );
        // boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        OnClick();

        //cargarLista();
        esconderTeclado();


        cargarDatosFalsos();
    }

    private void cargarDatosFalsos() {
        ingreso.setText(Datos.INGRESO + Datos.MONEDA);
        historicos = Datos.HISTORICO;

        cargarLista();
    }

    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void cargarLista() {
        LinearLayoutManager llm = new LinearLayoutManager(HistoricoDiarioActivity.this);
        recyclerHistorico.setLayoutManager(llm);

        //historicos = new ArrayList<>();

        AdaptadorHistorico adapter = new AdaptadorHistorico(historicos, HistoricoDiarioActivity.this);
        recyclerHistorico.setAdapter(adapter);
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {
        ingreso = findViewById(R.id.ingreso);
        toolbarHistorico = findViewById(R.id.toolbarHistorico);
        recyclerHistorico = findViewById(R.id.recyclerHistorico);

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

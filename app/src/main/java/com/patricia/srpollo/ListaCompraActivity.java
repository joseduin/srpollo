package com.patricia.srpollo;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.patricia.srpollo.adaptador.AdaptadorAsistencia;
import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.bd.BdContructor;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IListaCompras;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.ListaCompraRequest;
import com.patricia.srpollo.presentador.IListaCompraPresentador;
import com.patricia.srpollo.presentador.ListaCompraPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.ListaComprasResponse;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.net.URLEncoder;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaCompraActivity extends AppCompatActivity implements IBaseActivity, IListaCompras, AdaptadorListaCompra.CallbackInterface {

    private Toolbar toolbarListaCompra;
    private RecyclerView recyclerListaCompra;
    private Button bEnviar;

    private IListaCompraPresentador iListaCompraPresentador;
    private ArrayList<ListaCompra> listaCompras = new ArrayList<>();
    private AdaptadorListaCompra adapter;
    private Configuracion configuracion = null;
    private BdContructor bd;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);
        bd = new BdContructor(ListaCompraActivity.this);

        enlazarVista();
        setSupportActionBar(toolbarListaCompra);
        getSupportActionBar().setTitle( "Lista de Compras" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iListaCompraPresentador = new ListaCompraPresentador(this, ListaCompraActivity.this, true);

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
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });
    }

    private void enviarDatos() {
        progressDialog = Mensaje.progressEnvio(ListaCompraActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.listaCompras();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        for (ListaCompra l : bd.listaCompras()) {
            if (l.getCantComprada() != 0.0 && l.getCosto() != 0.0) {
                ListaCompraRequest request = new ListaCompraRequest(l.getCantComprada(), l.getTotal(), l.getCosto());
                Call<ListaComprasResponse> call = endPointsApi.listaComprasGeneralesRequest(request, l.getIdWeb());
                Log.d("RESPONSE", call.request().url().toString() + " ");

                call.enqueue(new Callback<ListaComprasResponse>() {
                    @Override
                    public void onResponse(Call<ListaComprasResponse> call, Response<ListaComprasResponse> response) {
                        if (response.code() == 200) {

                        }
                    }

                    @Override
                    public void onFailure(Call<ListaComprasResponse> call, Throwable t) {
                        Mensaje.mensajeCorto(ListaCompraActivity.this, Mensaje.ERROR_CONEXION);
                        Log.e("ERROR", t.toString() + " " + call.toString());
                    }
                });

                bd.listaCompraEliminar(l);
            }
        }

        progressDialog.dismiss();
        Mensaje.mensajeCorto(ListaCompraActivity.this, "Datos enviados al servidor");
        finish();
    }

    @Override
    public void enlazarVista() {
        toolbarListaCompra = findViewById(R.id.toolbarListaCompra);
        recyclerListaCompra = findViewById(R.id.recyclerListaCompra);
        bEnviar = findViewById(R.id.bEnviar);
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
                Log.d("SINCRONIZAR", "YA");
                iListaCompraPresentador = new ListaCompraPresentador(this, ListaCompraActivity.this, false);
                return true;
            case R.id.enviar_whts:
                if (configuracion == null) {
                    Mensaje.mensajeCorto(ListaCompraActivity.this, "Sin nada que enviar");
                } else {
                    int i = 0;
                    String copy = "LISTA DE COMPRAS \n";
                    for (ListaCompra l : bd.listaCompras())
                        copy += (i ++) + ".- " + l.getProducto() + " \n" + "\t Cantidad: " + l.getCantcomprar() + "\n\n";

                    ClipData clip = ClipData.newPlainText("text", copy);
                    ClipboardManager clipboard = (ClipboardManager)this.getSystemService(CLIPBOARD_SERVICE);
                    clipboard.setPrimaryClip(clip);

                    try {
                        PackageManager packageManager = getPackageManager();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String url = "https://api.whatsapp.com/send?phone="+ configuracion.getLista_whatsaap() +"&text=" + URLEncoder.encode(copy, "UTF-8");
                        intent.setPackage("com.whatsapp");
                        intent.setData(Uri.parse(url));
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onHandleSelection(int id, String atributo, double d) {
        Log.d("CALLBACK", id + " " + atributo + " " + d);
        ListaCompra l = bd.listaComprasPorId(id);
        switch (atributo) {
            case "cant_comprada":
                l.setCantComprada(d);
                break;
            case "cant_total":
                l.setTotal(d);
                break;
            case "costo":
                l.setCosto(d);
                break;
        }
        bd.listaCompraActualizar(l);
    }

}

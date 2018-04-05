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
import android.widget.Spinner;

import com.google.gson.Gson;
import com.patricia.srpollo.adaptador.AdaptadorListaCompra;
import com.patricia.srpollo.adaptador.AdaptadorPedidoSoda;
import com.patricia.srpollo.bd.BdContructor;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.interfaz.IBaseActivity;
import com.patricia.srpollo.interfaz.IPedidosSodas;
import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.modelo.PedidosSodasRequest;
import com.patricia.srpollo.presentador.IPedidoSodaPresentador;
import com.patricia.srpollo.presentador.ListaCompraPresentador;
import com.patricia.srpollo.presentador.PedidoSodaPresentador;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.PedidosSodasResponse;
import com.patricia.srpollo.utils.Combo;
import com.patricia.srpollo.utils.Mensaje;

import java.net.URLEncoder;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoSodaActivity extends AppCompatActivity implements IBaseActivity, IPedidosSodas, AdaptadorPedidoSoda.CallbackInterface {

    private Toolbar toolbarSoda;
    private RecyclerView soda;
    private Button bEnviar;

    private IPedidoSodaPresentador iPedidoSodaPresentador;
    private ArrayList<PedidoSoda> pedidoSodas = new ArrayList<>();
    private AdaptadorPedidoSoda adapter;
    private Configuracion configuracion = null;
    private BdContructor bd;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_soda);
        bd = new BdContructor(PedidoSodaActivity.this);

        enlazarVista();
        setSupportActionBar(toolbarSoda);
        getSupportActionBar().setTitle( "Pedidos de Sodas" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iPedidoSodaPresentador = new PedidoSodaPresentador(this, PedidoSodaActivity.this, true);


        OnClick();
        esconderTeclado();
    }

    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(PedidoSodaActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        soda.setLayoutManager(llm);
    }

    @Override
    public AdaptadorPedidoSoda crearAdaptador(ArrayList<PedidoSoda> list) {
        pedidoSodas = list;
        adapter = new AdaptadorPedidoSoda( pedidoSodas, PedidoSodaActivity.this );
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorPedidoSoda adapter) {
        soda.setAdapter(adapter);
    }

    @Override
    public void configuraciones(Configuracion configuracion) {
        this.configuracion = configuracion;
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
                iPedidoSodaPresentador = new PedidoSodaPresentador(this, PedidoSodaActivity.this, false);
                return true;
            case R.id.enviar_whts:
                if (configuracion == null) {
                    Mensaje.mensajeCorto(PedidoSodaActivity.this, "Sin nada que enviar");
                } else {
                    int i = 0;
                    String copy = "PEDIDO DE SODAS \n";
                    for (PedidoSoda l : bd.pedidosSodas())
                        copy += (i ++) + ".- " + l.getSabore() + "(" + l.getUnidades_por_paquete() + ")" + " \n" + "\t Cantidad Unidad: " + l.getCantcomprar() + "\n\n";

                    ClipData clip = ClipData.newPlainText("text", copy);
                    ClipboardManager clipboard = (ClipboardManager)this.getSystemService(CLIPBOARD_SERVICE);
                    clipboard.setPrimaryClip(clip);

                    try {
                        PackageManager packageManager = getPackageManager();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String url = "https://api.whatsapp.com/send?phone="+ configuracion.getSoda_whatsaap() +"&text=" + URLEncoder.encode(copy, "UTF-8");
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
    public void OnClick() {
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });
    }

    private void enviarDatos() {
        progressDialog = Mensaje.progressEnvio(PedidoSodaActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.pedidosSodas();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        for (PedidoSoda l : bd.pedidosSodas()) {
            if (l.getCantComprada() != 0.0 && l.getCosto() != 0.0) {
                PedidosSodasRequest request = new PedidosSodasRequest(l.getCantComprada(), l.getTotal(), l.getCosto());
                Call<PedidosSodasResponse> call = endPointsApi.listaPedidosSodasRequest(request, l.getIdWeb());
                Log.d("RESPONSE", call.request().url().toString() + " ");

                call.enqueue(new Callback<PedidosSodasResponse>() {
                    @Override
                    public void onResponse(Call<PedidosSodasResponse> call, Response<PedidosSodasResponse> response) {
                        if (response.code() == 200) {

                        }
                    }

                    @Override
                    public void onFailure(Call<PedidosSodasResponse> call, Throwable t) {
                        Mensaje.mensajeCorto(PedidoSodaActivity.this, Mensaje.ERROR_CONEXION);
                        Log.e("ERROR", t.toString() + " " + call.toString());
                    }
                });

                bd.pedidoSodaEliminar(l);
            }
        }

        progressDialog.dismiss();
        Mensaje.mensajeCorto(PedidoSodaActivity.this, "Datos enviados al servidor");
        finish();
    }

    @Override
    public void enlazarVista() {
        toolbarSoda = findViewById(R.id.toolbarSoda);
        soda = findViewById(R.id.soda);
        bEnviar = findViewById(R.id.bEnviar);
    }

    @Override
    public void onHandleSelection(int id, String atributo, double d) {
        Log.d("CALLBACK", id + " " + atributo + " " + d);
        PedidoSoda l = bd.pedidosSodasPorId(id);
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
        bd.pedidoSodaActualizar(l);
    }
}

package com.patricia.srpollo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.modelo.TrabajadorRequest;
import com.patricia.srpollo.restApi.EndPointsApi;
import com.patricia.srpollo.restApi.adapter.RestApiAdapter;
import com.patricia.srpollo.restApi.modelo.TrabajadorResponse;
import com.patricia.srpollo.sesion.SessionManager;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
// Mal login
    private EditText Usuario, contraseña;
    private Button buttonIniciar;
    private ProgressDialog progressDialog;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(LoginActivity.this);
        session.checkOut();

        enlazarVista();
        OnClick();
    }

    private void OnClick() {
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        Usuario.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    contraseña.requestFocus();
                    return true;
                }
                return false;
            }
        });

        contraseña.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    login();
                    return true;
                }
                return false;
            }
        });
    }

    private void login() {
        String usuario = Usuario.getText().toString().trim();
        String clave = contraseña.getText().toString().trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Mensaje.mensajeLargo(this, getResources().getString(R.string.campos_obligatorios));
            return;
        }

        progressDialog = Mensaje.progressConsultar(LoginActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.login();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        TrabajadorRequest request = new TrabajadorRequest(usuario, clave);
        Call<TrabajadorResponse> call = endPointsApi.login(request);
        Log.d("RESPONSE", call.request().url().toString() + " ");

        call.enqueue(new Callback<TrabajadorResponse>() {
            @Override
            public void onResponse(Call<TrabajadorResponse> call, Response<TrabajadorResponse> response) {
                progressDialog.dismiss();

                if (response.code() == 200) {
                    TrabajadorResponse res = response.body();
                    Trabajador trabajador = res.getTrabajador();
                    session.createLoginSession(trabajador);
                    IrA.vista(LoginActivity.this,InicioActivity.class);

                } else {
                    Mensaje.mensajeCorto(LoginActivity.this, Mensaje.ERROR_AUTH);
                }
            }

            @Override
            public void onFailure(Call<TrabajadorResponse> call, Throwable t) {
                progressDialog.dismiss();
                Mensaje.mensajeCorto(LoginActivity.this, Mensaje.ERROR_CONEXION);
                Log.e("ERROR", t.toString() + " " + call.toString());
            }
        });
    }

    private void enlazarVista() {
        Usuario = findViewById(R.id.Usuario);
        contraseña= findViewById(R.id.contraseña);
        buttonIniciar= findViewById(R.id.buttonIniciar);
    }
}

package com.patricia.srpollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.patricia.srpollo.datos_falsos.Datos;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

public class LoginActivity extends AppCompatActivity {

    private EditText Usuario, contraseña;
    private Button buttonIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enlazarVista();
        OnClick();
    }

    private void OnClick() {
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = Usuario.getText().toString().trim();
                String clave = contraseña.getText().toString().trim();

                if (!usuario.equals(Datos.USUARIO) && !clave.equals(Datos.CLAVE))
                    IrA.vista(LoginActivity.this,InicioActivity.class);
                else
                    Mensaje.mensajeCorto(LoginActivity.this, Mensaje.ERROR_AUTH);
            }
        });
    }

    private void enlazarVista() {
        Usuario = findViewById(R.id.Usuario);
        contraseña= findViewById(R.id.contraseña);
        buttonIniciar= findViewById(R.id.buttonIniciar);
    }
}

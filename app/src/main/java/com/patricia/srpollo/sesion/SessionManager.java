package com.patricia.srpollo.sesion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.patricia.srpollo.InicioActivity;
import com.patricia.srpollo.LoginActivity;
import com.patricia.srpollo.modelo.Almacen;
import com.patricia.srpollo.modelo.Trabajador;

/**
 * Created by Jose on 5/3/2018.
 */

public class SessionManager {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    int PRIVATE_MODE = Preferences.MODE;

    // Sharedpref file name
    private static final String PREF_NAME = Preferences.NAME;

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String KEY_ID              = "id";
    private static final String KEY_USUARIO         = "usuario";
    private static final String KEY_NOMBRE          = "nombre";
    private static final String KEY_APELLIDO        = "apellido";
    private static final String KEY_ROL             = "rol";
    private static final String KEY_ROL_DESC        = "rol_desc";
    private static final String KEY_IDENTIFICACION  = "identificacion";
    private static final String KEY_ALMACEN         = "almacen";
    private static final String KEY_ALMACEN_DESC    = "almacen_desc";
    private static final String KEY_CARGO           = "cargo";
    private static final String KEY_CARGO_ID        = "cargo_id";
    private static final String KEY_TURNO           = "turno";
    private static final String KEY_TURNO_ID        = "turno_id";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(Trabajador trabajador) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putInt(KEY_ID, trabajador.getId());
        editor.putString(KEY_USUARIO, trabajador.getUsuario());
        editor.putString(KEY_NOMBRE, trabajador.getNombre());
        editor.putString(KEY_APELLIDO, trabajador.getApellido());
        editor.putInt(KEY_ROL, trabajador.getRol());
        editor.putString(KEY_ROL_DESC, trabajador.getRol() == 1 ? "Administrador" : "Trabajador");
        editor.putString(KEY_IDENTIFICACION, trabajador.getIdentificacion());
        editor.putInt(KEY_ALMACEN, trabajador.getAlmacen().getId());
        editor.putString(KEY_ALMACEN_DESC, trabajador.getAlmacen().getNombre());

        if (trabajador.getRol() == 1) {
            editor.putInt(KEY_CARGO_ID, 0);
            editor.putString(KEY_CARGO, null);
            editor.putInt(KEY_TURNO_ID, 0);
            editor.putString(KEY_TURNO, null);
        } else {
            editor.putInt(KEY_CARGO_ID, trabajador.getCargo_id());
            editor.putString(KEY_CARGO, trabajador.getCargo().getDescripcion());
            editor.putInt(KEY_TURNO_ID, trabajador.getTurno_id());
            editor.putString(KEY_TURNO, trabajador.getTurno().getDescripcion());
        }

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkIn() {
        if(!this.isLoggedIn()) {
            irA(LoginActivity.class);
        }
    }

    public void checkOut() {
        if(this.isLoggedIn()) {
            irA(InicioActivity.class);
        }
    }

    public void irA(Class<?> c) {
        Intent i = new Intent(_context, c);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    /**
     * Get stored session data
     * */
    public Trabajador getActivo(){
        Trabajador trabajador = new Trabajador();

        trabajador.setId(pref.getInt(KEY_ID, 0));
        trabajador.setUsuario(pref.getString(KEY_USUARIO, null));
        trabajador.setNombre(pref.getString(KEY_NOMBRE, null));
        trabajador.setApellido(pref.getString(KEY_APELLIDO, null));
        trabajador.setIdentificacion(pref.getString(KEY_IDENTIFICACION, null));
        trabajador.setRol(pref.getInt(KEY_ROL, 0));
        trabajador.setAlmacen_id(pref.getInt(KEY_ALMACEN, 0));
        trabajador.setAlmacen(new Almacen(pref.getInt(KEY_ALMACEN, 0), pref.getString(KEY_ALMACEN_DESC, null)));
        trabajador.setCargo_id(pref.getInt(KEY_CARGO_ID, 0));
        trabajador.setTurno_id(pref.getInt(KEY_TURNO_ID, 0));

        return trabajador;
    }

    /**
     * Clear session details
     * */
    public void logout() {
        editor.clear();
        editor.commit();
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}

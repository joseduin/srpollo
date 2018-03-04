package com.patricia.srpollo.restApi;

/**
 * Created by Jose on 24/2/2018.
 */

public class ConstantesRestApi {

    public static final String API  = "https://srpollo-joseduin.c9users.io/";
    public static final String JSON = ".json?movil=true";
    public static final String ALMACEN = "&almacen_id={alamacen_id}";

    public static final String TURNO     = "turnos";
    public static final String URL_TURNO = API + TURNO + JSON;

    public static final String PRODUCTO     = "productos";
    public static final String URL_PRODUCTO = API + PRODUCTO + JSON;

    public static final String EXISTE_REGISTRRO     = "existe_registro";
    public static final String URL_EXISTE_REGISTRRO = API + EXISTE_REGISTRRO + JSON;

    public static final String REGISTRO_DIARIO = "registro_diarios";
    public static final String URL_REGISTRO_DIARIO = API + REGISTRO_DIARIO + JSON;

}

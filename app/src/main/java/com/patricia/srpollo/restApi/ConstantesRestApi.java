package com.patricia.srpollo.restApi;

/**
 * Created by Jose on 24/2/2018.
 */

public class ConstantesRestApi {

    public static final String API  = "https://srpollo-joseduin.c9users.io/";
    private static final String JSON = ".json?movil=true";
    private static final String ID = "/{id}";

    private static final String LOGIN = "login_access";
    public static final String URL_LOGIN = API + LOGIN + ".json";

    private static final String TURNO    = "turnos";
    public static final String URL_TURNO = API + TURNO + JSON;

    private static final String PRODUCTO    = "productos";
    public static final String URL_PRODUCTO = API + PRODUCTO + JSON;

    private static final String EXISTE_REGISTRRO    = "existe_registro";
    public static final String URL_EXISTE_REGISTRRO = API + EXISTE_REGISTRRO + JSON;

    private static final String REGISTRO_DIARIO = "registro_diarios";
    public static final String URL_REGISTRO_DIARIO = API + REGISTRO_DIARIO + JSON;

    private static final String REGISTRO_DIARIO_ITEM  = "/{registro_diario_id}/registro_diario_items";
    public static final String URL_REGISTRO_DIARIO_ITEM = API + REGISTRO_DIARIO + REGISTRO_DIARIO_ITEM + JSON;

    private static final String SABORE = "sabores";
    public static final String URL_SABORE = API + SABORE + JSON;

    private static final String LISTA_GENERAL = "lista_compras";
    public static final String URL_LISTA_GENERAL = API + LISTA_GENERAL + JSON;
    public static final String URL_LISTA_GENERAL_REQUEST = API + LISTA_GENERAL + ID + JSON;

    private static final String PEDIDOS_SODAS = "pedidos_sodas";
    public static final String URL_PEDIDOS_SODAS = API + PEDIDOS_SODAS + JSON;
    public static final String URL_PEDIDOS_SODAS_REQUEST = API + PEDIDOS_SODAS + ID + JSON;

    private static final String ALMACEN = "almacens";
    public static final String URL_ALMACEN = API + ALMACEN + JSON;

    private static final String INFRACCION = "infraccions";
    public static final String URL_INFRACCION = API + INFRACCION + JSON;

}

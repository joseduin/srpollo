package com.patricia.srpollo.datos_falsos;

import com.patricia.srpollo.modelo.Asistencia;
import com.patricia.srpollo.modelo.Faltante;
import com.patricia.srpollo.modelo.Historico;
import com.patricia.srpollo.modelo.Inventario;
import com.patricia.srpollo.modelo.Lista;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.modelo.Responsable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by patricia on 1/25/2018.
 */

public final class Datos {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyy");

    public static String USUARIO = "luis";
    public static String CLAVE = "1234";
    public static String HOY = sdf.format(new Date());
    public static ArrayList<String> PRODUCTOS = new ArrayList() {{
        add("Pollo Broaster");
        add("CocaCola 2Lts - CocaCola");
        add("CocaCola 2Lts - Zero");
        add("CocaCola 600ml - Papaya");
        add("Cebolla");
        add("Gas");
        add("Mila");
    }};
    public static ArrayList<String> CATEGORIA = new ArrayList() {{
        add("Soda 2Lts");
        add("Del Valle");
        add("Soda Personal");
    }};
    public static ArrayList<String> SODA = new ArrayList() {{
        add("CocaCola 2Lts");
        add("Aquarius 2Lts");
        add("Simba 2Lts");
    }};
    public static ArrayList<String> SABORES = new ArrayList() {{
        add("Zero");
        add("CocaCola");
        add("Naranja");
        add("Papaya");
    }};
    public static ArrayList<String> TURNO = new ArrayList() {{
        add("Mañana");
        add("Tarde");
    }};
    public static ArrayList<String> CARGO = new ArrayList() {{
        add("Mesa");
        add("Horno");
        add("Ayudante Cocina 1");
        add("Ayudante Cocina 2");
    }};
    public static ArrayList<String> PERSONAL = new ArrayList() {{
        add("Jose Duin");
        add("Patricia Freitez");
        add("Luis Cors");
        add("Maria Garcia");
    }};
    public static String MONEDA = " Bs";
    public static double INGRESO = 100.0;
    public static double PRECIO = 250.00;
    public static double SALDO = 50.0;
    public static Lista LISTA = new Lista(1, TURNO.get(1));
    public static ArrayList<Historico> HISTORICO = new ArrayList() {{
        add(new Historico(PRODUCTOS.get(0), PRECIO, SALDO, LISTA));
        add(new Historico(PRODUCTOS.get(0), PRECIO, SALDO, LISTA));
        add(new Historico(PRODUCTOS.get(0), PRECIO, SALDO, LISTA));
        add(new Historico(PRODUCTOS.get(0), PRECIO, SALDO, LISTA));
    }};
    public static ArrayList<Inventario> INVENTARIO = new ArrayList() {{
        add(new Inventario(PRODUCTOS.get(1), 10.0, 2.0,200.0));
        add(new Inventario(PRODUCTOS.get(2), 20.0, 25.0,250.0));
        add(new Inventario(PRODUCTOS.get(3), 50.0, 20.0,500.0));
        add(new Inventario(PRODUCTOS.get(5), 30.0, 20.0,500.0));
    }};
    public static ArrayList<Asistencia> ASISTENCIA = new ArrayList() {{
        add(new Asistencia(1, CARGO.get(0), false));
        add(new Asistencia(2, CARGO.get(1), false));
        add(new Asistencia(3, CARGO.get(2), false));
    }};
    public static ArrayList<PedidoSoda> PEDIDO_SODA = new ArrayList() {{
        add(new PedidoSoda(SABORES.get(0), 6, 5,0,0));
        add(new PedidoSoda(SABORES.get(1), 6, 3,0,0));
        add(new PedidoSoda(SABORES.get(2), 6, 10,0,0));
    }};
    public static  ArrayList<ListaCompra> LISTA_COMPRA = new ArrayList() {{
        add(new ListaCompra(PRODUCTOS.get(0), 10, 0, 0));
        add(new ListaCompra(PRODUCTOS.get(4), 20, 0, 0));
        add(new ListaCompra(PRODUCTOS.get(5), 10, 0, 0));
        add(new ListaCompra(PRODUCTOS.get(6), 15, 0, 0));

    }};
    public static ArrayList<Faltante> FALTANTES = new ArrayList() {{
        add(new Faltante(PRODUCTOS.get(0), 10, 200.00, 2000.00));
        add(new Faltante(PRODUCTOS.get(3), 5, 20.00, 100.00));
        add(new Faltante(PRODUCTOS.get(4), 1, 10.00, 10.00));
    }};
    public static ArrayList<Responsable> RESPONSABLES = new ArrayList() {{
        add(new Responsable(PERSONAL.get(0), 125.0));
        add(new Responsable(PERSONAL.get(1), 125.0));
    }};



}

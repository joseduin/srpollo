package com.patricia.srpollo.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;

import java.util.ArrayList;

/**
 * Created by Jose on 31/3/2018.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCompras = "CREATE TABLE " + ConstantesBaseDatos.TABLE_COMPRAS + "(" +
                ConstantesBaseDatos.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.PRODUCTO + " TEXT, " +
                ConstantesBaseDatos.CANT_COMPRAR + " REAL, " +
                ConstantesBaseDatos.CANT_PAQUETE + " REAL, " +
                ConstantesBaseDatos.UNIDAD + " TEXT, " +
                ConstantesBaseDatos.CANT_COMPRADA + " REAL, " +
                ConstantesBaseDatos.CANT_TOTAL + " REAL, " +
                ConstantesBaseDatos.COSTO + " REAL " +
                ")";

        String tablePedidos = "CREATE TABLE " + ConstantesBaseDatos.TABLE_PEDIDOS + "(" +
                ConstantesBaseDatos.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.SABORE + " TEXT, " +
                ConstantesBaseDatos.CANT_COMPRAR + " REAL, " +
                ConstantesBaseDatos.CANT_PAQUETE + " INTEGER, " +
                ConstantesBaseDatos.UNIDAD + " TEXT, " +
                ConstantesBaseDatos.CANT_COMPRADA + " REAL, " +
                ConstantesBaseDatos.CANT_TOTAL + " REAL, " +
                ConstantesBaseDatos.COSTO + " REAL, " +
                ")";

        String tableConf = "CREATE TABLE " + ConstantesBaseDatos.TABLE_CONFIGURACION + "(" +
                ConstantesBaseDatos.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.WTS_COMPRA + " TEXT, " +
                ConstantesBaseDatos.WTS_SABORE + " TEXT, " +
                ")";

        db.execSQL(tableCompras);
        db.execSQL(tablePedidos);
        db.execSQL(tableConf);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_COMPRAS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_PEDIDOS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_CONFIGURACION + "'");

        onCreate(db);
    }

    public void insertar(ContentValues contentValues, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(table,null, contentValues);
        db.close();
    }

    public void actualizar(ContentValues contentValues, String table, String table_id, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(table, contentValues, table_id + "=" + id, null);
        db.close();
    }

    public void eliminarTodos(String table) {

    }

    public void eliminar(String table, String table_id, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + table);
    }

    public ArrayList<ListaCompra> listaCommpras() {
        ArrayList<ListaCompra> reports = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_COMPRAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            reports.add( getListadoCompra(registros) );
        }

        db.close();
        return reports;
    }

    private ListaCompra getListadoCompra(Cursor registros) {
        ListaCompra listaCompra = new ListaCompra();

        listaCompra.setId(registros.getInt(0));
        listaCompra.setProducto(registros.getString(1));
        listaCompra.setCantcomprar(registros.getDouble(2));
        listaCompra.setCantPaquete(registros.getDouble(3));
        listaCompra.setUnidad(registros.getString(4));

        listaCompra.setCantComprada(registros.getDouble(5));
        listaCompra.setTotal(registros.getDouble(6));
        listaCompra.setCosto(registros.getDouble(7));

        return listaCompra;
    }

    public ArrayList<PedidoSoda> pedidosSodas() {
        ArrayList<PedidoSoda> reports = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_PEDIDOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            reports.add( getPedidosSodas(registros) );
        }

        db.close();
        return reports;
    }

    private PedidoSoda getPedidosSodas(Cursor registros) {
        PedidoSoda pedidoSoda = new PedidoSoda();

        pedidoSoda.setId(registros.getInt(0));
        pedidoSoda.setSabore(registros.getString(1));
        pedidoSoda.setCantcomprar(registros.getDouble(2));
        pedidoSoda.setUnidades_por_paquete(registros.getInt(3));
        pedidoSoda.setUnidad(registros.getString(4));

        pedidoSoda.setCantComprada(registros.getDouble(5));
        pedidoSoda.setTotal(registros.getDouble(6));
        pedidoSoda.setCosto(registros.getDouble(7));

        return pedidoSoda;
    }

    public Configuracion configuracion() {
        Configuracion reports = new Configuracion();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_CONFIGURACION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            reports = getConfiguracion(registros);
        }

        db.close();
        return reports;
    }

    private Configuracion getConfiguracion(Cursor registros) {
        Configuracion configuracion = new Configuracion();

        configuracion.setId(registros.getInt(0));
        configuracion.setLista_whatsaap(registros.getString(1));
        configuracion.setSoda_whatsaap(registros.getString(2));

        return configuracion;
    }

}

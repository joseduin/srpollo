package com.patricia.srpollo.bd;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.patricia.srpollo.modelo.Configuracion;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.modelo.PedidoSoda;

import java.util.ArrayList;

/**
 * Created by Jose on 31/3/2018.
 */

public class BdContructor {

    private BaseDatos db;

    public BdContructor(Context context) {
        this.db = new BaseDatos(context);
    }

    public ArrayList<ListaCompra> listaCompras() {
        return db.listaCommpras();
    }

    public ListaCompra listaComprasPorId(int id) {
        return db.listaCommpras(id);
    }

    public void listaCompraInsertar(ListaCompra listaCompra) {
        db.insertar(convertListaCompra(listaCompra, false), ConstantesBaseDatos.TABLE_COMPRAS);
    }

    public void listaCompraActualizar(ListaCompra listaCompra) {
        db.actualizar(convertListaCompra(listaCompra, true), ConstantesBaseDatos.TABLE_COMPRAS, ConstantesBaseDatos.ID, listaCompra.getId());
    }

    public void listaCompraEliminar(ListaCompra listaCompra) {
        db.eliminar(ConstantesBaseDatos.TABLE_COMPRAS, ConstantesBaseDatos.ID, listaCompra.getId());
    }

    public void listaCompraEliminarTodos() {
        db.eliminarTodos(ConstantesBaseDatos.TABLE_COMPRAS);
    }

    private ContentValues convertListaCompra(ListaCompra pedidoSoda, boolean id) {
        ContentValues contentValues = new ContentValues();
        if (id)
            contentValues.put(ConstantesBaseDatos.ID, pedidoSoda.getId());
        contentValues.put(ConstantesBaseDatos.ID_WEB, pedidoSoda.getIdWeb());
        contentValues.put(ConstantesBaseDatos.PRODUCTO, pedidoSoda.getProducto());
        contentValues.put(ConstantesBaseDatos.CANT_COMPRAR, pedidoSoda.getCantcomprar());
        contentValues.put(ConstantesBaseDatos.CANT_PAQUETE, pedidoSoda.getCantPaquete());
        contentValues.put(ConstantesBaseDatos.UNIDAD, pedidoSoda.getUnidad());

        contentValues.put(ConstantesBaseDatos.CANT_COMPRADA, pedidoSoda.getCantComprada());
        contentValues.put(ConstantesBaseDatos.CANT_TOTAL, pedidoSoda.getTotal());
        contentValues.put(ConstantesBaseDatos.COSTO, pedidoSoda.getCosto());

        return contentValues;
    }

    public ArrayList<PedidoSoda> pedidosSodas() {
        return db.pedidosSodas();
    }

    public PedidoSoda pedidosSodasPorId(int id) {
        return db.pedidosSodas(id);
    }

    public void pedidoSodaInsertar(PedidoSoda pedidoSoda) {
        db.insertar(convertPedidoSoda(pedidoSoda, false), ConstantesBaseDatos.TABLE_PEDIDOS);
    }

    public void pedidoSodaActualizar(PedidoSoda pedidoSoda) {
        db.actualizar(convertPedidoSoda(pedidoSoda, true), ConstantesBaseDatos.TABLE_PEDIDOS, ConstantesBaseDatos.ID, pedidoSoda.getId());
    }

    public void pedidoSodaEliminar(PedidoSoda pedidoSoda) {
        db.eliminar(ConstantesBaseDatos.TABLE_PEDIDOS, ConstantesBaseDatos.ID, pedidoSoda.getId());
    }

    public void pedidoSodaEliminarTodos() {
        db.eliminarTodos(ConstantesBaseDatos.TABLE_PEDIDOS);
    }

    private ContentValues convertPedidoSoda(PedidoSoda pedidoSoda, boolean id) {
        ContentValues contentValues = new ContentValues();
        if (id)
            contentValues.put(ConstantesBaseDatos.ID, pedidoSoda.getId());
        contentValues.put(ConstantesBaseDatos.ID_WEB, pedidoSoda.getIdWeb());
        contentValues.put(ConstantesBaseDatos.SABORE, pedidoSoda.getSabore());
        contentValues.put(ConstantesBaseDatos.CANT_COMPRAR, pedidoSoda.getCantcomprar());
        contentValues.put(ConstantesBaseDatos.CANT_PAQUETE, pedidoSoda.getUnidades_por_paquete());
        contentValues.put(ConstantesBaseDatos.UNIDAD, pedidoSoda.getUnidad());

        contentValues.put(ConstantesBaseDatos.CANT_COMPRADA, pedidoSoda.getCantComprada());
        contentValues.put(ConstantesBaseDatos.CANT_TOTAL, pedidoSoda.getTotal());
        contentValues.put(ConstantesBaseDatos.COSTO, pedidoSoda.getCosto());

        return contentValues;
    }

    public Configuracion configuracion() {
        return db.configuracion();
    }

    public void configuracionInsertar(Configuracion configuracion) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.WTS_COMPRA, configuracion.getLista_whatsaap());
        contentValues.put(ConstantesBaseDatos.WTS_SABORE, configuracion.getSoda_whatsaap());
        db.insertar(contentValues, ConstantesBaseDatos.TABLE_CONFIGURACION);
    }

}

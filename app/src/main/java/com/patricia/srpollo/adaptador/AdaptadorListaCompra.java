package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.ListaCompra;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorListaCompra extends RecyclerView.Adapter<AdaptadorListaCompra.ListaCompraViewHolder> {


        ArrayList<ListaCompra> listaCompras;

        Context context;

        public AdaptadorListaCompra(ArrayList<ListaCompra> listaCompras, Context context){
            this.listaCompras = listaCompras;
            this.context = context;
        }

    @Override
    public ListaCompraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_compra, parent, false);
        ListaCompraViewHolder pvh = new ListaCompraViewHolder(v);
        return pvh;    }

    @Override
    public void onBindViewHolder(ListaCompraViewHolder holder, int position) {
        ListaCompra listaCompra = listaCompras.get(position);

        holder.producto.setText(listaCompra.getProducto());
        holder.cantComprada.setText(listaCompra.getCantComprada()+"");
        if (listaCompra.getCantcomprar() != 0)
            holder.cantComprar.setText(listaCompra.getCantcomprar()+"");
        if (listaCompra.getPrecio() != 0)
            holder.precio.setText(listaCompra.getPrecio()+"");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaCompras.size();
    }

    public static class ListaCompraViewHolder extends RecyclerView.ViewHolder {
        EditText producto, cantComprar, cantComprada, precio, costo;

        ListaCompraViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.producto);
            cantComprar = itemView.findViewById(R.id.cantComprar);
            cantComprada = itemView.findViewById(R.id.cantComprada);
            precio = itemView.findViewById(R.id.precio);
            costo = itemView.findViewById(R.id.costo);

        }
    }
}

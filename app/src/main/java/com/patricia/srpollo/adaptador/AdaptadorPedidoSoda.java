package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.PedidoSoda;

import java.util.ArrayList;

/**
 * Created by patricia on 1/25/2018.
 */

public class AdaptadorPedidoSoda extends RecyclerView.Adapter<AdaptadorPedidoSoda.PedidoSodaViewHolder> {

    ArrayList<PedidoSoda> pedidoSodas;

    Context context;

    public AdaptadorPedidoSoda(ArrayList<PedidoSoda> pedidoSodas, Context context){
        this.pedidoSodas = pedidoSodas;
        this.context = context;
    }

    @Override
    public PedidoSodaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pedido_soda, parent, false);
        PedidoSodaViewHolder pvh = new PedidoSodaViewHolder(v);
        return pvh;     }

    @Override
    public void onBindViewHolder(PedidoSodaViewHolder holder, int position) {
        PedidoSoda pedidoSoda = pedidoSodas.get(position);
/*
        holder.producto.setText(pedidoSoda.getProducto());

        if (pedidoSoda.getUds() != 0)
            holder.uds.setText(pedidoSoda.getUds() +"");
        if (pedidoSoda.getCantUds() != 0)
            holder.cantUds.setText(pedidoSoda.getCantUds() +"");
        if (pedidoSoda.getTotalPaq() != 0)
            holder.totalPaq.setText(pedidoSoda.getTotalPaq() +"");
        if (pedidoSoda.getCantpaq() != 0)
            holder.cantpaq.setText(pedidoSoda.getCantpaq() +"");
            */
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return pedidoSodas.size();
    }

    public static class PedidoSodaViewHolder extends RecyclerView.ViewHolder {
        EditText producto, uds, cantpaq, totalPaq, cantUds;

        PedidoSodaViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.producto);
            uds = itemView.findViewById(R.id.uds);
            cantpaq = itemView.findViewById(R.id.cantPaq);
            totalPaq = itemView.findViewById(R.id.totalPaq);
            cantUds = itemView.findViewById(R.id.cantUds);

        }
    }
}

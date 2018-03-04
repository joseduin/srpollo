package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.Faltante;

import java.util.ArrayList;

/**
 * Created by patricia on 1/25/2018.
 */

public class AdaptadorFaltantes extends RecyclerView.Adapter<AdaptadorFaltantes.FaltanteViewHolder>{

    ArrayList<Faltante> faltantes;

    Context context;

    public AdaptadorFaltantes(ArrayList<Faltante> asistencias, Context context){
        this.faltantes = asistencias;
        this.context = context;
    }


    @Override
    public FaltanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_faltantes, parent, false);
        FaltanteViewHolder pvh = new FaltanteViewHolder(v);
        return pvh;    }

    @Override
    public void onBindViewHolder(FaltanteViewHolder holder, int position) {
        Faltante faltante = faltantes.get(position);

        holder.descripcion.setText(faltante.getDescripcion());
        holder.cantidad.setText(faltante.getCantidad()+"");
        holder.precio.setText(faltante.getPrecio()+"");
        holder.subTotal.setText(faltante.getSubTotal()+"");

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return faltantes.size();
    }

    public static class FaltanteViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion, precio, subTotal;
        EditText cantidad;


        FaltanteViewHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.descripcion);
            cantidad = itemView.findViewById(R.id.cantidad);
            precio = itemView.findViewById(R.id.precio);
            subTotal = itemView.findViewById(R.id.subTotal);

        }
    }
}

package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.Historico;
import com.patricia.srpollo.utils.IrA;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorHistorico extends RecyclerView.Adapter<AdaptadorHistorico.HistoricoViewHolder> {

    ArrayList<Historico> historicos;

    Context context;

    public AdaptadorHistorico(ArrayList<Historico> historicos, Context context){
        this.historicos = historicos;
        this.context = context;
    }

    @Override
    public HistoricoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_historico_diario, parent, false);
            HistoricoViewHolder pvh = new HistoricoViewHolder(v);
            return pvh;
    }

    @Override
    public void onBindViewHolder(HistoricoViewHolder holder, int position) {

        Historico historico = historicos.get(position);
        holder.gasto.setText(historico.getGastoEfectivo()+"");
        holder.producto.setText(historico.getProducto());
        holder.saldo.setText(String.valueOf(historico.getSaldoEfectivo()));
        holder.turno.setText(historico.getLista().getNombre());



        holder.imageEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    //IrA.vista(context, ObservacionesAsistenciaActivity.class);
                }
            });
    }

    @Override
    public int getItemCount() {
        return historicos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class HistoricoViewHolder extends RecyclerView.ViewHolder {
        TextView producto, gasto, saldo, turno;
        ImageButton imageEliminar;

        HistoricoViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.producto);
            gasto = itemView.findViewById(R.id.gasto);
            saldo = itemView.findViewById(R.id.saldo);
            turno = itemView.findViewById(R.id.turno);
            imageEliminar = itemView.findViewById(R.id.imageEliminar);

        }
    }
}

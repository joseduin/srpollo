package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.patricia.srpollo.ObservacionesAsistenciaActivity;
import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.Tarea;
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorTarea extends RecyclerView.Adapter<AdaptadorTarea.TareaViewHolder> {

    private ArrayList<Tarea> lista;
    private Context context;

    public AdaptadorTarea(ArrayList<Tarea> lista, Context context){
        this.lista = lista;
        this.context = context;
    }

    @Override
    public TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tarea, parent, false);
        TareaViewHolder pvh = new TareaViewHolder(v);
        return pvh;    }

    @Override
    public void onBindViewHolder(final TareaViewHolder holder, int position) {
        final Tarea t = lista.get(position);

        holder.tarea.setText(t.getTarea());
        holder.estado.setText(t.getEstado());
        holder.primero.setChecked(t.isPrimero());
        holder.segundo.setChecked(t.isSegundo());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        private TextView tarea, estado;
        private CheckBox primero, segundo;

        TareaViewHolder(View itemView) {
            super(itemView);
            tarea = itemView.findViewById(R.id.tarea);
            estado = itemView.findViewById(R.id.estado);
            primero = itemView.findViewById(R.id.primero);
            segundo = itemView.findViewById(R.id.segundo);
        }
    }

}

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
import com.patricia.srpollo.modelo.Asistencia;
import com.patricia.srpollo.utils.IrA;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorAsistencia extends RecyclerView.Adapter<AdaptadorAsistencia.AsisteciaViewHolder> {

    ArrayList<Asistencia> asistencias;

    Context context;

    public AdaptadorAsistencia(ArrayList<Asistencia> asistencias, Context context){
        this.asistencias = asistencias;
        this.context = context;
    }

    @Override
    public AsisteciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_asistencia, parent, false);
        AsisteciaViewHolder pvh = new AsisteciaViewHolder(v);
        return pvh;    }

    @Override
    public void onBindViewHolder(AsisteciaViewHolder holder, int position) {
        Asistencia asistencia = asistencias.get(position);

        holder.textCargo.setText(asistencia.getCargo());
        holder.bObservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(context, ObservacionesAsistenciaActivity.class);
            }
        });;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return asistencias.size();
    }

    public static class AsisteciaViewHolder extends RecyclerView.ViewHolder {
        TextView textCargo;
        CheckBox checkAsistio;
        ImageButton bObservacion;

        AsisteciaViewHolder(View itemView) {
            super(itemView);
            textCargo = itemView.findViewById(R.id.textCargo);
            checkAsistio = itemView.findViewById(R.id.checkAsistio);
            bObservacion = itemView.findViewById(R.id.bObservacion);
        }
    }

}

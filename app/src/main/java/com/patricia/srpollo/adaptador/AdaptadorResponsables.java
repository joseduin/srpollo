package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.Responsable;

import java.util.ArrayList;

/**
 * Created by patricia on 1/25/2018.
 */

public class AdaptadorResponsables extends RecyclerView.Adapter<AdaptadorResponsables.ResponsableViewHolder> {

    ArrayList<Responsable> responsables;

    Context context;

    public AdaptadorResponsables(ArrayList<Responsable> responsables, Context context){
        this.responsables = responsables;
        this.context = context;
    }

    @Override
    public ResponsableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_responsable, parent, false);
        ResponsableViewHolder pvh = new ResponsableViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ResponsableViewHolder holder, int position) {

        Responsable responsable = responsables.get(position);

        holder.eResponsable.setText(responsable.getNombre());
        holder.eMulta.setText(responsable.getMulta()+"");

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return responsables.size();
    }


    public static class ResponsableViewHolder extends RecyclerView.ViewHolder {
        TextView eResponsable, eMulta;

        ResponsableViewHolder(View itemView) {
            super(itemView);
            eResponsable = itemView.findViewById(R.id.eResponsable);
            eMulta = itemView.findViewById(R.id.eMulta);
        }
    }


}

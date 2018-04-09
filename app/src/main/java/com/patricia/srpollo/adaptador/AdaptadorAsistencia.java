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
import com.patricia.srpollo.modelo.Trabajador;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorAsistencia extends RecyclerView.Adapter<AdaptadorAsistencia.AsisteciaViewHolder> {

    private ArrayList<Trabajador> asistencias;
    private Context context;
    private CallbackInterface mCallback;

    public interface CallbackInterface {
        void onHandleSelection(int id_trabajador);
    }

    public AdaptadorAsistencia(ArrayList<Trabajador> asistencias, Context context){
        this.asistencias = asistencias;
        this.context = context;
        this.mCallback = (CallbackInterface) context;
    }

    @Override
    public AsisteciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_asistencia, parent, false);
        AsisteciaViewHolder pvh = new AsisteciaViewHolder(v);
        return pvh;    }

    @Override
    public void onBindViewHolder(final AsisteciaViewHolder holder, int position) {
        final Trabajador t = asistencias.get(position);

        String name = t.getNombre() + " " + t.getApellido () + " (" + t.getUsuario() + ") - " + t.getCargo().getDescripcion();
        holder.textCargo.setText( name );
        holder.bObservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrA.vista(context, ObservacionesAsistenciaActivity.class, t);
            }
        });
        holder.bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkAsistio.isChecked()) {
                    // callback
                    if (mCallback != null) {
                        mCallback.onHandleSelection(t.getId());
                    }
                } else {
                    Mensaje.mensajeCorto(context, "Marque la asistencia del personal");
                }
            }
        });
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
        private TextView textCargo;
        private CheckBox checkAsistio;
        private ImageButton bObservacion, bsend;

        AsisteciaViewHolder(View itemView) {
            super(itemView);
            textCargo = itemView.findViewById(R.id.textCargo);
            checkAsistio = itemView.findViewById(R.id.checkAsistio);
            bObservacion = itemView.findViewById(R.id.bObservacion);
            bsend = itemView.findViewById(R.id.bsend);
        }
    }

}

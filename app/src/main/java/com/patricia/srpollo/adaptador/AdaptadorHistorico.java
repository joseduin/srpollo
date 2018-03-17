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
import com.patricia.srpollo.modelo.RegistroDiarioItem;
import com.patricia.srpollo.utils.Convertidor;
import com.patricia.srpollo.utils.IrA;
import com.patricia.srpollo.utils.Variable;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorHistorico extends RecyclerView.Adapter<AdaptadorHistorico.HistoricoViewHolder> {

    private ArrayList<RegistroDiarioItem> historicos;
    private Context context;

    private CallbackInterface mCallback;

    public interface CallbackInterface {
        void onHandleSelection(int position, String metohd);
    }


    public AdaptadorHistorico(ArrayList<RegistroDiarioItem> historicos, Context context){
        this.historicos = historicos;
        this.context = context;

        this.mCallback = (CallbackInterface) context;
    }

    @Override
    public HistoricoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_historico_diario, parent, false);
            HistoricoViewHolder pvh = new HistoricoViewHolder(v);
            return pvh;
    }

    @Override
    public void onBindViewHolder(HistoricoViewHolder holder, int position) {
        final RegistroDiarioItem item = historicos.get(position);

        if (item.getProducto_id() == 0) {
            String split = " - ";
            holder.tProducto.setText("Soda");
            holder.producto.setText( item.getSabore().getSoda().getCategoria().getDescripcion() + split +
                    item.getSabore().getSoda().getDescripcion() + split +
                    item.getSabore().getDescripcion() );
        } else {
            holder.tProducto.setText("Producto");
            holder.producto.setText( item.getProducto().getDescripcion() );
        }

        holder.gasto.setText(Convertidor.DoubleToString( item.getGasto_efectivo(), 2 ) + Variable.MONEDA);
        holder.caja_chica.setText( Convertidor.DoubleToString( item.getCaja_chica(), 2 ) + Variable.MONEDA );
        holder.saldo.setText( Convertidor.DoubleToString( item.getSaldo_caja_chica(), 2 ) + Variable.MONEDA );
        holder.turno.setText( item.getTurno().getDescripcion() );

        if ((historicos.size() - 1) == position) {
            holder.imageEliminar.setVisibility(View.VISIBLE);
            holder.imageEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onHandleSelection(item.getId(), "borrar");
                }
            });
        } else {
            holder.imageEliminar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return historicos.size();
    }

    public void updateList(ArrayList<RegistroDiarioItem> historicos) {
        this.historicos = historicos;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class HistoricoViewHolder extends RecyclerView.ViewHolder {
        TextView producto, gasto, saldo, turno, tProducto, caja_chica;
        ImageButton imageEliminar;

        HistoricoViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.producto);
            gasto = itemView.findViewById(R.id.gasto);
            saldo = itemView.findViewById(R.id.saldo);
            turno = itemView.findViewById(R.id.turno);
            tProducto = itemView.findViewById(R.id.tProducto);
            imageEliminar = itemView.findViewById(R.id.imageEliminar);
            caja_chica = itemView.findViewById(R.id.caja_chica);
        }
    }
}

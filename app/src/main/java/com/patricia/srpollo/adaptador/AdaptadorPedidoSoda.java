package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.PedidoSoda;
import com.patricia.srpollo.utils.Input;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

/**
 * Created by patricia on 1/25/2018.
 */

public class AdaptadorPedidoSoda extends RecyclerView.Adapter<AdaptadorPedidoSoda.PedidoSodaViewHolder> {

    private ArrayList<PedidoSoda> pedidoSodas;
    private Context context;
    private CallbackInterface mCallback;

    public interface CallbackInterface {
        void onHandleSelection(int id, String atributo, double d);
    }


    public AdaptadorPedidoSoda(ArrayList<PedidoSoda> pedidoSodas, Context context){
        this.pedidoSodas = pedidoSodas;
        this.context = context;
        this.mCallback = (CallbackInterface) context;    }

    @Override
    public PedidoSodaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pedido_soda, parent, false);
        PedidoSodaViewHolder pvh = new PedidoSodaViewHolder(v);
        return pvh;     }

    @Override
    public void onBindViewHolder(final PedidoSodaViewHolder holder, int position) {
        final PedidoSoda pedidoSoda = pedidoSodas.get(position);

        holder.producto.setText(pedidoSoda.getSabore());
        holder.uds.setText(String.valueOf(pedidoSoda.getUnidades_por_paquete()));

        if (pedidoSoda.getCantComprada() != 0.0) {
            holder.cantComprada.setText( String.valueOf(pedidoSoda.getCantComprada()) );
            holder.totalPaq.setText( String.valueOf(pedidoSoda.getTotal()) );
        }
        if (pedidoSoda.getCosto() != 0.0) {
            holder.costo.setText( String.valueOf(pedidoSoda.getCosto()) );
        }

        holder.cantComprada.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    try {
                        double comprado = Double.parseDouble(charSequence.toString());
                        holder.totalPaq.setText(String.valueOf(pedidoSoda.getUnidades_por_paquete() * comprado));
                        if (mCallback != null) {
                            mCallback.onHandleSelection(pedidoSoda.getId(), "cant_comprada", comprado);
                            mCallback.onHandleSelection(pedidoSoda.getId(), "cant_total", pedidoSoda.getUnidades_por_paquete() * comprado);
                        }
                    } catch (Exception e) {
                        Mensaje.mensajeCorto(context, "El formato valido es 0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.costo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    try {
                        if (mCallback != null) {
                            mCallback.onHandleSelection(pedidoSoda.getId(), "costo", Double.parseDouble(charSequence.toString()));
                        }
                    } catch (Exception e) {
                        Mensaje.mensajeCorto(context, "El formato valido es 0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        EditText producto, uds, cantComprada, totalPaq, costo;

        PedidoSodaViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.producto);
            uds = itemView.findViewById(R.id.uds);
            cantComprada = itemView.findViewById(R.id.cantComprada);
            totalPaq = itemView.findViewById(R.id.totalPaq);
            costo = itemView.findViewById(R.id.costo);

            // Conf Inputs
            cantComprada.setFilters(new InputFilter[] {Input.decimalEditText(1)} );
            costo.setFilters(new InputFilter[] {Input.decimalEditText(2)} );
        }
    }
}

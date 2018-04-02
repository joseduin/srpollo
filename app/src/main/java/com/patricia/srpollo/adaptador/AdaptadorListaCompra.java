package com.patricia.srpollo.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.patricia.srpollo.R;
import com.patricia.srpollo.modelo.ListaCompra;
import com.patricia.srpollo.utils.Input;
import com.patricia.srpollo.utils.Mensaje;

import java.util.ArrayList;

/**
 * Created by patricia on 1/24/2018.
 */

public class AdaptadorListaCompra extends RecyclerView.Adapter<AdaptadorListaCompra.ListaCompraViewHolder> {

    private ArrayList<ListaCompra> listaCompras;
    private Context context;
    private CallbackInterface mCallback;

    public interface CallbackInterface {
        void onHandleSelection(int id, String atributo, double d);
    }

    public AdaptadorListaCompra(ArrayList<ListaCompra> listaCompras, Context context){
        this.listaCompras = listaCompras;
        this.context = context;
        this.mCallback = (CallbackInterface) context;
    }

    @Override
    public ListaCompraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_compra, parent, false);
        ListaCompraViewHolder pvh = new ListaCompraViewHolder(v);
        return pvh;    }

    @Override
    public void onBindViewHolder(final ListaCompraViewHolder holder, int position) {
        final ListaCompra listaCompra = listaCompras.get(position);

        holder.producto.setText(listaCompra.getProducto());
        holder.cantComprar.setText(listaCompra.getCantcomprar()+ " " + listaCompra.getUnidad());

        if (listaCompra.getCantComprada() != 0.0) {
            holder.cantComprada.setText( String.valueOf(listaCompra.getCantComprada()) );
            holder.cantTotalComrada.setText( String.valueOf(listaCompra.getTotal()) );
        }
        if (listaCompra.getCosto() != 0.0) {
            holder.costo.setText( String.valueOf(listaCompra.getCosto()) );
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
                        holder.cantTotalComrada.setText(String.valueOf(listaCompra.getCantPaquete() * comprado));
                        if (mCallback != null) {
                            mCallback.onHandleSelection(listaCompra.getId(), "cant_comprada", comprado);
                            mCallback.onHandleSelection(listaCompra.getId(), "cant_total", listaCompra.getCantPaquete() * comprado);
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
                            mCallback.onHandleSelection(listaCompra.getId(), "costo", Double.parseDouble(charSequence.toString()));
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
        return listaCompras.size();
    }

    public static class ListaCompraViewHolder extends RecyclerView.ViewHolder {

        private EditText producto, cantComprar, cantComprada, cantTotalComrada, costo;

        ListaCompraViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.producto);
            cantComprar = itemView.findViewById(R.id.cantComprar);
            cantComprada = itemView.findViewById(R.id.cantComprada);
            cantTotalComrada = itemView.findViewById(R.id.cantTotalComrada);
            costo = itemView.findViewById(R.id.costo);

            // Conf Inputs
            cantComprada.setFilters(new InputFilter[] {Input.decimalEditText(1)} );
            costo.setFilters(new InputFilter[] {Input.decimalEditText(2)} );
        }

    }
}

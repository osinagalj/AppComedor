

package com.example.view.MyOrders.Fragment.Confirmados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;

import java.util.ArrayList;

import Model.Order;


public class AdapterConfirmed extends RecyclerView.Adapter<com.example.view.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Order> model;

    private View.OnClickListener listener;

    public AdapterConfirmed(Context context, ArrayList<Order> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public com.example.view.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_pendiente_new, parent, false);
        view.setOnClickListener(this);
        return new com.example.view.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.view.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder holder, int position) {
        String product_id = String.valueOf(model.get(position).getId());
        String product_description = model.get(position).getDescripcion();
        String product_time = model.get(position).getTime();
        String product_price = String.valueOf(model.get(position).getPrice());

        holder.product_id.setText("#"+product_id);
        holder.product_time.setText(product_time);
        holder.product_description.setText(product_description);
        holder.product_price.setText(product_price);
    }


    @Override
    public int getItemCount() {
        return model.size();
    }


    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_id, product_time,product_description,product_price;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.row_pending_order_new_label_product_name);
            product_time = itemView.findViewById(R.id.row_pending_order_new_label_product_time);
            product_description = itemView.findViewById(R.id.row_pending_order_new_label_product_description);
            product_price = itemView.findViewById(R.id.row_pending_order_new_label_product_price);
        }
    }
}

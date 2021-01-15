package com.example.view.MyOrders.Fragment.Pendientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;

import java.util.ArrayList;

import DataBase.Repositorio;
import ModeloGian.Order;

public class AdapterPendientes extends RecyclerView.Adapter<com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Order> model;
    Context context;
    private int count = 0;
    Button button;
    private View.OnClickListener listener;
    FragmentPendientes fragmentPendientes;

    public AdapterPendientes(Context context, ArrayList<Order> model,FragmentPendientes fragmentPendientes) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        this.fragmentPendientes = fragmentPendientes;
        this.context = context;
    }


    @NonNull
    @Override
    public com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_pendiente_new, parent, false);
        view.setOnClickListener(this);
        button = (Button) view.findViewById(R.id.row_pending_order_new_button_remove);

        return new com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder holder, final int position) {
        String product_id = String.valueOf(model.get(position).getId());
        String product_description = model.get(position).getDescripcion();
        String product_time = model.get(position).getTime();
        String product_price = String.valueOf(model.get(position).getPrice());

        holder.product_id.setText("#"+product_id);
        holder.product_time.setText(product_time);
        holder.product_description.setText(product_description);
        holder.product_price.setText(product_price);

        //To remove orders
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //TODO ESTO ELIMINA PERO A VECES TIRA ERROR POR LA POSICION
               // Repositorio.repo.removePendOrder(model.get(position).getId()); //TODO ACA HAY QUE PASARLE EL NRO DE ORDEN PARA ELIMINAR, NO LA POS
                //model.remove(position);
                //notifyDataSetChanged();
                //notifyItemRemoved(position); // esto hace andar mal a la posicion

                Repositorio.repo.removePendOrder(model.get(0).getId()); //no las borra porque ya no las cargo desde el repo este
                model.remove(0);
                notifyItemRemoved(0);

            }
        });

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
        TextView product_id, product_description, product_price,product_time;
        ImageView product_img;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.row_pending_order_new_label_product_name);
            product_time = itemView.findViewById(R.id.row_pending_order_new_label_product_time);
            product_description = itemView.findViewById(R.id.row_pending_order_new_label_product_description);
            product_price = itemView.findViewById(R.id.row_pending_order_new_label_product_price);
            //product_img = itemView.findViewById(R.id.row_pending_order_imageView_product_img);


        }
    }
}

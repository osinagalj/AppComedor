package com.example.view.MyOrders.Fragment.Pendientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    ImageButton button;
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
        View view = inflater.inflate(R.layout.row_order_pendiente, parent, false);
        view.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.row_pending_order_button_remove);

        return new com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder holder, final int position) {
        String product_name = String.valueOf(model.get(position).getId());
        int product_nro = model.get(position).getId();
        //String product_price = model.get(position).getProductPrice(); // Obetener el precio de la orden
        //int produc_amount = model.get(position).get;
        int produc_amount = 2;
         //TODO ACA SE VINCULA LA VISTA CON EL MODELO
        //int product_img = model.get(position).getProductImg();

        holder.product_name.setText("#"+product_nro);
        holder.product_description.setText(product_name);
        //holder.product_price.setText("Total: " + product_price);
        String unidad = " Unidad";
        if(produc_amount>1)
            unidad = " Unidades";
        String amount_text = produc_amount + unidad;
        holder.product_amount.setText(amount_text); //TODO si la cantidad es 1 poner unidad sino unidades
        //holder.product_img.setImageResource(product_img);


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

                Repositorio.repo.removePendOrder(model.get(0).getId());
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
        TextView product_name, product_description, product_price,product_amount;
        ImageView product_img;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
        //TODO ACA SE VINCULA LA VISTA CON EL MODELO
            product_name = itemView.findViewById(R.id.row_pending_order_label_product_name);
            product_description = itemView.findViewById(R.id.row_pending_order_label_product_description);
            product_price = itemView.findViewById(R.id.row_pending_order_label_product_price);
            product_img = itemView.findViewById(R.id.row_pending_order_imageView_product_img);
            product_amount = itemView.findViewById(R.id.row_pending_order_label_amount);

        }
    }
}

package com.example.view.Food.Carrito;

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

import ModeloGian.Product;

public class AdapterCarrito extends RecyclerView.Adapter<com.example.view.Food.Carrito.AdapterCarrito.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Product> model;
    Context context;
    private int count = 0;
    ImageButton button;
    private View.OnClickListener listener;


    public AdapterCarrito(Context context, ArrayList<Product> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;

        this.context = context;
    }


    @NonNull
    @Override
    public com.example.view.Food.Carrito.AdapterCarrito.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_pendiente, parent, false);
        view.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.row_pending_order_button_remove);

        return new com.example.view.Food.Carrito.AdapterCarrito.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.view.Food.Carrito.AdapterCarrito.ViewHolder holder, final int position) {
        String product_name = model.get(position).getName();
        int product_nro = model.get(position).getId();
        String product_price = String.valueOf(model.get(position).getPrice());
        int produc_amount = model.get(position).getStock(); //TODO ACA HAY QUE PASAR LA CANTIDAD DE CADA PRODCUTO TMB

        //TODO ACA SE VINCULA LA VISTA CON EL MODELO
        int product_img = model.get(position).getImgId();

        holder.product_name.setText("#"+product_nro);
        holder.product_description.setText(product_name);
        holder.product_price.setText("Total: " + product_price);
        String unidad = " Unidad";
        if(produc_amount>1)
            unidad = " Unidades";
        String amount_text = produc_amount + unidad;
        holder.product_amount.setText(amount_text);
        holder.product_img.setImageResource(product_img);


        //To remove orders
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //Repositorio.repo.removePendOrder(model.get(position).getId()); //TODO ACA HAY QUE PASARLE EL NRO DE ORDEN PARA ELIMINAR, NO LA POS
                //TODO ELIMINAR PRODUCTO DE LA ORDEN
                model.remove(position);
                notifyDataSetChanged();
                //notifyItemRemoved(position); // esto hace andar mal a la posicion
                notifyItemRangeChanged(position,getItemCount());

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

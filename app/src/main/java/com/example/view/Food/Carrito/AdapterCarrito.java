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

import com.example.view.BackEnd;
import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

import Model.Product;

public class AdapterCarrito extends RecyclerView.Adapter<com.example.view.Food.Carrito.AdapterCarrito.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    List<Product> model = BackEnd.getLoggedUser().getCartProducts();
    Context context;
    private int count = 0;
    ImageButton button;
    private View.OnClickListener listener;


    public AdapterCarrito(Context context, ArrayList<Product> model) {//TODO borrar el segundo parametro
        this.inflater = LayoutInflater.from(context);
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
        int produc_amount = BackEnd.getLoggedUser().getCartProductAmount(model.get(position));
        int product_img = model.get(position).getImgId();

        holder.product_name.setText("#"+product_nro);
        holder.product_description.setText(product_name);
        holder.product_price.setText("Precio x Unidad: " + product_price);
        float tot = model.get(position).getPrice() * produc_amount;
        holder.product_total_price.setText("Total: " + tot );


        String unidad = " Unidad";
        if(produc_amount>1)
            unidad = " Unidades";
        String amount_text = produc_amount + unidad;
        holder.product_amount.setText(amount_text);
        holder.product_img.setImageResource(product_img);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackEnd.getLoggedUser().removeCartProduct(model.get(position));
                model = BackEnd.getLoggedUser().getCartProducts();
                notifyDataSetChanged();
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
        TextView product_name, product_description, product_price,product_amount, product_total_price;
        ImageView product_img;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            //TODO ACA SE VINCULA LA VISTA CON EL MODELO
            product_name = itemView.findViewById(R.id.row_pending_order_label_product_name);
            product_description = itemView.findViewById(R.id.row_pending_order_label_product_description);
            product_price = itemView.findViewById(R.id.row_pending_order_label_product_price);
            product_total_price = itemView.findViewById(R.id.row_pending_order_label_product_total_price);
            product_img = itemView.findViewById(R.id.row_pending_order_imageView_product_img);
            product_amount = itemView.findViewById(R.id.row_pending_order_label_amount);



        }
    }
}

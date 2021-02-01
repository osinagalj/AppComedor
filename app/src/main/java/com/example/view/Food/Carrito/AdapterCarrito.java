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

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    List<Product> model =  new ArrayList<>();
    Context context;
    ImageButton button;
    private View.OnClickListener listener;


    public AdapterCarrito(Context context, ArrayList<Product> model) {//TODO borrar el segundo parametro
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.model = model;
    }


    @NonNull
    @Override
    public AdapterCarrito.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_pendiente, parent, false);

        view.setOnClickListener(this);
        button = view.findViewById(R.id.row_pending_order_button_remove);

        return new AdapterCarrito.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCarrito.ViewHolder holder, final int position) {
        String product_name = model.get(position).getName();
        int product_nro = model.get(position).getId();
        String product_price = String.valueOf(model.get(position).getPrice());

        int produc_amount = BackEnd.getAmount(model.get(position));

        //float produc_amount = BackEnd.getProducts().get(model.get(position))model.get(position).getPrice(); //todo
        int product_img = model.get(position).getImgId();

        holder.product_name.setText("#"+product_nro);
        holder.product_description.setText(product_name);
        holder.product_price.setText("Precio x Unidad: " + product_price);
        float tot = model.get(position).getPrice() * produc_amount; //TODO
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
                BackEnd.removeProduct(model.get(position));
                model.remove(model.get(position));
                //model = BackEnd.getProducts();
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
            product_name = itemView.findViewById(R.id.row_pending_order_label_product_name);
            product_description = itemView.findViewById(R.id.row_pending_order_label_product_description);
            product_price = itemView.findViewById(R.id.row_pending_order_label_product_price);
            product_total_price = itemView.findViewById(R.id.row_pending_order_label_product_total_price);
            product_img = itemView.findViewById(R.id.row_pending_order_imageView_product_img);
            product_amount = itemView.findViewById(R.id.row_pending_order_label_amount);
        }
    }
}

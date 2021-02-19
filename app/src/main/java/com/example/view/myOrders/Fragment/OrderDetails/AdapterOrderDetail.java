package com.example.view.myOrders.Fragment.OrderDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;

import java.util.ArrayList;

public class AdapterOrderDetail extends RecyclerView.Adapter<AdapterOrderDetail.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<OrderDetail> model;
    Context context;
    private int count = 0;
    Button button;
    private View.OnClickListener listener;


    public AdapterOrderDetail(Context context, ArrayList<OrderDetail> model ) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterOrderDetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_details, parent, false);
        view.setOnClickListener(this);
        button = (Button) view.findViewById(R.id.row_pending_order_new_button_remove);

        return new AdapterOrderDetail.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrderDetail.ViewHolder holder, final int position) {
        String product_amount = model.get(position).getAmount();
        String product_name = model.get(position).getName();
        String product_price = model.get(position).getPrice();

        holder.product_amount.setText(product_amount);
        holder.product_name.setText(product_name);
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
        TextView product_amount, product_name,product_price;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            product_amount = itemView.findViewById(R.id.row_order_details_label_order_product_amount);
            product_name = itemView.findViewById(R.id.row_order_details_label_order_product_name);
            product_price = itemView.findViewById(R.id.row_order_details_label_order_product_price);
        }
    }
}

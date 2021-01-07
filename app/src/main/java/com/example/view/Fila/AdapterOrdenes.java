package com.example.view.Fila;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.Food.AdapterPersonas;
import com.example.view.Food.Food;
import com.example.view.R;

import java.util.ArrayList;

public class AdapterOrdenes  extends RecyclerView.Adapter<AdapterOrdenes.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Orden> model;

    private View.OnClickListener listener;

    public AdapterOrdenes(Context context, ArrayList<Orden> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterOrdenes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_fila, parent, false);
        view.setOnClickListener(this);
        return new AdapterOrdenes.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrdenes.ViewHolder holder, int position) {
        String nombres = model.get(position).getNombre();
        holder.nombres.setText(nombres);

    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombres, fechancimiento;
        ImageView imagen;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombres = itemView.findViewById(R.id.nombres2);

        }
    }

}
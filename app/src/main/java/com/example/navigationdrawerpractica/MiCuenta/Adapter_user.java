package com.example.navigationdrawerpractica.MiCuenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class Adapter_user extends RecyclerView.Adapter<Adapter_user.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<User_data> model;

    private View.OnClickListener listener;

    public Adapter_user(Context context, ArrayList<User_data> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public Adapter_user.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_my_account, parent, false);
        view.setOnClickListener(this);
        return new Adapter_user.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_user.ViewHolder holder, int position) {
        String nombres = model.get(position).getCampo();
        String fechanacimiento = model.get(position).getDescripcion();
        holder.nombres.setText(nombres);
        holder.fechancimiento.setText(fechanacimiento);

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

            nombres = itemView.findViewById(R.id.campo);
            fechancimiento = itemView.findViewById(R.id.descripcion);

        }
    }
}

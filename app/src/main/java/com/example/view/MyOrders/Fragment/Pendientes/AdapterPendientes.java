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

public class AdapterPendientes extends RecyclerView.Adapter<com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Order> model;
    Context context;
    private int count = 0;
    ImageButton button;
    private View.OnClickListener listener;


    public AdapterPendientes(Context context, ArrayList<Order> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;

        this.context = context;
    }


    @NonNull
    @Override
    public com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_pendiente, parent, false);
        view.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.pendientes_button_remove_order);




        return new com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder holder, final int position) {
        String nombres = model.get(position).getNombre();
        String fechanacimiento = model.get(position).getFechanacimiento();
        int imageid = model.get(position).getImagenid();
        holder.nombres.setText(nombres);
        holder.fechancimiento.setText(fechanacimiento);
        holder.imagen.setImageResource(imageid);


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO CORREGIR LOS INDICES, POR AHORA SOLO SE PUEDE ELIMINAR EL PRIMER ELEMENTO DE LOS PEDIDOS PENDIENTES
                // NO LO PUDE ARREGLAR F
                // TODO AL PARECE EL POSITION SIEMPRE MANTIENE LAS POISICONES INICIALES, OSEA SI ELIMINAS EL ELEMENTO 0,
                //  EL ELEMENTO 2 NO PASA A SER EL 1, SINO SIGUE SIENDO EL 2
                System.out.println("positionn de mierda = " + (position));
                System.out.println("Size del model  de mierda = " + (model.size()));
                model.remove(0);
                notifyItemRemoved(0);
                Repositorio.repo.removeFirstPendOrder(0);

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
        TextView nombres, fechancimiento;
        ImageView imagen;
        ImageButton img_delete;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombres = itemView.findViewById(R.id.pendiente_nombre);
            fechancimiento = itemView.findViewById(R.id.pendiente_precio);
            imagen = itemView.findViewById(R.id.pendiente_img);
            //img_delete = itemView.findViewById(R.id.pendientes_button_remove_order);

        }
    }
}

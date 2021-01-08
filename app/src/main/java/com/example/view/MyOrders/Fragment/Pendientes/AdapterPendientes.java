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

import com.example.view.Interfaces.MainActivity;
import com.example.view.R;

import java.util.ArrayList;

public class AdapterPendientes extends RecyclerView.Adapter<com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Order> model;

    private View.OnClickListener listener;

    public AdapterPendientes(Context context, ArrayList<Order> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_pendiente, parent, false);
        view.setOnClickListener(this);

        ImageButton button = (ImageButton) view.findViewById(R.id.button_remove_order);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("HAS ELIMINADO UNA ORDEN PENDIENTE1");
                TextView nombres = v.findViewById(R.id.pendiente_nombre);
                System.out.println("HAS ELIMINADO UNA ORDEN PENDIENTE2 =" +  v.getId());
                MainActivity.repo.eliminarPrimeraOrdenPendiente();
            }
        });
        return new com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.view.MyOrders.Fragment.Pendientes.AdapterPendientes.ViewHolder holder, int position) {
        String nombres = model.get(position).getNombre();
        String fechanacimiento = model.get(position).getFechanacimiento();
        int imageid = model.get(position).getImagenid();
        holder.nombres.setText(nombres);
        holder.fechancimiento.setText(fechanacimiento);
        holder.imagen.setImageResource(imageid);


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

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombres = itemView.findViewById(R.id.pendiente_nombre);
            fechancimiento = itemView.findViewById(R.id.pendiente_precio);
            imagen = itemView.findViewById(R.id.pendiente_img);
        }
    }
}

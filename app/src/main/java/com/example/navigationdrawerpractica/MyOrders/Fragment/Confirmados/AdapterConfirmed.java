

package com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados;

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

public class AdapterConfirmed extends RecyclerView.Adapter<com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Order2> model;

    private View.OnClickListener listener;

    public AdapterConfirmed(Context context, ArrayList<Order2> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_confirmed, parent, false);
        view.setOnClickListener(this);
        return new com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados.AdapterConfirmed.ViewHolder holder, int position) {
        String nombres = model.get(position).getNombre();
        String fechanacimiento = model.get(position).getFechanacimiento();
        int imageid = model.get(position).getImagenid();
        holder.nombres2.setText(nombres);
        holder.fechancimiento2.setText(fechanacimiento);
        holder.imagen2.setImageResource(imageid);
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
        TextView nombres2, fechancimiento2;
        ImageView imagen2;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nombres2 = itemView.findViewById(R.id.confirmed_nombre);
            fechancimiento2 = itemView.findViewById(R.id.confirmed_precio);
            imagen2 = itemView.findViewById(R.id.confirmed_img);
        }
    }
}

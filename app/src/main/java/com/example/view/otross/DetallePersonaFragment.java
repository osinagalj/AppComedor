package com.example.view.otross;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.view.Food.Food;
import com.example.view.Interfaces.MainActivity;
import com.example.view.Interfaces.iComunicaFragments;
import com.example.view.MyOrders.Fragment.Pendientes.Order;
import com.example.view.R;

public class DetallePersonaFragment extends Fragment { // Este ya no se usa, despues lo elimino
    TextView nombre;
    //ImageView imagen;
    Button button_add_order2;

    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        final ImageView imagen = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoPersona = getArguments();
        Food food = null;
        //validacion para verificar si existen argumentos para mostrar

        if(objetoPersona !=null){
            food = (Food) objetoPersona.getSerializable("objeto");
            imagen.setImageResource(food.getImagenid());
            nombre.setText(food.getNombre());
            final String name = food.getNombre();
            final int idImg = food.getImagenid();
            final String price = food.getFechanacimiento();
            button_add_order2 = view.findViewById(R.id.button_add_order);
            button_add_order2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Order o1 = new Order(name,price,idImg);
                    MainActivity.repo.putOrder(o1);
                    Toast.makeText(getContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
                }
            });
        }




        return view;
    }

    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Comidas");
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

    }
}

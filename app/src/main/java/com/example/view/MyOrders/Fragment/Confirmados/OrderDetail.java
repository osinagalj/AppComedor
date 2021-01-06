package com.example.view.MyOrders.Fragment.Confirmados;

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


public class OrderDetail extends Fragment {
    TextView nro_orden;
    //ImageView imagen;
    Button button_add_order2;


    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_details,container,false);
        nro_orden = view.findViewById(R.id.txt_nro_orden);

        Bundle objetoPersona = getArguments();
        Order food = null;
        //validacion para verificar si existen argumentos para mostrar

        if(objetoPersona !=null){
            //food = (Order) objetoPersona.getSerializable("PDF");
            nro_orden.setText("2001010");

        }

        return view;
    }


    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Ticket");
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

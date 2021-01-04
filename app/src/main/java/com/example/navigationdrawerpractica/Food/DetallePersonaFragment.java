package com.example.navigationdrawerpractica.Food;

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

import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes.Order;
import com.example.navigationdrawerpractica.R;

public class DetallePersonaFragment extends Fragment {
    TextView nombre;
    ImageView imagen;
    Button button_add_order2;

    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        imagen = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoPersona = getArguments();
        Food food = null;
        //validacion para verificar si existen argumentos para mostrar
        System.out.println("Entro 0");
        String name = "fodd name";
        if(objetoPersona !=null){
            System.out.println("Entro 1");
            food = (Food) objetoPersona.getSerializable("objeto");
            imagen.setImageResource(food.getImagenid());
            nombre.setText(food.getNombre());
            name = food.getNombre();
            System.out.println("Entro 2");

        }

        button_add_order2 = view.findViewById(R.id.button_add_order);
        button_add_order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Seleccionó: Boton add  ", Toast.LENGTH_LONG).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                Order o1 = new Order("prueba77","$50",R.drawable.food_alfajor_pepitos);
                o1.setNombre("tu kaka");
                interfaceComunicaFragments.enviarOrdenPendiente(o1);
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });


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

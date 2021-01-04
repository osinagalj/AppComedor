package com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.navigationdrawerpractica.Food.Food;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Repositorio;

import java.util.ArrayList;


public class FragmentPendientes extends Fragment {


    AdapterPendientes adapterPend;
    RecyclerView recyclerViewPersonas;
    ArrayList<Order> listaFoods;


    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView_live);
        listaFoods = new ArrayList<>();

        cargarLista();


        //para agregar un pedido

        Bundle objetoPersona = getArguments();
        Order o2 = null;
        //validacion para verificar si existen argumentos para mostrar

        Order o1 = new Order("prueba77","$50",R.drawable.food_alfajor_pepitos);


        Order o3 = new Order("prueba79","$60",R.drawable.food_empanada);
        Order o4 = new Order("prueba80","$80",R.drawable.food_empanada);


        MainActivity.repo.setId(1001);
        System.out.println("Id del repo "+MainActivity.repo.getId());

        System.out.println("Ingresando ordenes");
        MainActivity.repo.putOrder(o3);

        System.out.println("Saliendo ordenes");
        MainActivity.repo.putOrder(o4);
        System.out.println("Saliendo ordenes2");


        ArrayList<Order> ar = MainActivity.repo.getOrders();
        for(Order o : ar){
            listaFoods.add(o);
        }
/*
        System.out.println("TODAVIA NO HAY UN OBJETO DE MIERDA");
        if(objetoPersona !=null){
            System.out.println("HAY UN OBJETO DE MIERDA");
            o2 = (Order) objetoPersona.getSerializable("orderP");
            Toast.makeText(getContext(), "Seleccionó:" + o2.getNombre() , Toast.LENGTH_LONG).show();
            o1.setNombre(o2.getNombre());
            listaFoods.add(o1);
        }
        */

        mostrarData();

        return view;
    }

    public void cargarLista(){
        listaFoods.add(new Order("pendiente 1","$50",R.drawable.food_alfajor_pepitos));
        listaFoods.add(new Order("pedido 2","$50",R.drawable.food_botella_coca));
    }


    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPend = new AdapterPendientes(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);

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


    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


}
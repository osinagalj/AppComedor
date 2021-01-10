package com.example.view.MyOrders.Fragment.Pendientes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.MyOrders.Fragment.Confirmados.ActivityPdf;
import com.example.view.R;

import java.util.ArrayList;

import DataBase.Repositorio;


public class FragmentPendientes extends Fragment {


    AdapterPendientes adapterPend;
    RecyclerView recyclerViewPersonas;
    ArrayList<Order> listaFoods;

    ImageButton buttonRemove;
    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_orders,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView_live);
        buttonRemove = view.findViewById(R.id.pendientes_button_remove_order);
        listaFoods = new ArrayList<>();
        cargarLista();
        mostrarData();

        return view;
    }

    public void cargarLista(){
        ArrayList<Order> ar = Repositorio.repo.getOrders();
        for(Order o : ar){
            listaFoods.add(o);
        }
    }


    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPend = new AdapterPendientes(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);

        adapterPend.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ActivityPdf.class);
                startActivity(intent);
                //voy a tener que pasarle la orden para el pedido

                //interfaceComunicaFragments.abrirPDF(listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
        /*
        buttonRemove = getView().findViewById(R.id.pendientes_button_remove_order);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ENTRO EN EL CLICK PAPA");
                adapterPend.notifyItemRemoved(0);
            }
        });*/
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
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

    }


    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


}
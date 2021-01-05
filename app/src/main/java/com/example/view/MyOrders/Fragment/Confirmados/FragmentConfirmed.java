package com.example.view.MyOrders.Fragment.Confirmados;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.Interfaces.iComunicaFragments;

import com.example.view.R;

import java.util.ArrayList;

public class FragmentConfirmed  extends Fragment {


    AdapterConfirmed adapterPend;
    RecyclerView recyclerViewPersonas;
    ArrayList<Order2> listaFoods;



    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixture,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView_fixture);
        listaFoods = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listaFoods.add(new Order2("a","$50",R.drawable.food_alfajor_pepitos));
        listaFoods.add(new Order2("pedido 8","$50",R.drawable.food_botella_coca));
        listaFoods.add(new Order2("pedido 9","$50",R.drawable.food_empanada));
        listaFoods.add(new Order2("pedido 10","$50",R.drawable.food_milanesas_con_fritas));
        listaFoods.add(new Order2("pedido","$50",R.drawable.food_pepas_trio));
        listaFoods.add(new Order2("pedido","$50",R.drawable.food_vaso_coca));
        listaFoods.add(new Order2("pedido","$50",R.drawable.food_turron_arcor));
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPend = new AdapterConfirmed(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);


    }

    @Override
    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Mis Pedidos");
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

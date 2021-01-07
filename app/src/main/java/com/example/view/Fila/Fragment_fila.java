package com.example.view.Fila;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.example.view.Food.AdapterPersonas;
import com.example.view.Food.Food;
import com.example.view.Food.FoodDetail;
import com.example.view.Interfaces.iComunicaFragments;
import com.example.view.R;

import java.util.ArrayList;

public class Fragment_fila extends Fragment {

    AdapterOrdenes adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<Orden> listaFoods;
    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;
    RecyclerView mainRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fila, container, false);
        recyclerViewPersonas = view.findViewById(R.id.rv_fila);
        listaFoods = new ArrayList<>();

        cargarLista();
        mostrarData();

        return view;
    }

    public void cargarLista(){
        listaFoods.add(new Orden("#12310"));
        listaFoods.add(new Orden("#12311"));
        listaFoods.add(new Orden("#12312"));
        listaFoods.add(new Orden("#12313"));
        listaFoods.add(new Orden("#12314"));
        listaFoods.add(new Orden("#12315"));
        listaFoods.add(new Orden("#12316"));
        listaFoods.add(new Orden("#12317"));
        listaFoods.add(new Orden("#12318"));
        listaFoods.add(new Orden("#12319"));
        listaFoods.add(new Orden("#12315"));
        listaFoods.add(new Orden("#12316"));
        listaFoods.add(new Orden("#12317"));
        listaFoods.add(new Orden("#12318"));
        listaFoods.add(new Orden("#12319"));
        listaFoods.add(new Orden("#12318"));
        listaFoods.add(new Orden("#12319"));
        listaFoods.add(new Orden("#12315"));
        listaFoods.add(new Orden("#12316"));
        listaFoods.add(new Orden("#12317"));
        listaFoods.add(new Orden("#12318"));
        listaFoods.add(new Orden("#12319"));
    }

    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterOrdenes(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPersonas);
    }

    @Override
    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Fila");
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
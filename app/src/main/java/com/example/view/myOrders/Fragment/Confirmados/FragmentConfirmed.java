package com.example.view.myOrders.Fragment.Confirmados;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.BackEnd;
import com.example.view.myOrders.Fragment.OrderDetails.ActivityPdf;
import com.example.view.R;

import java.util.ArrayList;

import model.Order;

public class FragmentConfirmed  extends Fragment {


    AdapterConfirmed adapterPend;
    RecyclerView recyclerViewPersonas;
    ArrayList<Order> listaFoods;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmed_orders,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView_fixture);
        listaFoods = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listaFoods.addAll(BackEnd.getConfirmedOrders());
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPend = new AdapterConfirmed(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);

        adapterPend.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityPdf.class);
                intent.putExtra("ORDER_SELECTED",listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


}

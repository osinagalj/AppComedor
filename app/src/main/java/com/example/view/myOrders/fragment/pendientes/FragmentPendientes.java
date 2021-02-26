package com.example.view.myOrders.fragment.pendientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;
import com.example.view.myOrders.fragment.orderDetails.ActivityPdf;

import java.util.ArrayList;
import java.util.List;

import model.Order;


public class FragmentPendientes extends Fragment {


    AdapterPendientes adapterPend;
    RecyclerView recyclerViewPersonas;
    List<Order> listaFoods;

    PendingOrdersViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_orders,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView_live);

        listaFoods = new ArrayList<>();

        viewModel = ViewModelProviders.of(this).get(PendingOrdersViewModel.class); //ViewModel para la DB

        viewModel.setOrders();

        viewModel.list_orders.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                listaFoods = orders;
                //foods1.addAll(list_foods); se agregan repetidos si hago esto
                mostrarData();
            }
        });


        return view;
    }

    private void mostrarData() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPend = new AdapterPendientes(getContext(), listaFoods,this);
        recyclerViewPersonas.setAdapter(adapterPend);

        adapterPend.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ActivityPdf.class);
                Order o = listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view));
                System.out.println("Numero de orden = " + o.getId());
                intent.putExtra("ORDER_SELECTED",o);
                startActivity(intent);
            }
        });

    }

}
package com.example.view.myOrders.fragment.pending_orders;

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


    AdapterPendientes adapter;
    RecyclerView rv_orders;
    List<Order> orders;

    PendingOrdersViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_orders,container,false);
        rv_orders = view.findViewById(R.id.recyclerView_live);

        orders = new ArrayList<>();

        viewModel = ViewModelProviders.of(this).get(PendingOrdersViewModel.class); //ViewModel para la DB

        viewModel.getOrders().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders2) {
                orders = orders2;
                showData();
            }
        });


        return view;
    }

    private void showData() {
        rv_orders.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterPendientes(getContext(), orders,this);
        rv_orders.setAdapter(adapter);

        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ActivityPdf.class);
                Order o = orders.get(rv_orders.getChildAdapterPosition(view));
                intent.putExtra("ORDER_SELECTED",String.valueOf(o.getId()));
                startActivity(intent);
            }
        });

    }

}
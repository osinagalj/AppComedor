package com.example.view.Fila;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.databinding.FragmentFilaBinding;

import java.util.ArrayList;
import java.util.List;

import DAO.OrderDAO;
import Model.Order;

public class Fragment_queue extends Fragment {

    OrdersAdapter adapter;
    ArrayList<String> orders;
    private FragmentFilaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Fila");
        super.onCreate(savedInstanceState);
        binding = FragmentFilaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        orders = new ArrayList<>();

        loadData();
        showData();

        return view;
    }

    public void loadData(){
        List<Order> data = OrderDAO.nextOrders();
        for(Order order : data){
            orders.add("#"+order.getId());
        }
        orders.add("#10021");
        orders.add("#10021");
    }

    private void showData(){
        binding.rvFila.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrdersAdapter(getContext(), orders);
        binding.rvFila.setAdapter(adapter);
    }

}
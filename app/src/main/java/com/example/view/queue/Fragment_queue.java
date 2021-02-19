package com.example.view.queue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.BackEnd;
import com.example.view.databinding.FragmentFilaBinding;

import java.util.ArrayList;

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

        binding.time.setText(BackEnd.getTimeToNextOrder());
        binding.orderNumber.setText(BackEnd.getNextOrder());

        loadData();
        showData();

        return view;
    }


    private void loadData(){

        orders.addAll(BackEnd.getNextOrders());

        orders.add("#10021");
        orders.add("#10021");
    }

    private void showData(){
        binding.rvQueue.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrdersAdapter(getContext(), orders);
        binding.rvQueue.setAdapter(adapter);
    }

}
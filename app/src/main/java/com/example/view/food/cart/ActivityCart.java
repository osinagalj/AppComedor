package com.example.view.food.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityCarritoBinding;
import com.example.view.food.FinishOrder;

import java.util.ArrayList;

import dao.OrderDAO;
import model.OrderLine;

public class ActivityCart extends AppCompatActivity {

    AdapterCart adapter;
    ArrayList<OrderLine> products;
    private ActivityCarritoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCarritoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        products = new ArrayList<>();
        binding.carritoTotalPrice.setText(String.valueOf(BackEnd.getOrderPrice()));

        loadData();
        showData();
        setUpButtons();
    }

    private void setUpButtons(){

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO eliminar los productos de la orden actual
                if(!BackEnd.orderIsEmpty()){
                    showSimpleDialog(v);
                }else{
                    finish();
                }
            }
        });

        binding.btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnFinishOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO eliminar los productos de la orden actual
                if(BackEnd.orderIsEmpty()){
                    Toast.makeText(getBaseContext(), "No se puede realizar un pedido sin productos", Toast.LENGTH_SHORT).show();
                }else{
                    completOrder();
                }
            }
        });
    }

    private void completOrder(){
        OrderDAO.getNumberNextOrder().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
                BackEnd.confirmOrder(id + 1);
                openFinishOrder();
            }
        });
    }

    public void openFinishOrder(){
        Intent intent = new Intent(this, FinishOrder.class);
        startActivity(intent);
        this.finish();
    }

    public void loadData(){
        products = new ArrayList<>();
        products.addAll(BackEnd.getOrder().getLines());
    }

    private void showData() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new AdapterCart(this, products);
        binding.recyclerView.setAdapter(adapter);
    }

    public void showSimpleDialog(View view) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Cancelar Pedido");
        builder.setMessage("Se eliminaran los productos agregados al carrito");
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                BackEnd.clearOrder();
                finish();
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
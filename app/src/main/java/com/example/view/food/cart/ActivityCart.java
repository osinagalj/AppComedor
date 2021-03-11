package com.example.view.food.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityCarritoBinding;
import com.example.view.food.FinishOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.OrderDAO;
import dao.ProductDAO;
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
                if(BackEnd.orderIsEmpty()){
                    Toast.makeText(getBaseContext(), "No se puede realizar un pedido sin productos", Toast.LENGTH_SHORT).show();
                }else{
                    completOrder();
                }
            }
        });
    }




    public MutableLiveData<Boolean> stockAvailable(List<OrderLine> o){
        final int[] stocks = {0};
        final int[] end = {0};
        MutableLiveData<Boolean> decrease_ok = new MutableLiveData<Boolean>();

        ArrayList<String> tables= new ArrayList<>();
        tables.add("foods");
        tables.add("combos");
        tables.add("dailyMenus");

        for(OrderLine line: o){
            for(String s: tables){
                ProductDAO.decreaseStock(String.valueOf(line.getProduct().getId()),line.getAmount(),s )
                        .observe(this, new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean decreaseOk) {
                                if(decreaseOk){
                                    stocks[0] = stocks[0] + 1;
                                }
                                end[0] = end[0] + 1;
                                if(end[0] == o.size()){ //si ya se hicieron todas las ordenes, exitosas o no
                                    if(stocks[0] == end[0]){ //si fueron exitosas
                                        decrease_ok.setValue(true); //retorno true
                                    }else{
                                        decrease_ok.setValue(false); //retorno false
                                        for (HashMap.Entry<Integer, Integer> entry : ProductDAO.productos_decrementados.entrySet()) {
                                            ProductDAO.increaseStock(String.valueOf(entry.getKey()), entry.getValue(),"foods");
                                            ProductDAO.increaseStock(String.valueOf(entry.getKey()), entry.getValue(),"combos");
                                            ProductDAO.increaseStock(String.valueOf(entry.getKey()), entry.getValue(),"dailyMenus");
                                        }
                                        ProductDAO.productos_decrementados.clear();
                                    }
                                }
                            }
                        });
            }

        }
        return decrease_ok;
    }


    private void getOrderId (){

        OrderDAO.getNumberNextOrder().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
                //if(!cargo[0]){
                //   cargo[0] = true;
                BackEnd.confirmOrder(id);
                openFinishOrder();

                //  }

            }
        });
    }
    private void completOrder(){

        stockAvailable(BackEnd.getOrder().getLines()).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean decreaseOk) {
                        if(decreaseOk){
                            getOrderId();
                            Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getBaseContext(), "No hay stock suficiente", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

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
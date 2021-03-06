package com.example.view.myOrders.fragment.orderDetails;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityOrderDetailsBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Product;

public class ActivityPdf extends AppCompatActivity {

    AdapterOrderDetail adapterPend;
    ArrayList<OrderDetail> listaFoods;
    private ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpButtons();
        listaFoods = new ArrayList<>();


        Order order =(Order) getIntent().getExtras().get("ORDER_SELECTED");

        if(order != null) {
            setData(order);
            showData();
        }

    }


    private void showData() {
        binding.orderDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        adapterPend = new AdapterOrderDetail(this, listaFoods);
        binding.orderDetailsRv.setAdapter(adapterPend);
    }


    private void setData(Order order){

        binding.orderNumber.setText("#"+order.getId());
        binding.totalPrice.setText(String.valueOf(order.getPrice()));
        String day = new SimpleDateFormat("dd/MM/yyyy").format(order.getPlaced());
        int hours = order.getPlaced().getHours();
        int seconds = order.getPlaced().getSeconds();
        String time = hours + "hs : " + seconds + " seg";
        binding.timeHour.setText(time);
        binding.timeDay.setText(day);
        String full_name = order.getPlacedBy().getNames() + " " + order.getPlacedBy().getLastName();
        binding.userName.setText(full_name);


        List<Product> products = order.getItems();
        for(Product product : products){
            listaFoods.add(new OrderDetail(String.valueOf(order.getAmount(product)),product.getName(), String.valueOf(product.getPrice(BackEnd.getLoggedUser()))));
        }
    }

    private void setUpButtons(){

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
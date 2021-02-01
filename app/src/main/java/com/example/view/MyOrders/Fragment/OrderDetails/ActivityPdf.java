package com.example.view.MyOrders.Fragment.OrderDetails;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.databinding.ActivityOrderDetailsBinding;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import Model.Order;
import Model.Product;

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


        final Order order =(Order) getIntent().getExtras().get("ORDER_SELECTED");

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
        ZonedDateTime zonedDateTime = order.getPlacedInstant().atZone(ZoneOffset.UTC);
        binding.timeHour.setText(zonedDateTime.toLocalTime().toString());
        binding.timeDay.setText(zonedDateTime.toLocalDate().toString());
        String full_name = order.getPlacedBy().getNames() + " " + order.getPlacedBy().getLastname();
        binding.userName.setText(full_name);


        List<Product> products = order.getItems();
        for(Product product : products){
            listaFoods.add(new OrderDetail(String.valueOf(order.getAmount(product)),product.getName(), String.valueOf(product.getPrice())));
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
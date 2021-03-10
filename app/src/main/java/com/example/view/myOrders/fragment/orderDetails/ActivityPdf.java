package com.example.view.myOrders.fragment.orderDetails;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.view.databinding.ActivityOrderDetailsBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.OrderDAO;
import model.Order;
import model.OrderLine;

public class ActivityPdf extends AppCompatActivity {

    AdapterOrderDetail adapterPend;
    ArrayList<OrderDetail> lines;
    private ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        setUpButtons();
        lines = new ArrayList<>();
        setContentView(view);

        String id =getIntent().getExtras().get("ORDER_SELECTED").toString();

        System.out.println("Id del string = " + id);
        OrderDAO.getOrderById(String.valueOf(id)).observe(this, new Observer<Order>() {
            @Override
            public void onChanged(Order order) {
                System.out.println("Entro en by id");
                setData(order);
                showData();

            }
        });

    }


    private void showData() {
        binding.orderDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        adapterPend = new AdapterOrderDetail(this, lines);
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

        for(OrderLine line : order.getLines()){
            lines.add(new OrderDetail(String.valueOf(line.getAmount()),line.getProduct().getName(), String.valueOf(line.getPrice())));
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
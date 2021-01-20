package com.example.view.MyOrders.Fragment.OrderDetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

import Model.Order;
import Model.Product;

public class ActivityPdf extends AppCompatActivity {

    AdapterOrderDetail adapterPend;
    RecyclerView recyclerViewPersonas;
    ArrayList<OrderDetail> listaFoods;
    ImageButton btn_close;
    private TextView order_number,total_price,time_hour,time_day, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        recyclerViewPersonas = findViewById(R.id.order_details_rv);
        listaFoods = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle  = intent.getExtras();
        final Order order =(Order) bundle.get("ORDER_SELECTED");

        setUpButtons();

        if(bundle != null) {
            setData(order);
            showData();
        }

    }


    private void showData() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(this));
        adapterPend = new AdapterOrderDetail(this, listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);
    }

    @SuppressLint("SetTextI18n")
    private void setData(Order order){
        order_number = findViewById(R.id.order_details_label_order_number);
        total_price = findViewById(R.id.order_details_label_order_price);
        time_hour = findViewById(R.id.order_details_label_order_time_hour);
        time_day = findViewById(R.id.order_details_label_order_time_day);
        user = findViewById(R.id.order_details_label_order_user_name);

        order_number.setText("#"+order.getId());
        total_price.setText(String.valueOf(order.getPrice()));
        time_hour.setText(order.getTimeHour());
        time_day.setText(order.getTime());
        //user.setText(order.getPlacedBy().getNames()); TODO

        List<Product> products = order.getItems();
        for(Product product : products){
            listaFoods.add(new OrderDetail(order.getAmount(0),product.getName(), String.valueOf(product.getPrice())));
        }
    }

    private void setUpButtons(){
        btn_close = findViewById(R.id.order_details_button_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
package com.example.view.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityFoodDetailsBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderLine;
import model.OrderState;
import model.Product;

public class FoodDetail extends AppCompatActivity  {

    private ActivityFoodDetailsBinding binding;

    MutableLiveData<Boolean> state = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFoodDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        final Product product = (Product) getIntent().getExtras().get("food_picked");

        setProductDetails(product);
        setUpButtons(product);

    }

    private void setProductDetails(Product product){
        binding.productImg.setImageResource(product.getImgId());
        binding.productName.setText(product.getName());
        binding.productPrice.setText(String.valueOf(product.getPrice(BackEnd.getLoggedUser())));
        binding.productDescription.setText(product.getDescription());

        if(product.toHome()){
            binding.cbToHome.setVisibility(View.VISIBLE);
        }else{
            binding.cbToHome.setVisibility(View.GONE);
        }
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
                                            decrease_ok.setValue(false); //retorno false, no se pudo realizar la orden
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
                binding.cbToHome.setChecked(false);
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



    private void observe(Product product){
        //todo obtener todas las ordenes creo no solo las pending
        OrderDAO.getOrders(OrderState.PENDING).observe(this, new Observer<List<Order>>() { //actualizo las ordenes
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                if(addProduct(product,orders)){
                    //Send the order to the DataBase

                    completOrder();
                }
            }
            });
    }

    private void observe2(Product product){

        OrderDAO.getOrders(OrderState.PENDING).observe(this, new Observer<List<Order>>() { //actualizo las ordenes
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                if(addProduct(product,orders)){
                    binding.cbToHome.setChecked(false);
                    Toast.makeText(getBaseContext(), "Se ha agregado el producto al carrito", Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                }
            }
        });
    }


    private void setUpButtons(Product product){

        binding.btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observe(product);
            }
        });

        binding.btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observe2(product);
            }
        });


        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        binding.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(binding.productAmount.getText().toString());
                if(number > 1){
                    number--;
                }else{
                    Toast.makeText(getBaseContext(), "Cantidad minima", Toast.LENGTH_SHORT).show();
                }
                binding.productAmount.setText(String.valueOf(number));
                binding.productPrice.setText(String.valueOf(product.getPrice(BackEnd.getLoggedUser())*number));
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.productAmount.getText().toString();
                int cant = Integer.parseInt(name) + 1;
                binding.productAmount.setText(String.valueOf(cant));
                binding.productPrice.setText(String.valueOf(product.getPrice(BackEnd.getLoggedUser())*cant));
            }
        });

    }


    private boolean addProduct(Product product, List<Order> orders){

        //Get the amount
        int addedAmount = Integer.parseInt(binding.productAmount.getText().toString());
        boolean toHome = binding.cbToHome.isChecked();

        //Add the food
        if(product.getPrice(BackEnd.getLoggedUser()) * addedAmount <= BackEnd.getLoggedUser().getBalance()){ //Si hay suficiente saldo
            if(product.getCategory() == 1){ //todo capaz es mejor obtener el numero del restaurant
                if(BackEnd.getDailyMenusRemaining(product,new Date(),orders) >= addedAmount){
                    BackEnd.addProduct(product,addedAmount,toHome);
                        return true;
                }else
                    Toast.makeText(getBaseContext(), "Cantidad maxima superada de este producto", Toast.LENGTH_SHORT).show();
            }else{
                    BackEnd.addProduct(product,addedAmount,toHome);
                    return true;
            }
        }else
            Toast.makeText(getBaseContext(), "No suficiente saldo en la cuenta", Toast.LENGTH_SHORT).show();

        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openFinishOrder(){
        Intent intent = new Intent(this,FinishOrder.class);
        startActivity(intent);
        finish();
    }

}
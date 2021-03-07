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

import java.util.Date;
import java.util.List;

import dao.OrderDAO;
import model.Order;
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
    private void completOrder(){

        //OrderDAO.setNumberNextOrder2();
        //final boolean[] cargo = {false}; //necesario sino inserta una orden vacia
        OrderDAO.setNumberNextOrder2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
                //if(!cargo[0]){
                 //   cargo[0] = true;
                    BackEnd.confirmOrder(id + 1);
                    openFinishOrder();
                    binding.cbToHome.setChecked(false);
              //  }

            }
        });
    }



    private void observe(Product product){

        OrderDAO.getOrders().observe(this, new Observer<List<Order>>() { //actualizo las ordenes
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                if(addProduct(product,orders)){
                    //Send the order to the DataBase
                    Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
                    completOrder();
                }
            }
            });
    }

    private void observe2(Product product){

        OrderDAO.getOrders().observe(this, new Observer<List<Order>>() { //actualizo las ordenes
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
        //TODO mejorar estafuncion y modularizar, posiblemente algunas cosas haya que hacerlas en el backend y no aca
        //Get the amount
        int addedAmount = Integer.parseInt(binding.productAmount.getText().toString());
        boolean toHome = binding.cbToHome.isChecked();
        //Add the food

        //Controlar el saldo > pedido
        //Controlar la cantidad de menus del dia que puede pedir
        //Controlar Stock

        if(product.getPrice(BackEnd.getLoggedUser()) * addedAmount <= BackEnd.getLoggedUser().getBalance()){ //Si hay suficiente saldo
            if(product.getCategory() == 1){ //todo capaz es mejor obtener el numero del restaurant
                if(BackEnd.getMenusRestantes(new Date(),orders) >= addedAmount){
                    //todo chkear stock
                    BackEnd.addProduct(product,addedAmount,toHome);
                        return true;

                     //   else{
                     //   Toast.makeText(getBaseContext(), "No hay stock suficiente", Toast.LENGTH_SHORT).show();
                  //  }
                }else{
                            Toast.makeText(getBaseContext(), "Cantidad maxima superada de este producto", Toast.LENGTH_SHORT).show();
                }


            }else{
                //todo chekear stock
                BackEnd.addProduct(product,addedAmount,toHome);
                    return true;
               // }else{
               //     Toast.makeText(getBaseContext(), "No hay stock suficiente", Toast.LENGTH_SHORT).show();
              //  }
            }

        }else{
            Toast.makeText(getBaseContext(), "No suficiente saldo en la cuenta", Toast.LENGTH_SHORT).show();
        }

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
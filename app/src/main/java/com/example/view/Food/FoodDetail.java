package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityFoodDetailsBinding;

import Model.Product;
import Model.ProductCategory;

public class FoodDetail extends AppCompatActivity {

    private ActivityFoodDetailsBinding binding;

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
        binding.productPrice.setText(String.valueOf(product.getPrice()));
        binding.productDescription.setText(product.getDescription());

        if(product.getCategory() == ProductCategory.DAILY_MENU){
            binding.cbToHome.setVisibility(View.VISIBLE);
        }else{
            binding.cbToHome.setVisibility(View.GONE);
        }
    }

    private void setUpButtons(Product product){

        binding.btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(addProduct(product)){
                        //Send the order to the DataBase
                        BackEnd.confirmOrder();
                        Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
                        openFinishOrder();
                        binding.cbToHome.setChecked(false);
                    }

                } catch (NumberFormatException numberFormatException){
                    Toast.makeText(getBaseContext(), "No ingreso un numero valido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(addProduct(product)){
                        //Send the order to the DataBase
                        binding.cbToHome.setChecked(false);
                        Toast.makeText(getBaseContext(), "Se ha agregado el producto al carrito", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (NumberFormatException numberFormatException){
                    Toast.makeText(getBaseContext(), "No ingreso un numero valido", Toast.LENGTH_SHORT).show();
                }
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
                binding.productPrice.setText(String.valueOf(product.getPrice()*number));
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.productAmount.getText().toString();
                int cant = Integer.parseInt(name) + 1;
                binding.productAmount.setText(String.valueOf(cant));
                binding.productPrice.setText(String.valueOf(product.getPrice()*cant));
            }
        });

    }

    private boolean addProduct(Product product){
        //Get the amount
        int addedAmount = Integer.parseInt(binding.productAmount.getText().toString());
        boolean toHome = binding.cbToHome.isChecked();
        //Add the food

        //Controlar el saldo > pedido
        //Controlar la cantidad de menus del dia que puede pedir
        //Controlar Stock

        if(product.getPrice() * addedAmount <= BackEnd.getLoggedUser().getBalance()){ //Si hay suficiente saldo
            if(product.getCategory().equals(ProductCategory.DAILY_MENU)){
                if(BackEnd.getLoggedUser().getDailySpecialRemaining() >= addedAmount){
                    BackEnd.getLoggedUser().setDailySpecialRemaining(BackEnd.getLoggedUser().getDailySpecialRemaining() - addedAmount);
                    if(BackEnd.addProduct(product,addedAmount,toHome)){
                        return true;
                    }else{
                        Toast.makeText(getBaseContext(), "No hay stock suficiente", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Cantidad maxima superada de este producto", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                if(BackEnd.addProduct(product,addedAmount,toHome)){
                    return true;
                }else{
                    Toast.makeText(getBaseContext(), "No hay stock suficiente", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        }else{
            Toast.makeText(getBaseContext(), "No suficiente saldo en la cuenta", Toast.LENGTH_SHORT).show();
            return false;
        }
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
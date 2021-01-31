package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityFoodDetailsBinding;

import Model.CommonUser;
import Model.Product;
import Model.ProductCategory;

public class FoodDetail extends AppCompatActivity {

    CommonUser loggedUser = BackEnd.getLoggedUser();

    private ActivityFoodDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFoodDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final Product product =(Product) getIntent().getExtras().get("food_picked");

        binding.productImg.setImageResource(product.getImgId());
        binding.productName.setText(product.getName());
        binding.productPrice.setText(String.valueOf(product.getPrice()));
        binding.productDescription.setText(product.getDescription());



        boolean isChecked = binding.cbToHome.isChecked();//TODO por si hay que pasarlo a la orden, aca esta el booleano
        if(product.getCategory() == ProductCategory.DAILY_MENU){
            binding.cbToHome.setVisibility(View.VISIBLE);
        }else{
            binding.cbToHome.setVisibility(View.GONE);
        }

        binding.btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int addedAmount = Integer.parseInt(binding.productAmount.getText().toString());
                    BackEnd.getLoggedUser().addProductToCart(product,addedAmount);
                    BackEnd.getLoggedUser().confirmOrder();
                    Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
                    openFinishOrder();
                } catch (NumberFormatException numberFormatException){
                    Toast.makeText(getBaseContext(), "No ingreso un numero valido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int addedAmount = Integer.parseInt(binding.productAmount.getText().toString());
                    BackEnd.getLoggedUser().addProductToCart(product,addedAmount);
                    finish();
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
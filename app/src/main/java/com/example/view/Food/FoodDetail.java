package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.DataHolder;
import com.example.view.R;

import Model.CommonUser;
import Model.Product;
import Model.ProductCategory;

public class FoodDetail extends AppCompatActivity {
    ImageView img;
    EditText amount;
    TextView product_name, product_price, product_description;
    CommonUser loggedUser = DataHolder.getLoggedUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        final Product product =(Product) b.get("food_picked");

        img = findViewById(R.id.food_details_product_img);
        product_name = findViewById(R.id.food_details_product_name);
        product_price = findViewById(R.id.food_details_product_price);
        amount = findViewById(R.id.food_details_editText_amount);
        product_description = findViewById(R.id.food_details_product_description);
        img.setImageResource(product.getImgId());
        product_name.setText(product.getName());
        product_price.setText(String.valueOf(product.getPrice()));
        product_description.setText(product.getDescription());



        CheckBox toHome = findViewById(R.id.cb_food_details_tohome);
        boolean isChecked = toHome.isChecked();//TODO por si hay que pasarlo a la orden, aca esta el booleano
        if(product.getCategory() == ProductCategory.DAILY_MENU){
            toHome.setVisibility(View.VISIBLE);
        }else{
            toHome.setVisibility(View.GONE);
        }



        Button fab = findViewById(R.id.food_details_button_add_order);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int addedAmount = Integer.parseInt(amount.getText().toString());
                    DataHolder.getLoggedUser().addProductToCart(product,addedAmount);
                    DataHolder.getLoggedUser().confirmOrder();
                    Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
                    openFinishOrder();
                } catch (NumberFormatException numberFormatException){
                    Toast.makeText(getBaseContext(), "No ingreso un numero valido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_add_products = findViewById(R.id.food_details_button_add_products);
        btn_add_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int addedAmount = Integer.parseInt(amount.getText().toString());
                    DataHolder.getLoggedUser().addProductToCart(product,addedAmount);
                    finish();
                } catch (NumberFormatException numberFormatException){
                    Toast.makeText(getBaseContext(), "No ingreso un numero valido", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageButton fab2 = findViewById(R.id.food_details_button_close);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ImageButton less = findViewById(R.id.food_details_button_less);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(amount.getText().toString());
                if(number > 1){
                    number--;
                }else{
                    Toast.makeText(getBaseContext(), "Cantidad minima", Toast.LENGTH_SHORT).show();
                }
                amount.setText(String.valueOf(number));
                product_price.setText(String.valueOf(product.getPrice()*number));
            }
        });

        ImageButton add = findViewById(R.id.food_details_button_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = amount.getText().toString();
                int cant = Integer.parseInt(name) + 1;
                amount.setText(String.valueOf(cant));
                product_price.setText(String.valueOf(product.getPrice()*cant));
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
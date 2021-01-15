package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

import java.util.ArrayList;

import DataBase.Order;
import DataBase.Repositorio;
import ModeloGian.Food;
import ModeloGian.Product;
import ModeloGian.ProductCategory;
import ModeloGian.Restaurant;

public class FoodDetail extends AppCompatActivity {
    ImageView img;
    EditText amount;
    TextView product_name, product_price;

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

        if(b!=null)
        {
            img.setImageResource(product.getImgId());
            product_name.setText(product.getName());
            product_price.setText(String.valueOf(product.getPrice()));

            Button fab = findViewById(R.id.food_details_button_add_order);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int amount2 = Integer.parseInt(amount.getText().toString());
                    //TODO revisar que la cantidad sea mayor a 0 y que el usuario tenga plata enla cuena para pedir, y si tiene hay que descontarsela
                    Order o1 = new Order(Repositorio.repo.getNroOrden(),amount2,product.getName(),String.valueOf(product.getPrice()),product.getImgId());
                    Repositorio.repo.putOrder(o1);
                    Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();

                    openFinishOrder();
                }
            });

            Button btn_add_products = findViewById(R.id.food_details_button_add_products);
            btn_add_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO AGREGAR EL PRODUCTO A LA LISTA DE PRODUCTOS EN LA ORDEN
                    Restaurant.getInstance().miOrden.add(new Food(0002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));
                    System.out.println("Agrego a la orden");

                    System.out.println("Agrego el tamaño es = " + Restaurant.getInstance().miOrden.size());

                    finish();

                }
            });
        }



        ImageButton fab2 = findViewById(R.id.food_details_button_close);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    //TODO HAY QUE HACER QUE CAMBIE EL PRECIO A MEDIDA QUE SE AUMENTA O BAJA LA CANTIDAD
        ImageButton less = findViewById(R.id.food_details_button_less);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer number = Integer.parseInt(amount.getText().toString());
                if(number > 1){
                    number--;
                }else{
                    Toast.makeText(getBaseContext(), "Cantidad minima", Toast.LENGTH_SHORT).show();
                }


                amount.setText(number.toString());
            }
        });

        ImageButton add = findViewById(R.id.food_details_button_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = amount.getText().toString();
                Integer cant = Integer.parseInt(name) + 1;
                amount.setText(cant.toString());
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
package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

import DataBase.Order;
import DataBase.Repositorio;
import model.Food;

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
        final Food food =(Food) b.get("food_picked");

        img = findViewById(R.id.food_details_product_img);
        product_name = findViewById(R.id.food_details_product_name);
        product_price = findViewById(R.id.food_details_product_price);
        amount = findViewById(R.id.food_details_editText_amount);

        if(b!=null)
        {
            img.setImageResource(food.getImagenId());
            product_name.setText(food.getName());
            product_price.setText(String.valueOf(food.getPrice()));

            Button fab = findViewById(R.id.food_details_button_add_order);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int amount2 = Integer.parseInt(amount.getText().toString());
                    //TODO revisar que la cantidad sea mayor a 0 y que el usuario tenga plata enla cuena para pedir, y si tiene hay que descontarsela
                    Order o1 = new Order(Repositorio.repo.getNroOrden(),amount2,food.getName(),String.valueOf(food.getPrice()),food.getImagenId());
                    Repositorio.repo.putOrder(o1);
                    Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();

                    openFinishOrder();
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
                if(number > 0)
                    number--;
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

    public void openFinishOrder(){
        Intent intent = new Intent(this,FinishOrder.class);
        startActivity(intent);
        finish();
    }
}
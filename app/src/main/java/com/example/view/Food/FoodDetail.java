package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.Interfaces.MainActivity;
import com.example.view.MyOrders.Fragment.Pendientes.Order;
import com.example.view.R;

public class FoodDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_persona_fragment);



        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        final Food j =(Food) b.get("food_picked");
        ImageView img = findViewById(R.id.imagen_detalleid);
        if(b!=null)
        {
            System.out.println("Se llego al fodd del j");

            img.setImageResource(j.getImagenid());
        }

        Button fab = findViewById(R.id.button_add_order);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order o1 = new Order(j.getNombre(),j.getFechanacimiento(),j.getImagenid());
                MainActivity.repo.putOrder(o1);
                Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton fab2 = findViewById(R.id.imageButton_close);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
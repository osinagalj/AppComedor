package com.example.view.Food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

public class FoodDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_persona_fragment);



        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        //final model.Food j =(model.Food) b.get("food_picked");
        ImageView img = findViewById(R.id.imagen_detalleid);
        if(b!=null)
        {
            System.out.println("Se llego al fodd del j");

           // img.setImageResource(j.getImagenId());
        }

        Button fab = findViewById(R.id.button_add_order);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Order o1 = new Order(j.getName(),String.valueOf(j.getPrice()),j.getImagenId());
                //MainActivity.repo.putOrder(o1);
                Toast.makeText(getBaseContext(), "Se ha realizado el pedido", Toast.LENGTH_SHORT).show();

                openFinishOrder();
            }
        });
        ImageButton fab2 = findViewById(R.id.imageButton_close);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        ImageButton less = findViewById(R.id.imageButton_less);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText amount = findViewById(R.id.editTextNumber);
                String name = amount.getText().toString();
                Integer cant = Integer.parseInt(name) - 1;
                amount.setText(cant.toString());
            }
        });

        ImageButton add = findViewById(R.id.imageButton_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText amount = findViewById(R.id.editTextNumber);
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
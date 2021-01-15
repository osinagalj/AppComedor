package com.example.view.MyOrders.Fragment.Confirmados;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

public class ActivityPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        ImageButton fab = findViewById(R.id.order_details_button_close);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //TODO HAY QUE HACER UN RECYCLERVIEW PARA LA DESCRIPCION DE LA ORDEN, CON CANTIDAD , NOMBRE , Y PRECIO EN TRES COLUMNAS
    }
}
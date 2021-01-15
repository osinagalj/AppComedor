package com.example.view.Food.Carrito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.Food.FinishOrder;
import com.example.view.R;

import java.util.ArrayList;

import Model.Product;
import Model.Restaurant;

public class Carrito extends AppCompatActivity {
        ImageView img;
        EditText amount;
        TextView product_name, product_price;

        AdapterCarrito adapterPend;
        RecyclerView recyclerViewPersonas;
        ArrayList<Product> listaFoods;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_carrito);
            recyclerViewPersonas = findViewById(R.id.recyclerView_carrito);
            listaFoods = new ArrayList<>();


            ImageButton button_close = findViewById(R.id.carrito_button_close);
            button_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO eliminar los productos de la orden actual
                    Restaurant.getInstance().miOrden = new ArrayList<>();
                    System.out.println("Agrego a la orde3333333333333333");
                    finish();
                }
            });

            Button button_make_order = findViewById(R.id.carrito_button_make_order);
            button_make_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO eliminar los productos de la orden actual


                    int nro = 12345;

                    Restaurant.getInstance().ordenesPendientes.add(new Model.Order(nro,Restaurant.getInstance().miOrden));
                    Restaurant.getInstance().miOrden = new ArrayList<>();


                    openFinishOrder();

                }
            });

            Button button_add_products = findViewById(R.id.carrito_button_add);
            button_add_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO no hay eliminar los productos de la orden actual

                    finish();
                }
            });

            cargarLista();
            mostrarData();
        }

    public void openFinishOrder(){
        Intent intent = new Intent(this, FinishOrder.class);
        startActivity(intent);
        this.finish();
    }



    public void cargarLista(){
        ArrayList<Product> ar = Restaurant.getInstance().miOrden;
        for(Product o : ar){
            listaFoods.add(o);
        }
    }


    private void mostrarData() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapterPend = new AdapterCarrito(this, listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);

    }
}
package com.example.view.Food.Carrito;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.BackEnd;
import com.example.view.Food.FinishOrder;
import com.example.view.R;

import java.util.ArrayList;

import Model.Product;

public class Carrito extends AppCompatActivity {
        ImageView img;
        EditText amount;
        TextView product_name, product_price, cartAmount;

        AdapterCarrito adapterPend;
        RecyclerView recyclerViewPersonas;
        ArrayList<Product> listaFoods;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_carrito);
            recyclerViewPersonas = findViewById(R.id.recyclerView_carrito);
            listaFoods = new ArrayList<>();
            cartAmount = findViewById(R.id.carrito_total_price);
            cartAmount.setText(String.valueOf(BackEnd.getLoggedUser().getCartTotalPrice()));

            ImageButton button_close = findViewById(R.id.carrito_button_close);
            button_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO eliminar los productos de la orden actual
                    if(!BackEnd.getLoggedUser().cartIsEmpty()){
                        showSimpleDialog(v);
                    }else{
                        finish();
                    }
                }
            });

            Button button_make_order = findViewById(R.id.carrito_button_make_order);
            button_make_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO eliminar los productos de la orden actual
                    if(BackEnd.getLoggedUser().getCartProducts().isEmpty()){
                        Toast.makeText(getBaseContext(), "No se puede realizar un pedido sin productos", Toast.LENGTH_SHORT).show();
                    }else{
                        BackEnd.getLoggedUser().confirmOrder();
                        BackEnd.getLoggedUser().clearCart();
                        openFinishOrder();
                    }
                }
            });

            Button button_add_products = findViewById(R.id.carrito_button_add);
            button_add_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        listaFoods.clear();
        listaFoods.addAll(BackEnd.getLoggedUser().getCartProducts());
    }

    private void mostrarData() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapterPend = new AdapterCarrito(this, listaFoods);
        recyclerViewPersonas.setAdapter(adapterPend);
    }

    public void showSimpleDialog(View view) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Cancelar Pedido");
        builder.setMessage("Se eliminaran los productos agregados al carrito");
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                BackEnd.getLoggedUser().clearCart();
                finish();
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
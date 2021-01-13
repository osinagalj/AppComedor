package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.Interfaces.MainActivity;
import com.example.view.R;

import ModeloGian.CommonUser;
import ModeloGian.Restaurant;

public class Login extends AppCompatActivity {

    private final Restaurant restaurant = Restaurant.getInstance();
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.login_button_sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUser user = restaurant.validateLoginData(111,"aaa"); //TODO obtener datos de la interfaz
                System.out.println(user.getBirthdate());
                if (user != null)
                    sign_in();
                else
                    System.out.println();// TODO mostrar error
            }
        });

        Button button_sign_up = findViewById(R.id.login_button_sign_up);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up();
            }
        });

    }

    public void sign_in() {
        Intent intent = new Intent(this, MainActivity.class);
        //TODO Pasarle a MainActivity el usuario que se logqea o puede ponerse en el modelo y despues pedirlo
        startActivity(intent);
    }

    public void sign_up() {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

}

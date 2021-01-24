package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.Menu.MainActivity;
import com.example.view.R;

import Model.CommonUser;
import Model.Restaurant;

public class Login extends AppCompatActivity {

    private final Restaurant restaurant = Restaurant.getInstance();
    private Button button;
    private EditText user_dni,user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.login_button_sign_in);
        user_dni = findViewById(R.id.login_editText_dni);
        user_password = findViewById(R.id.login_editText_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dni = 0;
                try {
                    dni = Integer.parseInt(user_dni.getText().toString());
                } catch (NumberFormatException numberFormatException){
                    badLoginData();
                }
                String password = user_password.getText().toString();
                CommonUser user = restaurant.validateLoginData(dni,password);
                if (user != null)
                    sign_in(user);
                else{
                    badLoginData();
                }
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

    public void badLoginData(){
        Toast.makeText(getBaseContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        user_dni.setText("");
        user_password.setText("");
    }

    public void sign_in(CommonUser logged_user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("LOGGED_USER",logged_user);
        startActivity(intent);
    }

    public void sign_up() {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

}

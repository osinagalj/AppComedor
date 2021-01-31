package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.DataHolder;
import com.example.view.Menu.MainActivity;
import com.example.view.databinding.ActivityLoginBinding;

import Model.CommonUser;
import DataBase.Restaurant;

public class Login extends AppCompatActivity {

    private final Restaurant restaurant = Restaurant.getInstance();

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dni = 0;
                try {
                    dni = Integer.parseInt(binding.userDni.getText().toString());
                } catch (NumberFormatException numberFormatException){
                    badLoginData();
                }
                String password = binding.userPassword.getText().toString();
                CommonUser user = restaurant.validateLoginData(dni,password);
                if (user != null)
                    sign_in(user);
                else{
                    badLoginData();
                }
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up();
            }
        });

    }

    public void badLoginData(){
        Toast.makeText(getBaseContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        binding.userDni.setText("");
        binding.userPassword.setText("");
    }

    public void sign_in(CommonUser logged_user) {
        Intent intent = new Intent(this, MainActivity.class);
        DataHolder.setLoggedUser(logged_user);
        startActivity(intent);
    }

    public void sign_up() {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

}

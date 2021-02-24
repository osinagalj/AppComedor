package com.example.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.BackEnd;
import com.example.view.menu.MainActivity;
import com.example.view.databinding.ActivityLoginBinding;

import dataBase.Restaurant;

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
                try {
                    if(BackEnd.setLoggedUser(dni,password)){
                        sign_in(dni,password);
                    }
                    else{
                        badLoginData();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

    public void sign_in(int dni, String password) throws InterruptedException {
        Intent intent = new Intent(this, MainActivity.class);
        BackEnd.setLoggedUser(dni,password);
        BackEnd.setDailyMenu();
        binding.userDni.setText("");
        binding.userPassword.setText("");
        startActivity(intent);
    }



    public void sign_up() {
        Intent intent = new Intent(this, ChooseCategory.class);
        startActivity(intent);
    }

}

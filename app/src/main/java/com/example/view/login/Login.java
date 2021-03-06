package com.example.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityLoginBinding;
import com.example.view.menu.MainActivity;

import model.CommonUser;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Data Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //ViewModel
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class); //ViewModel para la DB

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

                sign_in(dni,password);

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
        Toast.makeText(getBaseContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        binding.userDni.setText("");
        binding.userPassword.setText("");
    }

    public void sign_in(int dni, String password)  {

        mViewModel.getUser(dni).observe(this, new Observer<CommonUser>() {
            @Override
            public void onChanged(@Nullable CommonUser user) {
                if(user.getIdentityCardNumber() != -1 && user.getPassword().equals(password)){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    BackEnd.setLoggedUser(user);
                    binding.userDni.setText("");
                    binding.userPassword.setText("");
                    startActivity(intent);
                }else{
                    badLoginData();
                }
            }
        });

    }



    public void sign_up() {
        Intent intent = new Intent(this, ChooseCategory.class);
        startActivity(intent);
    }

}

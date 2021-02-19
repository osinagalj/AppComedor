package com.example.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.databinding.ActivityChooseCategoryBinding;

public class ChooseCategory extends AppCompatActivity {

    private ActivityChooseCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseCategoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpButtons();
    }

    private void setUpButtons(){

        //Button to close activity
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up("STUDENT");
            }
        });
        binding.btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up("TEACHER");
            }
        });
        binding.btnNoTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up("NO TEACHER");
            }
        });
        binding.btnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up("EXTERNAL");
            }
        });
    }

    private void sign_up(String category){

        //TODO Hay que hacer un if, si el usuario es valido, que buscamos en el archivo de users, y
        // la contraseña tmb, entonces hacemos el openActivity2, sino tiramos un Toast para avisar
        Intent intent = new Intent(this, Sign_up.class);
        intent.putExtra("USER_CATEGORY",category);
        startActivity(intent);
    }

}
package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

public class Sign_up_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button sign_up_button_4 = findViewById(R.id.sign_up_button_1);

        sign_up_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Hay que hacer un if, si el usuario es valido, que buscamos en el archivo de users, y
                // la contraseña tmb, entonces hacemos el openActivity2, sino tiramos un Toast para avisar
                sign_up_student();
            }
        });

        ImageButton btn_close = findViewById(R.id.sign_up_1_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void sign_up_student() {
        Intent intent = new Intent(this, Sign_up_student.class);
        intent.putExtra("USER_CATEGORY","STUDENT");
        startActivity(intent);
    }

}
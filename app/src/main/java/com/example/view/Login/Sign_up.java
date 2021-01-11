package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        //Button to close activity
        ImageButton btn_close = findViewById(R.id.choose_category_button_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Buttons category
        Button sign_up_button_1 = findViewById(R.id.choose_category_button_student);
        sign_up_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Hay que hacer un if, si el usuario es valido, que buscamos en el archivo de users, y
                // la contraseña tmb, entonces hacemos el openActivity2, sino tiramos un Toast para avisar
                sign_up_student();
            }
        });

        //Buttons category
        Button sign_up_button_2 = findViewById(R.id.choose_category_button_teacher);
        sign_up_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO idem
                sign_up_teacher();
            }
        });

        //Buttons category
        Button sign_up_button_3 = findViewById(R.id.choose_category_button_no_teacher);
        sign_up_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO idem
                sign_up_no_teacher();
            }
        });

        //Buttons category
        Button sign_up_button_4 = findViewById(R.id.choose_category_button_external);
        sign_up_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO rt
                sign_up_external();
            }
        });

    }

    public void sign_up_student() {
        Intent intent = new Intent(this, Sign_up_student.class);
        intent.putExtra("USER_CATEGORY","STUDENT");
        startActivity(intent);
    }
    public void sign_up_teacher() {
        Intent intent = new Intent(this, Sign_up_student.class);
        intent.putExtra("USER_CATEGORY","TEACHER");
        startActivity(intent);
    }
    public void sign_up_no_teacher() {
        Intent intent = new Intent(this, Sign_up_student.class);
        intent.putExtra("USER_CATEGORY","NO TEACHER");
        startActivity(intent);
    }
    public void sign_up_external() {
        Intent intent = new Intent(this, Sign_up_student.class);
        intent.putExtra("USER_CATEGORY","EXTERNAL");
        startActivity(intent);
    }

}
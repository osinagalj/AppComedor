package com.example.view.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

public class Sign_up_student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        ImageButton btn_close = findViewById(R.id.sign_up_student_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        View sign_up_label_dni = findViewById(R.id.sign_up_label_variable);
        sign_up_label_dni.setVisibility(View.VISIBLE);


    }
}
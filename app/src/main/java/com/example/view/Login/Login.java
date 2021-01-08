package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.Interfaces.MainActivity;
import com.example.view.R;

public class Login extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.login_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Hay que hacer un if, si el usuario es valido, que buscamos en el archivo de users, y
                // la contraseña tmb, entonces hacemos el openActivity2, sino tiramos un Toast para avisar
                openActivity2();
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

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sign_up() {
        Intent intent = new Intent(this, Sign_up_1.class);
        startActivity(intent);
    }

}

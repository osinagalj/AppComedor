package com.example.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.Main.MainActivity;
import com.example.view.R;

import ModeloGian.CommonUser;
import ModeloGian.Restaurant;

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

                //DNI y password que ingresa el usuario
                String dni = user_dni.getText().toString();
                String password = user_password.getText().toString();

                //TODO no lo paso por parametro porque sino hay que ingresar el usuario y el password siempre y paja
                //Habria que chekear que el dni sean solo numeros, y tenga tanta longitud, y alguna cosita para el password tmb, como que tenga una longitud minima

                //TODO si ingreso la password y no el user se rompe pa

                //CommonUser user = restaurant.validateLoginData(Integer.valueOf(dni),password);
                CommonUser user = restaurant.validateLoginData(111,"aaa"); //TODO obtener datos de la interfaz
                //System.out.println(user.getBirthdate()); TODO esto no puede ir aca, sino cuando mete el dato mal se rompe porque no existe el usuario
                if (user != null)
                    sign_in(user.getIdentityCardNumber());
                else{
                    Toast.makeText(getBaseContext(), "Usuario y/o contraaseña incorrectos", Toast.LENGTH_LONG).show();
                    //TODO capaz que podemos tirar el error si metio mal el usuario o la contraseña si te pinta
                    user_password.setText("");
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

    public void sign_in(int id_logged_user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ID_LOGGER_USER",id_logged_user); //TODO en el main activity lo obtenes con el intent.getExtra("LOGGER_USER")
        startActivity(intent);

    }

    public void sign_up() {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

}

package com.example.view.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

public class Sign_up_student extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        ImageButton btn_close = findViewById(R.id.sign_up_button_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        final String category =(String) b.get("USER_CATEGORY");

        TextView txt_category = findViewById(R.id.sign_up_label_category);



        Button brn_create_account = findViewById(R.id.sign_up_button_create_account);
        brn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(category);
            }
        });


        // declaración de switch
        EditText sign_up_label_dni = findViewById(R.id.sign_up_editText_variable);
        switch(category)
        {
            case "STUDENT" :
                txt_category.setText("Alumno");
                break;
            case "EXTERNAL" :
                txt_category.setText("Externo");
                break;
            case "TEACHER" :
                txt_category.setText("Docente");
                sign_up_label_dni.setVisibility(View.VISIBLE);
                sign_up_label_dni.setHint("Cantidad de Materias a cargo");
                break;
            case "NO TEACHER" :
                txt_category.setText("No Docente");
                sign_up_label_dni.setVisibility(View.VISIBLE);
                sign_up_label_dni.setHint("Antiguedad");
                break;
            //case "STUDENT" or EXTERNAL
            default:
                sign_up_label_dni.setVisibility(View.GONE);
                break;

        }
    }

    public void createAccount(String category){
        //TODO crear el usuario, y hay que chekear campo por campo que los datos esten bien y todo eso.
        //TODO Posiblemente haya que escribir en el archivo de usuarios para la persistencia
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
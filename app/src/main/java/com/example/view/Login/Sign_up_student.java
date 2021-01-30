package com.example.view.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.R;

import java.time.LocalDate;

import Model.Antiquity;
import Model.Category;
import Model.CommonUser;
import Model.Condition;
import Model.Discount;
import Model.PriceCalculator;
import Model.Restaurant;

public class Sign_up_student extends AppCompatActivity {

    private EditText dni,password,name,lastname;
    private RadioGroup condition;

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
        switch(category) {
            case "STUDENT" :
                txt_category.setText("Alumno");
                break;
            case "EXTERNAL" :
                txt_category.setText("Externo");
                break;
            case "TEACHER" :
                txt_category.setText("Docente");
                sign_up_label_dni.setVisibility(View.VISIBLE);
                sign_up_label_dni.setHint("Anos de antiguedad");
                break;
            case "NO TEACHER" :
                txt_category.setText("No Docente");
                sign_up_label_dni.setVisibility(View.VISIBLE);
                sign_up_label_dni.setHint("Anos de antiguedad");
                break;
            //case "STUDENT" or EXTERNAL
            default:
                sign_up_label_dni.setVisibility(View.GONE);
                break;
        }
    }

    public void createAccount(String category){
        dni = findViewById(R.id.sign_up_editText_dni);
        password = findViewById(R.id.sign_up_editText__password);
        name = findViewById(R.id.sign_up_editText_name);
        lastname = findViewById(R.id.sign_up_editText_lastName);
        condition = findViewById(R.id.sign_up_radiogroup_category); //TODO no es category, es condition
        int parsedICN = 0;
        PriceCalculator userCategory = null;
        try {
            parsedICN = Integer.parseInt(dni.getText().toString());
            if (!Restaurant.getInstance().isRegistered(parsedICN)) {
                switch (category) {
                    case "STUDENT":
                        userCategory = new Discount(15);
                        break;
                    case "EXTERNAL":
                        userCategory = new Discount(0);
                        break;
                    case "TEACHER":
                    case "NO TEACHER":
                        int yearsOfWork = Integer.parseInt(((EditText) findViewById(R.id.sign_up_editText_variable)).getText().toString());
                        userCategory = new Antiquity(LocalDate.of(LocalDate.now().getYear() - yearsOfWork, 1, 1));
                        break;
                }
            }
            else {
                badSignUpData();
            }
        } catch (NumberFormatException numberFormatException){
            numberFormatException.printStackTrace();
            badSignUpData();
        }
        int conditionId = condition.getCheckedRadioButtonId();
        Condition newUserCondition;
        if (conditionId != -1) {
            RadioButton radioButton = condition.findViewById(conditionId);
            String conditionName = radioButton.getText().toString();
            newUserCondition = Restaurant.getInstance().getCondition(conditionName);
        } else {
            newUserCondition = Restaurant.getInstance().getCondition("Ninguna");
        }
        CommonUser newUser = new CommonUser(password.getText().toString(), name.getText().toString(), lastname.getText().toString(),
                            LocalDate.of(1, 1, 1), parsedICN, newUserCondition,Category.ALUMNO, userCategory);
        //TODO get the user category no category.Alumno
        Restaurant.getInstance().addUser(newUser);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void badSignUpData(){
        Toast.makeText(getBaseContext(), "Datos de registro incorrectos", Toast.LENGTH_LONG).show();
    }
}
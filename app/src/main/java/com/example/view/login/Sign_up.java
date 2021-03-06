package com.example.view.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivitySignUpBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.Category;
import model.CommonUser;

public class Sign_up extends AppCompatActivity {


    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //get the category selected in Sign_up view
        final String category =(String) getIntent().getExtras().get("USER_CATEGORY");

        setButtons(category);
        setCategoryLabel(category);
    }

    private void setCategoryLabel(String category){

        switch(category) {
            case "STUDENT" :
                binding.labelCategory.setText("Alumno");
                binding.antiquity.setVisibility(View.GONE);
                break;
            case "EXTERNAL" :
                binding.labelCategory.setText("Externo");
                binding.antiquity.setVisibility(View.GONE);
                break;
            case "TEACHER" :
                binding.labelCategory.setText("Docente");
                binding.antiquity.setVisibility(View.VISIBLE);
                binding.antiquity.setHint("Anos de antiguedad");
                break;
            case "NO TEACHER" :
                binding.labelCategory.setText("No Docente");
                binding.antiquity.setVisibility(View.VISIBLE);
                binding.antiquity.setHint("Anos de antiguedad");
                break;
        }
    }

    private void setButtons(String category){
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(category);
            }
        });
    }

    private void createAccount(String category){

        int parsedICN = 0;
        parsedICN = Integer.parseInt(binding.dni.getText().toString());
        rigthSignUp(parsedICN,category);

    }

    private void rigthSignUp(int parsedICN, String category){
        //TODO falta agregar chequeos en varios campos, que no esten vacios por ejemplo, y que no este en uso el dni ya por ejemplo

        int conditionId = binding.condition.getCheckedRadioButtonId();
        int newUserCondition;
        if (conditionId != -1) {
            RadioButton radioButton = binding.condition.findViewById(conditionId);
            String conditionName = radioButton.getText().toString();
            //newUserCondition = Restaurant.getInstance().getCondition(conditionName);
            newUserCondition = 1;//TODO get the righ condition
        } else {
            newUserCondition = 0;
        }

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        CommonUser newUser = new CommonUser(
                parsedICN,
                binding.password.getText().toString(),
                0f,
                binding.name.getText().toString(),
                binding.lastName.getText().toString(),
                date,
                newUserCondition,
                getCategory(category)
        );

        BackEnd.addUser(newUser);

        success();
    }

    private Category getCategory(String category){
        switch (category) {
            case "STUDENT":
                return Category.ALUMNO;
            case "EXTERNAL":
                return Category.EXTERNO;
            case "TEACHER":
                return Category.DOCENTE;
            case "NO TEACHER":
                return Category.NO_DOCENTE;
        }
        return null;
    }


    public void success() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Registro exitoso!");
        builder.setMessage("La cuenta se ha creado con exito");
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                //Go back to login
                Intent intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);

                finish();
            }
        });

        // Create the AlertDialog object and return it
        builder.create().show();
    }


    private void showAlert(){
        Toast.makeText(this, "Datos de registro incorrectos", Toast.LENGTH_LONG).show();
    }
}
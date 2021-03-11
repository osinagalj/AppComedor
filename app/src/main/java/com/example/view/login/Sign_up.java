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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import model.Category;
import model.CommonUser;
import model.PriceAntiquity;
import model.PriceFixedDiscount;
import model.PriceSubjects;

public class Sign_up extends AppCompatActivity {


    private ActivitySignUpBinding binding;
    private Category newUserCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //get the category selected in Sign_up view
        final String category =(String) getIntent().getExtras().get("USER_CATEGORY");

        setButtons();
        setCategoryLabel(category);
    }

    private void setCategoryLabel(String category){
        switch(category) {
            case "STUDENT" :
                binding.labelCategory.setText("Alumno");
                binding.antiquity.setVisibility(View.GONE);
                newUserCategory = Category.ALUMNO;
                break;
            case "EXTERNAL" :
                binding.labelCategory.setText("Externo");
                binding.antiquity.setVisibility(View.GONE);
                newUserCategory = Category.EXTERNO;
                break;
            case "TEACHER" :
                binding.labelCategory.setText("Docente");
                binding.antiquity.setVisibility(View.VISIBLE);
                binding.antiquity.setHint("Cantidad de Materias");
                newUserCategory = Category.DOCENTE;
                break;
            case "NO TEACHER" :
                binding.labelCategory.setText("No Docente");
                binding.antiquity.setVisibility(View.VISIBLE);
                binding.antiquity.setHint("Anos de antiguedad");
                newUserCategory = Category.NO_DOCENTE;
                break;
        }
    }

    private void setButtons(){
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(Integer.parseInt(binding.dni.getText().toString()));
            }
        });
    }

    private void createAccount(int parsedICN){
        int conditionId = binding.condition.getCheckedRadioButtonId();
        int newUserCondition;
        if (conditionId != -1) {
            RadioButton radioButton = binding.condition.findViewById(conditionId);
            String conditionName = radioButton.getText().toString();
            switch (conditionName){
                case "Vegetariano":
                    newUserCondition = 1;
                    break;
                case "Celiaco":
                    newUserCondition = 2;
                    break;
                case "Vegano":
                    newUserCondition = 3;
                    break;
                default:
                    newUserCondition = 0;
            }
        } else {
            newUserCondition = 0;
        }

        CommonUser newUser = new CommonUser(parsedICN,
                binding.password.getText().toString(),
                5000f,
                binding.name.getText().toString(),
                binding.lastName.getText().toString(),
                new Date(),
                newUserCondition,
                newUserCategory);

        switch (newUser.getCategory()) {
            case ALUMNO:
                newUser.setDiscountCalculator(new PriceFixedDiscount(0.6f));
                break;
            case DOCENTE:
                newUser.addAttribute("subjects",Integer.parseInt(binding.antiquity.getText().toString()));
                newUser.setDiscountCalculator(new PriceSubjects(2));
                break;
            case NO_DOCENTE:
                System.out.println("Fecha 01/01/"+(LocalDate.now().getYear()-Integer.parseInt(binding.antiquity.getText().toString())));
                try {
                    newUser.addAttribute("startDate",new SimpleDateFormat("dd/MM/yyyy").parse("01/01/"+(LocalDate.now().getYear()-Integer.parseInt(binding.antiquity.getText().toString()))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(newUser.getAttribute("startDate"));
                newUser.setDiscountCalculator(new PriceAntiquity((Date) newUser.getAttribute("startDate")));
                break;
            default:
                newUser.setDiscountCalculator(new PriceFixedDiscount(0f));
        }
        BackEnd.addUser(newUser);
        success();
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
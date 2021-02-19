package com.example.view.myAccount;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view.BackEnd;
import com.example.view.databinding.ActivityChangePasswordBinding;

public class Activity_change_password extends AppCompatActivity {

    private ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpButtons();

    }

    private void setUpButtons(){
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        binding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }

        });
    }

    private void changePassword(){
        if(binding.oldPassword.getText().toString().equals(BackEnd.getLoggedUser().getPassword()))
            if(binding.newPassword.getText().toString().equals(binding.newPassword2.getText().toString())){
                BackEnd.changePassword(binding.newPassword2.getText().toString());
                showSimpleDialog();
            }else{
                Toast.makeText(getBaseContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }
        else
            Toast.makeText(getBaseContext(), "La contraseña actual es incorrecta", Toast.LENGTH_LONG).show();
    }

    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Cambio de contraseña");
        builder.setMessage("Se ha realizado el cambio de contraseña con exito");
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

}
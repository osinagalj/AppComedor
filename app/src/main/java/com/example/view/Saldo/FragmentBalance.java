package com.example.view.Saldo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.view.BackEnd;
import com.example.view.databinding.FragmentBalanceBinding;

public class FragmentBalance extends Fragment {

    private FragmentBalanceBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Mi Saldo");

        binding = FragmentBalanceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.userAmount.setText(String.valueOf(BackEnd.getLoggedUser().getBalance()));

        setUpButtons(view);
        return view;
    }

    private void setUpButtons(View view) {

        binding.btnLoadAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.loadAmount.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "No ha indicado la platita todavia", Toast.LENGTH_LONG).show();
                }else{
                    float amount = Float.parseFloat(binding.loadAmount.getText().toString());
                    loadMoney(amount);
                }
            }
        });

        binding.btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.trasnferDni.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "No ha indicado el usuario destino pete", Toast.LENGTH_LONG).show();
                }else{
                    if(binding.transferAmount.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "No ha indicado la cantidad pete", Toast.LENGTH_LONG).show();
                    }else{
                        int dni = Integer.parseInt(binding.trasnferDni.getText().toString());
                        float amount = Float.parseFloat(binding.transferAmount.getText().toString());
                        transferMoney(dni,amount);
                    }
                }
            }
        });

    }

    private void loadMoney(float amount){
        //TODO el dni del usuario que hace la trasnferencia lo sacas del logged user
        Toast.makeText(getContext(), "Se ha cargado bien la platita", Toast.LENGTH_LONG).show();
        binding.loadAmount.getText().clear();
    }

    private void transferMoney(int DNIuserDestino,float amount){
        //TODO el dni del usuario que hace la trasnferencia lo sacas del logged user
        Toast.makeText(getContext(), "ya le diste la platita a tu compa", Toast.LENGTH_LONG).show();
        binding.transferAmount.getText().clear();
    }


}

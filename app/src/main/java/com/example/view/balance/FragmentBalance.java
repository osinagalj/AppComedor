package com.example.view.balance;

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

        String full_name = BackEnd.getLoggedUser().getNames() + " " +BackEnd.getLoggedUser().getLastName();
        binding.userName.setText(full_name);

        setUpButtons(view);
        return view;
    }

    private void setUpButtons(View view) {

        binding.btnLoadAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.loadAmount.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "No ha indicado el monto", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getContext(), "No ha indicado el usuario destino ", Toast.LENGTH_LONG).show();
                }else{
                    if(binding.transferAmount.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "No ha indicado la cantidad ", Toast.LENGTH_LONG).show();
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
        if(BackEnd.loadMoney(amount)){
            Toast.makeText(getContext(), "El dinero ha sido acreditado correctamente", Toast.LENGTH_LONG).show();
            binding.userAmount.setText(String.valueOf(BackEnd.getLoggedUser().getBalance()));
        }
        else
            Toast.makeText(getContext(), "No se ha podido cargar el dinero", Toast.LENGTH_LONG).show();


        binding.loadAmount.getText().clear();
    }

    private void transferMoney(int DNIuserDestino,float amount){

        if(BackEnd.transferMoney(DNIuserDestino,amount)){
            Toast.makeText(getContext(), "La transferencia ha sido exitosa", Toast.LENGTH_LONG).show();
            binding.userAmount.setText(String.valueOf(BackEnd.getLoggedUser().getBalance()));
        }
        else
            Toast.makeText(getContext(), "No se ha podido relizar la transferencia", Toast.LENGTH_LONG).show();

        binding.transferAmount.getText().clear();
    }


}

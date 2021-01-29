package com.example.view.Saldo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.view.DataHolder;
import com.example.view.R;

public class fragment_balance extends Fragment {
    TextView balance;
    EditText transfer_amount, trasnfer_dni, load_amount;
    Button btn_transfer_amount,btn_load_amount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Mi Saldo");

        //Edit Text fields
        load_amount = view.findViewById(R.id.balance_editText_load_amount);
        transfer_amount = view.findViewById(R.id.balance_editText_transfer_amount);
        trasnfer_dni = view.findViewById(R.id.balance_editText_transfer_receiver); //dni del usuario destino

        //btn
        btn_transfer_amount = view.findViewById(R.id.balance_button_transfer_amount);
        btn_load_amount = view.findViewById(R.id.balance_buttont_load_amount);

        balance = view.findViewById(R.id.balance_label_profile_amount);
        balance.setText(String.valueOf(DataHolder.getLoggedUser().getBalance()));

        setUpButtons(view);
        return view;
    }

    private void setUpButtons(View view) {

        btn_load_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(load_amount.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "No ha indicado la platita todavia", Toast.LENGTH_LONG).show();
                }else{
                    float amount = Float.parseFloat(load_amount.getText().toString());
                    loadMoney(amount);
                }
            }
        });

        btn_transfer_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(trasnfer_dni.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "No ha indicado el usuario destino pete", Toast.LENGTH_LONG).show();
                }else{
                    if(transfer_amount.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "No ha indicado la cantidad pete", Toast.LENGTH_LONG).show();
                    }else{
                        int dni = Integer.parseInt(trasnfer_dni.getText().toString());
                        float amount = Float.parseFloat(transfer_amount.getText().toString());
                        transferMoney(dni,amount);
                    }
                }
            }
        });

    }

    private void loadMoney(float amount){
        //TODO el dni del usuario que hace la trasnferencia lo sacas del logged user
        Toast.makeText(getContext(), "Se ha cargado bien la platita", Toast.LENGTH_LONG).show();
        load_amount.getText().clear();
    }

    private void transferMoney(int DNIuserDestino,float amount){
        //TODO el dni del usuario que hace la trasnferencia lo sacas del logged user
        Toast.makeText(getContext(), "ya le diste la platita a tu compa", Toast.LENGTH_LONG).show();
        transfer_amount.getText().clear();
    }


}

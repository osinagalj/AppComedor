package com.example.navigationdrawerpractica.Saldo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.MiCuenta.Adapter_user;
import com.example.navigationdrawerpractica.MiCuenta.User_data;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class fragment_balance extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        return view;

    }
    @Override
    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Mi Saldo");
        super.onAttach(context);
    }
}

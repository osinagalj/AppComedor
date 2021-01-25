package com.example.view.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.view.DataHolder;
import com.example.view.Menu.MainActivity;
import com.example.view.R;

import java.util.Objects;

import DAO.UserDAO;
import Model.CommonUser;

public class fragmen_myAccount extends Fragment {

    TextView name,lastName,dni,category, condition, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        setUserData(view, DataHolder.getLoggedUser());

        Button button_change_password = view.findViewById(R.id.myAccount_button_change_password);
        button_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_password();
            }
        });
        return view;
    }

    private void setUserData(View view,CommonUser user){
        name = view.findViewById(R.id.myAccount_label_name);
        lastName = view.findViewById(R.id.myAccount_label_lastName);
        dni = view.findViewById(R.id.myAccount_label_dni);
        category = view.findViewById(R.id.myAccount_label_category);
        condition = view.findViewById(R.id.myAccount_label_condition);
        password = view.findViewById(R.id.myAccount_label_password);

        name.setText(user.getNames());
        lastName.setText(user.getLastname());
        dni.setText(String.valueOf(user.getIdentityCardNumber()));
        category.setText(user.getPriceCalculator().toString());
        condition.setText(user.getConditionName());
        password.setText(user.getPassword());
    }
    public void change_password() {
        Intent intent = new Intent(getContext(), Activity_change_password.class);
        startActivity(intent);
    }

}
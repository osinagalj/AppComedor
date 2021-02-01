package com.example.view.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.view.BackEnd;
import com.example.view.databinding.FragmentMyaccountBinding;

import Model.CommonUser;

public class FragmentMyAccount extends Fragment {

    private FragmentMyaccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Mi Cuenta");
        super.onCreate(savedInstanceState);
        binding = FragmentMyaccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setUserData(BackEnd.getLoggedUser());
        setButtons();

        return view;
    }


    private void setUserData(CommonUser user){
        binding.myAccountLabelName.setText(user.getNames());
        binding.myAccountLabelLastName.setText(user.getLastname());
        binding.myAccountLabelDni.setText(String.valueOf(user.getIdentityCardNumber()));
        binding.myAccountLabelCategory.setText(user.getCategory());
        binding.myAccountLabelCondition.setText(user.getConditionName());
        binding.myAccountLabelPassword.setText(user.getPassword());
        //binding.myAccountImageViewProfile.setImageResource();
    }

    private void change_password() {
        Intent intent = new Intent(getContext(), Activity_change_password.class);
        startActivity(intent);
    }

    private void setButtons(){

        binding.myAccountButtonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_password();
            }
        });
    }

}
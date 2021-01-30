package com.example.view.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.view.DataHolder;
import com.example.view.databinding.FragmentMyaccountBinding;

import Model.CommonUser;

public class FragmentMyAccount extends Fragment {

    TextView name,lastName,dni,category, condition, password;

    private FragmentMyaccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentMyaccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setUserData(DataHolder.getLoggedUser());
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
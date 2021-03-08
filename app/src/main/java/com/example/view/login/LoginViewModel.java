package com.example.view.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dao.UserDAO;
import model.CommonUser;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<CommonUser> getUser(int icn){
        return UserDAO.getUser(icn);
    }
}

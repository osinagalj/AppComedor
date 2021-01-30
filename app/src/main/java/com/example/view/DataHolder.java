package com.example.view;

import DAO.UserDAO;
import Model.CommonUser;

public class DataHolder {
    private static CommonUser loggedUser = UserDAO.registeredUsers().get(0); //TODO inicializado solo para saltear el login en el manifest

    public static CommonUser getLoggedUser() {
        return loggedUser;
    }


    public static void setLoggedUser(CommonUser loggedUser) {
        DataHolder.loggedUser = loggedUser;
    }
}

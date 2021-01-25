package com.example.view;

import Model.CommonUser;

public class DataHolder {
    private static CommonUser loggedUser;

    public static CommonUser getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(CommonUser loggedUser) {
        DataHolder.loggedUser = loggedUser;
    }
}

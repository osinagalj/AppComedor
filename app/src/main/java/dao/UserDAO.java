package dao;

import dataBase.Restaurant;
import model.CommonUser;

public class UserDAO {


    //@GET
    public static CommonUser getUser(int dni, String password){
        return Restaurant.getInstance().validateLoginData(dni,password); //devolver una copia no esto
    }
/*
    //@GET
    public static int getUserMenus(int dni, String password){
        return Restaurant.getInstance().validateLoginData(dni,password);
    }
*/
    //@POST
    public static void addUser(CommonUser user){
        Restaurant.getInstance().addUser(user);
    }

    //@UPDATE
    public static boolean loadMoney(int icn, float amount){
        return Restaurant.getInstance().loadMoney(icn,amount); //Return false if the operation fails
    }

    //@UPDATE
    public static void changePassword(int dni, String password){
        Restaurant.getInstance().changePassword(dni,password);
    }



}

package dao;

import dataBase.Restaurant;
import model.CommonUser;

public class UserDAO {



    public static void addUser2(CommonUser user){
        Restaurant.getInstance().db.collection("users2").document(String.valueOf(user.getIdentityCardNumber())).set(user);
    }

    //@UPDATE
    public static boolean loadMoney(int icn, float amount){
        //return Restaurant.getInstance().loadMoney(icn,amount); //Return false if the operation fails TODO
        return true;
    }

    //@UPDATE
    public static void changePassword(int icn, String password){
        Restaurant.getInstance().db.collection("users2").document(String.valueOf(icn))
                .update(
                        "password", password
                );

        //Restaurant.getInstance().changePassword(dni,password); // TODO
    }


}

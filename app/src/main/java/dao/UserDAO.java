package dao;

import java.util.HashMap;
import java.util.Map;

import dataBase.Restaurant;
import model.CommonUser;

public class UserDAO {


    //@GET
    public static void getUser(int dni, String password) {}

    //@POST
    public static void addUser(CommonUser user){

        // Create a new user with a first and last name
        Map<String, Object> user_db = new HashMap<>();
        user_db.put("identityCardNumber", user.getIdentityCardNumber());
        user_db.put("password", user.getPassword());
        user_db.put("names", user.getNames());
        user_db.put("lastName", user.getLastname());
        user_db.put("birthDate", user.getBirthdate().toString());
        user_db.put("balance", user.getBalance());
        user_db.put("condition", user.getCondition().toString());
        user_db.put("category", user.getCategory());

        // Add a new document with a generated ID
        Restaurant.getInstance().db.collection("users").document(String.valueOf(user.getIdentityCardNumber())).set(user_db);

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

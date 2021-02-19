package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataBase.Restaurant;
import model.CommonUser;
import model.Condition;

public class UserDAO {


    //@GET
    public static CommonUser getUser(int dni, String password){
        return Restaurant.getInstance().validateLoginData(dni,password); //devolver una copia no esto
    }

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








    //Que hace esto aca? capaz esto va en el back-end
    public static List<Condition> usersConditions() {
        List<Condition> conditions = new ArrayList<>();
        Set<String> noConsumable = new HashSet<>();
        noConsumable.add("trigo");
        noConsumable.add("cebada");
        noConsumable.add("avena");
        noConsumable.add("centeno");
        conditions.add(new Condition("Celiaco", noConsumable));
        noConsumable = new HashSet<>();
        noConsumable.add("carne");
        noConsumable.add("pollo");
        noConsumable.add("cerdo");
        noConsumable.add("grasa animal");
        conditions.add(new Condition("Vegetariano", noConsumable));
        noConsumable = new HashSet<>();
        conditions.add(new Condition("Ninguna", noConsumable));
        return conditions;
    }
}

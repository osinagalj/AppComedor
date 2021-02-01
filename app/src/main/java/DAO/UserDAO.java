package DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DataBase.Restaurant;
import Model.CommonUser;
import Model.Condition;

public class UserDAO {



    public static CommonUser getUser(int dni, String password){
        return Restaurant.getInstance().getUser(dni,password); //devolver una copia no esto
    }

    public static void loadMoney(int dni, float amount){
        //TODO Cargar plata al usuario
    }

    public static boolean chargeUser(int dni, float amount){
        //TODO eliminar plata del usuario
        return true;
    }

    public static void changePassword(int dni, String password){
        Restaurant.getInstance().changePassword(dni,password);
    }

    public static void addUser(CommonUser user){
        Restaurant.getInstance().addUser(user);
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

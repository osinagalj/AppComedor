package DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Model.CommonUser;
import Model.Condition;
import Model.Discount;

public class UserDAO {

    public static CommonUser getLoggedUser(){
        CommonUser user = new CommonUser("Lauta","password123.","Lautaro", "Osinaga", LocalDate.of(1999,5,20), 111,new Condition("Celiaco",new HashSet<>()),new Discount(10));
        //TODO devolver el usuario
        return user;
    }

    public static CommonUser getUserById(int id){
        CommonUser user = new CommonUser("aaa","aaa","Juan", "Perez", LocalDate.of(2000,1,15), 111,new Condition("Celiaco",new HashSet<>()),new Discount(10));
        //TODO devolver el usuario
        return user;
    }
    public static List<CommonUser> registeredUsers() {
        List<CommonUser> users = new ArrayList<>();
     //TODO Hay que levantar los usuarios del archivo en DataBase, no crear usuarios nuevos
        users.add(new CommonUser("Lauta","password123.","Lautaro", "Osinaga", LocalDate.of(1999,5,20), 111,new Condition("Celiaco",new HashSet<>()),new Discount(10)));
        return users;
    }
    
}

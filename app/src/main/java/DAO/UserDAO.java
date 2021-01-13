package DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ModeloGian.CommonUser;
import ModeloGian.Condition;
import ModeloGian.Discount;

public class UserDAO {
    public static List<CommonUser> registeredUsers(){
        List<CommonUser> users = new ArrayList<>();
        users.add(new CommonUser("aaa","aaa","Juan", "Perez", LocalDate.of(2000,1,15), 111,new Condition("Celiaco",new HashSet<>()),new Discount(10)));
        return users;
    }
}

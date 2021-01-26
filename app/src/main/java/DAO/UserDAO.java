package DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Model.CommonUser;
import Model.Condition;
import Model.Discount;

public class UserDAO {

    public static List<CommonUser> registeredUsers() {
        List<CommonUser> users = new ArrayList<>();
        CommonUser user = new CommonUser("111","Lautaro", "Osinaga", LocalDate.of(1999,5,20), 111,new Condition("Celiaco",new HashSet<>()),new Discount(10));
        user.setBalance(1000);
        user.addConfirmedOrder(OrderDAO.getCompletedOrders(user).get(0));
        users.add(user);
        return users;
    }

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

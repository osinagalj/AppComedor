package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SuperUser extends User{

    private HashMap<String, Date> accessList;

    public SuperUser(String userName, String passWord, String name, String lastName, int dni){
        super(passWord, name, lastName,  dni);
    }

    public void addFood(Restaurant r, Food f){
        r.addFood(f);
    }
    public void addStock(Restaurant r,int idFood, int stock){
        r.addStock(idFood,stock);
    }
    //public void removeUser(model.User)
    public void modifyPrice(Restaurant f, int idFood, int newPrice){

    }
}

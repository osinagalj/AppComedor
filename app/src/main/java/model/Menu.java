package model;

import java.util.ArrayList;
import java.util.Date;

public class Menu {

    public ArrayList<DailyMenu> foods = new ArrayList<>(); //change to private
    private Date date;

    public Menu(Date date){
        this.date = date;
    }

    /**Gets the menu corresponding to the user's condition*/
    public void add(DailyMenu menu){
        for(DailyMenu food: foods){
             if(food.getConditions() == menu.getConditions()) //todo
                return;
        }
        //Si no existe una comida con la misma condicion
        foods.add(menu);
    }

    /**Gets the menu corresponding to the user's condition*/
    public DailyMenu getMenu(CommonUser user){
        for(DailyMenu food: foods){
            if(user.canConsume(food.getConditions())) //si la condicion del usuario no esta en la lista de condiciones no posibles
                return food;
        }
        return null;
    }

    public Date getDate(){
        return date;
    }
    public ArrayList<DailyMenu> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<DailyMenu> foods) {
        this.foods = foods;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

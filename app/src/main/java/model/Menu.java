package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Menu {

    public ArrayList<DailyMenu> foods = new ArrayList<>(); //change to private

    private LocalDate date;

    public Menu(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void add(DailyMenu menu){

        for(DailyMenu food: foods){
            if(food.getConditions() == menu.getConditions()) //todo
                return;
        }
        //Si no existe una comida con la misma condicion
        foods.add(menu);
    }

    public DailyMenu getMenu(CommonUser user){
        for(DailyMenu food: foods){
            for(int condition : food.getConditions()){
                if(condition == user.getCondition())
                    return food;
            }

        }
        return null;
    }

}

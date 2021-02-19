package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Menu {
    private int cant = 3; //TODO tiene que ser la cantidad de condiones que haya
    private ArrayList<DailyMenu> foods = new ArrayList<>();

    private LocalDate date;

    public Menu(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void add(DailyMenu menu){
        if(cant > foods.size()){
            for(DailyMenu food: foods){
                if(food.getCondition().equals(menu.getCondition()))
                    return;
            }
            //Si no existe una comida con la misma condicion
            foods.add(menu);
        }
    }

    public DailyMenu getMenu(CommonUser user){
        for(DailyMenu food: foods){
            if(food.getCondition().equals(user.getCondition()))
                return food;
        }
        return null;
    }

}

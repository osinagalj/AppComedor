package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Menu {
    private int cant = 4; //TODO tiene que ser la cantidad de condiones que haya
    public ArrayList<DailyMenu> foods = new ArrayList<>(); //change to private

    private LocalDate date;

    public Menu(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void add(DailyMenu menu){

        if(cant >= foods.size()){
            for(DailyMenu food: foods){
                if(food.getCondition() == menu.getCondition())
                    return;
            }
            //Si no existe una comida con la misma condicion
            foods.add(menu);
        }
    }

    public DailyMenu getMenu(CommonUser user){
        System.out.println("tamaño del menu = "+foods.size());
        for(DailyMenu food: foods){
            System.out.println("food_id = "+food.getId());

            for(int condition : food.getCondition()){
                System.out.println("food_condition = "+condition);
                System.out.println("user condition = "+user.getCondition());
                if(condition == user.getCondition())
                    return food;
            }

        }
        System.out.println("No encontro un menu valido f xd");
        return null;
    }

}

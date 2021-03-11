package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Menu {

    private List<DailyMenu> dailyMenusOptions = new ArrayList<>();
    private Date date;

    public Menu(Date date){
        this.date = date;
    }

    /**
     * Add a new daily menu that does not have the same conditions (who can consume) as the previous one.
     * @param menu the new menu
     */
    public void add(DailyMenu menu){
        for(DailyMenu food: dailyMenusOptions){
             if(food.getConditions() == menu.getConditions())
                return;
        }
        //Si no existe una comida con la misma condicion
        dailyMenusOptions.add(menu);
    }

    /**
     * Gets the menu corresponding to the user's condition
     * @param user for whom you wish to obtain the menu
     * @return the menu corresponding to the user if there is one that suits his conditions, otherwise null
     */
    public DailyMenu getMenu(CommonUser user){
        for(DailyMenu food: dailyMenusOptions){
            if(user.canConsume(food.getConditions())) //si la condicion del usuario no esta en la lista de condiciones no posibles
                return (DailyMenu)food.clone();
        }
        return null;
    }

    public Date getDate(){
        return (Date) date.clone();
    }

    public List<DailyMenu> getFoods() {
        return Collections.unmodifiableList(dailyMenusOptions);
    }

    public void setFoods(List<DailyMenu> foods) {
        this.dailyMenusOptions = foods;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

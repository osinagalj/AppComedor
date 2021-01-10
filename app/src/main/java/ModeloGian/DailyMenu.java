package ModeloGian;

import java.util.List;

public class DailyMenu extends Food{
    private int dailyLimit;

    public DailyMenu(String name, String description, int stock, float price, int dailyLimit) {
        //super(name, description, stock, price);
        this.dailyLimit = dailyLimit;
    }

    public DailyMenu(String name, String description, int stock, float price, List<String> ingredients) {
        //super(name, description, stock, price, ingredients);
    }

}

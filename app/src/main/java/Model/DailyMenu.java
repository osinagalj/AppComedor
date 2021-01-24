package Model;

import java.io.Serializable;
import java.util.List;

public class DailyMenu extends Food implements Serializable {

    public DailyMenu(int id, String name, String description, int imgId, ProductCategory category, int stock, float price) {
        super(id, name, description, imgId, category, stock, price);
    }

    public DailyMenu(int id, String name, String description, int imgId, ProductCategory category, int stock, float price, List<String> ingredients) {
        super(id, name, description, imgId, category, stock, price,ingredients);
    }

    @Override
    public int getDailyLimit() {
        return Restaurant.MAX_SPECIAL_ORDERS;
    }
}

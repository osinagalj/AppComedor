package ModeloGian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combo extends Product{
    private List<Product> comboItems;
    private int discount;


    public Combo(int id,String name, String description, int imgId, List<Product> comboItems, int discount) {
        super(id, name, description,imgId);
        this.comboItems = comboItems;
        this.discount = discount;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients= new ArrayList<>();
        for (Product item : comboItems)
            ingredients.addAll(item.getIngredients());

        return Collections.unmodifiableList(ingredients);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public int getStock() {
        return 0;
    }

    @Override
    public int getDailyLimit() {
        return 0;
    }
}

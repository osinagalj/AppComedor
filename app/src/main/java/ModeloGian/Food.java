package ModeloGian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Food extends Product {
    private int stock;
    private float price;
    private List<String> ingredients;

    protected Food(){}

    @Override
    public List<String> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public Food(int id, String name, String description, int stock, float price) {
        super(id, name, description);
        this.stock = stock;
        this.price = price;
        this.ingredients = new ArrayList<>();
    }

    public Food(int id,String name, String description, int stock, float price, List<String> ingredients) {
        super(id,name, description);
        this.stock = stock;
        this.price = price;
        this.ingredients = ingredients;
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

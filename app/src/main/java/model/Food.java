package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Food extends Product implements Serializable {

    private int stock;
    private float price;
    private List<String> ingredients;

    public Food(int id, String name, String description, int imgId, ProductCategory category, Condition condition, int stock, float price) {
        super(id, name, description, imgId, category,condition);
        this.stock = stock;
        this.price = price;
        this.ingredients = new ArrayList<>();
    }

    @Override
    public boolean toHome() {
        return false;
    }

    @Override
    public float getPrice(CommonUser user) {
        return price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "stock=" + stock +
                ", price=" + price +
                ", ingredients=" + ingredients +
                "} " + super.toString();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(this);
        return Collections.unmodifiableList(products);
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void addStock(int stock) {
        this.stock+=stock;
    }

    @Override
    public int getDailyLimit() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean decreaseStock(int amount) {
        if(stock >= amount ){
            stock-=amount;
            return true;
        }
        return false;
    }
}

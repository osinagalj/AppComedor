package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Food extends Product  {



    private int stock;
    private float price;

    public Food(){}

    public Food(int id, String name, String description, int imgId, int productCategory, int stock, float price) {
        super(id, name, description, imgId, productCategory);
        this.stock = stock;
        this.price = price;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

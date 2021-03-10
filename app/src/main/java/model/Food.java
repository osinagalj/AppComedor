package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Food extends Product implements Serializable {

    private int stock2;
    private float price2;

    public Food(){}

    public Food(int id, String name, String description, int imgId, int productCategory, int stock2, float price2) {
        super(id, name, description, imgId, productCategory);
        this.stock2 = stock2;
        this.price2 = price2;
    }

    public int getStock2() {
        return stock2;
    }

    public void setStock2(int stock2) {
        this.stock2 = stock2;
    }

    public float getPrice2() {
        return price2;
    }

    public void setPrice2(float price2) {
        this.price2 = price2;
    }


    @Override
    public boolean toHome() {
        return false;
    }

    @Override
    public float getPrice(CommonUser user) {
        return price2;
    }

    @Override
    public String toString() {
        return "Food{" +
                "stock=" + stock2 +
                ", price=" + price2 +
                "} " + super.toString();
    }

    @Override
    public int getStock() {
        return stock2;
    }

    @Override
    public void addStock(int stock) {
        this.stock2+=stock;
    }

    @Override
    public int getDailyLimit() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean decreaseStock(int amount) {
        if(stock2 >= amount ){
            stock2-=amount;
            return true;
        }
        return false;
    }


    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(this);
        return products; //Collections.unmodifiableList(
    }


}

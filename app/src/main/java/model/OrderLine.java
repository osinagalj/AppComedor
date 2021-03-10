package model;

import java.io.Serializable;

public class OrderLine implements Serializable {
    private Product product;
    private int amount;
    private boolean toHome;
    private float price; //Historical price

    public OrderLine(){}

    public OrderLine(Product product, int amount, boolean toHome, float price){
        this.product = product;
        this.amount = amount;
        this.toHome = toHome;
        this.price = price;
    }

    public void increaseAmount(int amount){
        this.amount = this.amount + amount;
    }
    //todo change amount of a line method
    //--------------------------------------------------------------------------------------------//
    //------------------------------------ Getters && Setters ------------------------------------//
    //--------------------------------------------------------------------------------------------//
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isToHome() {
        return toHome;
    }

    public void setToHome(boolean toHome) {
        this.toHome = toHome;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}

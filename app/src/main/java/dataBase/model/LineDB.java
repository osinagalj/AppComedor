package dataBase.model;

import dataBase.Restaurant;
import model.OrderLine;

public class LineDB  {
    private int product_id;
    private int amount;
    private boolean toHome;
    private float price; //Historical price

    public LineDB(int product, int amount, boolean toHome, float price){
        this.product_id = product;
        this.amount = amount;
        this.toHome = toHome;
        this.price = price;
    }
/*
    public LineDB(OrderLine line){
        this.product_id = line.getProduct().getId();
        this.amount = line.getAmount();
        this.price = line.getPrice();
        this.toHome = line.isToHome();
    }
*/
    public OrderLine getOrderLine(){
        return new OrderLine(
                Restaurant.getInstance().getProduct(this.product_id),
                this.amount,
                this.toHome,
                this.price
        );
    }

    //--------------------------------------------------------------------------------------------//
    //------------------------------------ Getters && Setters ------------------------------------//
    //--------------------------------------------------------------------------------------------//
    public int getProductId() {
        return product_id;
    }

    public void setProduct(int product_id) {
        this.product_id = product_id;
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

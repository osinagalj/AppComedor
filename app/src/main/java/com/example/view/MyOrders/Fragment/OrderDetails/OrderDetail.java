package com.example.view.MyOrders.Fragment.OrderDetails;

public class OrderDetail {

    private String name;
    private String price;
    private String amount;

    public OrderDetail(){}

    public OrderDetail(String amount,String name,String price){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
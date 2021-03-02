package model;

import com.example.view.BackEnd;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;

public class Order implements Serializable {

    private int id;
    private Instant placed;
    private CommonUser placedBy;
    private Map<Product,Integer> items;

    /*
    public Order(CommonUser placedBy, Map<Product,Integer> items){
        this.id = Restaurant.getInstance().nextOrderNum(); //TODO change
        this.items= items;
        this.placed = Instant.now();
        this.placedBy = placedBy;
    }
*/
    public Order(CommonUser placedBy){
        this.id = Restaurant.getInstance().nextOrderNum();
        this.items= new HashMap<>();
        this.placed = Instant.now();
        this.placedBy = placedBy;
    }

    public Order(int id,CommonUser placedBy, Map<Product,Integer> items){
        this.id = id;
        this.items= items;
        this.placed = Instant.now();
        this.placedBy = placedBy;
    }

    public int getAmount(Product product){
        if (items.containsKey(product)){
            return items.get(product);
        }
        return -1;
    }

    public int getAmountToHome(Product product){
        return 2;
    } //todo?

    public boolean addProduct(Product p, int amount){
        if (p != null) {
            items.put(p, amount);
            return true;
        }
        else {
            return false;
        }
    }

    public void addProduct(Product p, int amount, boolean home){
        addProduct(p,amount);
    }


    public String getDescription(){
        StringBuilder description = new StringBuilder();
        for (Product product : items.keySet()){
            description.append(product.getName());
            description.append(" ");
            description.append(product.getDescription());
            description.append("\n");
        }
        return description.toString();
    }

    public float getPrice() {
        float totalPrice = 0;
        for (Product product : items.keySet()){
            totalPrice+=product.getPrice(BackEnd.getLoggedUser())*items.get(product);
        }
        return totalPrice;
    }

    public int getId() {
        return id;
    }

    public Instant getPlacedInstant() {
        return placed;
    }

    public CommonUser getPlacedBy() {
        return placedBy;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(new ArrayList<>(items.keySet()));
    }

    public void changeAmount(Product product, int amount, boolean Home){
        int old = items.get(product);
        items.remove(product);
        items.put(product, old + amount);

    }

    public void removeProduct(Product product){
        if (items.containsKey(product))
            items.remove(product);

    }


    public void setId(int id) {
        this.id = id;
    }

    public Instant getPlaced() {
        return placed;
    }

    public void setPlaced(Instant placed) {
        this.placed = placed;
    }

    public void setPlacedBy(CommonUser placedBy) {
        this.placedBy = placedBy;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

}

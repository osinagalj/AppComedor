package model;

import com.example.view.BackEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {

    private int id;
    private Date placed;
    private CommonUser placedBy;
    private Map<Product,Integer> items;

    public Order(int id,CommonUser placedBy, Map<Product,Integer> items){
        this.id = id;
        this.items= items;
        this.placed = new Date();
        this.placedBy = placedBy;
    }

    public Order(int id,CommonUser placedBy, Map<Product,Integer> items, Date date){
        this.id = id;
        this.items= items;
        this.placed = date;
        this.placedBy = placedBy;
    }

    public int getAmount(Product product){
        if (items.containsKey(product)){
            return items.get(product);
        }
        return -1;
    }


    public boolean addProduct(Product p, int amount){
        if (p != null) {
            items.put(p, amount);
            return true;
        }
        else {
            return false;
        }
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

    public Date getPlaced() {
        return placed;
    }

    public void setPlaced(Date placed) {
        this.placed = placed;
    }

    public void setPlacedBy(CommonUser placedBy) {
        this.placedBy = placedBy;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

}

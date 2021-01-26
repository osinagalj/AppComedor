package Model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {
    private static int ordersPlaced = 0;

    private int id;
    private Instant placed;
    private CommonUser placedBy;

    private final Map<Product,Integer> items;
    private final Map<Product,Integer> toHome;

    public Order(CommonUser placedBy, Map<Product,Integer> items, Map<Product,Integer> toHome){
        this.id = 5;//Restaurant.getInstance().nextOrderNum();
        this.items= items;
        this.toHome = toHome;
        this.placed = Instant.now();
        this.placedBy = placedBy;
    }

    public Order(CommonUser placedBy){
        this.id = 5;//Restaurant.getInstance().nextOrderNum();
        this.items= new HashMap<>();
        this.toHome = new HashMap<>();
        this.placed = Instant.now();
        this.placedBy = placedBy;
    }

    public String getAmount(int produtID){ //TODO lo necesito para mostrar en el ticket
        return "x3";
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

    public boolean addProductToHome(Product p, int amount){
        if (p != null) {
            toHome.put(p, amount);
            return true;
        }
        else {
            return false;
        }
    }

    public String getDescription(){
        StringBuilder description = new StringBuilder();
        for (Product product : items.keySet()){
            description.append(product.getDescription());
            description.append("\n");
        }
        for (Product product : toHome.keySet()){
            description.append(product.getDescription());
            description.append("\n");
        }
        return description.toString();
    }

    public float getPrice() {
        float totalPrice = 0;
        for (Product product : items.keySet()){
            totalPrice+=product.getPrice()*items.get(product);
        }
        for (Product product : toHome.keySet()){
            totalPrice+=product.getPrice()*toHome.get(product);
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

    public List<Product> getToHome() {
        return Collections.unmodifiableList(new ArrayList<>(toHome.keySet()));
    }

}

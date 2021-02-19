package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combo extends Product implements Serializable {
    private List<Product> comboItems;
    private int discount;

    public Combo(int id,String name, String description, int imgId, ProductCategory category, List<Product> comboItems, int discount) {
        super(id, name, description, imgId, category);
        if (comboItems != null)
            this.comboItems = comboItems;
        else
            this.comboItems = new ArrayList<>();
        this.discount = discount;
    }

    public Combo(int id,String name, String description, int imgId, ProductCategory category, int discount) {
        super(id, name, description, imgId, category);
        this.discount = discount;
        this.comboItems = new ArrayList<>();
    }

    public void addItem(Product comboItem){
        comboItems.add(comboItem);
    }

    public void removeItem(int barcode){
        comboItems.removeIf(product -> product.getId() == barcode);
    }

    public List<Product> getComboItems() {
        return comboItems;
    }

    public void setComboItems(List<Product> comboItems) {
        this.comboItems = comboItems;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "comboItems=" + comboItems +
                ", discount=" + discount +
                "} " + super.toString();
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients= new ArrayList<>();
        for (Product item : comboItems)
            ingredients.addAll(item.getIngredients());

        return Collections.unmodifiableList(ingredients);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Product item : comboItems)
            products.addAll(item.getProducts());
        return Collections.unmodifiableList(products);
    }

    @Override
    public float getPrice() {
        float price = 0;
        for (Product item : comboItems)
            price+=item.getPrice();
        return price;
    }

    @Override
    public int getStock() {
        int stock = Integer.MAX_VALUE;
        for (Product item : comboItems)
            stock = Integer.min(stock,item.getStock());
        return stock;
    }

    @Override
    public void addStock(int stock) {
        for (Product item : comboItems){
            item.addStock(stock);
        }
    }

    @Override
    public int getDailyLimit() {
        int limit = Integer.MAX_VALUE;
        for (Product item : comboItems)
            limit = Integer.min(limit,item.getDailyLimit());
        return limit;
    }

    @Override
    public boolean decreaseStock(int amount) { //TODO
        for (Product product : comboItems){
            decreaseStock(amount);
        }
        return true; //Hacer la logica para retornar true o false si hay o no stock
    }
}

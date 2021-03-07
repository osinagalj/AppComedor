package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Combo extends Product implements Serializable {
    private List<Product> comboItems;
    private DiscountCalculator discount;

    public Combo(int id,String name, String description, int imgId, int productCategory, List<Product> comboItems, DiscountCalculator calculator) {
        super(id, name, description, imgId, productCategory);
        if (comboItems != null)
            this.comboItems = comboItems;
        else
            this.comboItems = new ArrayList<>();
        this.discount = calculator;
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

    @Override
    public boolean toHome() {
        return false;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "comboItems=" + comboItems +
                ", discount=" + discount +
                "} " + super.toString();
    }

    @Override
    public float getPrice(CommonUser user) {

        return discount.getPrice(comboItems);
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
            product.decreaseStock(amount);
        }
        return true; //Hacer la logica para retornar true o false si hay o no stock
    }


    /*
    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (Product item : comboItems)
            products.addAll(item.getProducts());
        return products;//Collections.unmodifiableList(
    }*/

    public DiscountCalculator getDiscount() {
        return discount;
    }

    public void setDiscount(FixedDiscount discount) {
        this.discount = discount;
    }

}

package model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combo extends Product implements Serializable {
    private List<Product> comboItems;
    private DiscountCalculator discount;

    public Combo(){}
    public Combo(int id,String name, String description, int imgId, int productCategory, List<Product> comboItems, DiscountCalculator calculator) {
        super(id, name, description, imgId, productCategory);
        if (comboItems != null){
            this.comboItems = comboItems;
            for(Product p: comboItems)
                super.addCondition(p.getConditions());
        }
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
        return Collections.unmodifiableList(comboItems);
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

    /**
     * The stock of a combo is the minimum stock of the products that compose it.
     */
    @Override
    public int getStock() {
        int stock = Integer.MAX_VALUE;
        for (Product item : comboItems)
            stock = Integer.min(stock,item.getStock());
        return stock;
    }

    /**
     * To add stock to a combo, stock is added to all the products that compose it.
     */
    @Override
    public void addStock(int stock) {
        for (Product item : comboItems){
            item.addStock(stock);
        }
    }

    /**
     * The daily limit of a combo is the minimum daily limit of the products that compose it.
     */
    @Override
    public int getDailyLimit() {
        int limit = Integer.MAX_VALUE;
        for (Product item : comboItems)
            limit = Integer.min(limit,item.getDailyLimit());
        return limit;
    }

    /**
     * To decrease stock to a combo, stock is decreased to all the products that compose it.
     * @param amount stock to be decreased
     * @return true if all the products that compose it have been able to decrease the stock, otherwise false
     */
    @Override
    public boolean decreaseStock(int amount) {
        for (Product product : comboItems){
            if (!product.decreaseStock(amount))
                return false;
        }
        return true;
    }

    /**
     * A combo return all the products that compose it.
     */
    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Product item : comboItems)
            products.addAll(item.getProducts());
        return Collections.unmodifiableList(products);
    }

    @NonNull
    @Override
    public Object clone() {
        List<Product> clonedItems = new ArrayList<>();
        for (Product comboItem : comboItems){
            clonedItems.add((Product) comboItem.clone());
        }
        return new Combo(getId(),getName(),getDescription(),getImgId(),getProductCategory(),clonedItems,getDiscount());
    }

    public DiscountCalculator getDiscount() {
        return (DiscountCalculator) discount.clone();
    }

    public void setDiscount(FixedDiscount discount) {
        this.discount = discount;
    }

}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DailyMenu extends Product  {

    private List<Food> menus = new ArrayList<>();
    private int max = 3; //TODO get the total of conditions


    public DailyMenu(int id, String name, String description, int imgId, ProductCategory category, int stock, float price) {
        super(id, name, description, imgId, category);

    }


    @Override
    public List<String> getIngredients() {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Product item : menus)
            products.addAll(item.getProducts());
        return Collections.unmodifiableList(products);
    }

    @Override
    public float getPrice() {
        float price = 0;
        for (Product item : menus)
            price+=item.getPrice();
        return price;
    }

    @Override
    public int getStock() {
        int stock = Integer.MAX_VALUE;
        for (Product item : menus)
            stock = Integer.min(stock,item.getStock());
        return stock;
    }

    @Override
    public void addStock(int stock) {
        for (Product item : menus){
            item.addStock(stock);
        }
    }

    @Override
    public int getDailyLimit() {
        int limit = Integer.MAX_VALUE;
        for (Product item : menus)
            limit = Integer.min(limit,item.getDailyLimit());
        return limit;
    }

    @Override
    public boolean decreaseStock(int amount) { //TODO
        for (Product product : menus){
            decreaseStock(amount);
        }
        return true; //Hacer la logica para retornar true o false si hay o no stock
    }
}

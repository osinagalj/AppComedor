package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DailyMenu extends Food  {

    private int limit;
    private boolean home = false;

    public DailyMenu(int id,String name, String description, int imgId, int productCategory, int stock, float price, int limit) {
        super(id,name, description, imgId, productCategory,stock,price);
        this.limit = limit;

    }

    public void setHome(){
        this.home = true;
    }

    @Override
    public boolean toHome() {
        return true;
    }

    @Override
    public String toString() {
        return "Menu del Dia {" +
                "stock=" + super.getStock() +
                ", price=" + super.getPrice(null) +
                "} " + super.toString();
    }
    //todo tener el tohone en bool y agregarlo al to strng

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(this);
        return Collections.unmodifiableList(products);
    }

    @Override
    public float getPrice(CommonUser user ) {
        if(user.getCategory().equals(Category.ALUMNO.toString()))
            return super.getPrice(user) * 0.6f;

        return super.getPrice(user);
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    @Override
    public void addStock(int stock) {
        super.addStock(stock);;
    }

    @Override
    public int getDailyLimit() {
        return limit;
    }

    @Override
    public boolean decreaseStock(int amount) {
        return super.decreaseStock(amount);
    }
}

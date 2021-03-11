package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private int imgId;
    private int productCategory;
    private List<Integer> conditions = new ArrayList<>(); //Condiciones que no pueden consumir este alimento

    protected Product(){}

    protected Product(int id, String name, String description, int imgId, int productCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
        this.productCategory = productCategory;
    }

    /**
     * @return true if the product has the possibility to take home, otherwise false
     */
    public abstract boolean toHome();

    /**
     * @return the stock of a product
     */
    public abstract int getStock();

    /**
     * @return the maximum amount that can be purchased per day
     */
    public abstract int getDailyLimit();

    /**
     * increases the stock of a product
     * @param stock amount to be increased
     */
    public abstract void addStock(int stock);

    /**
     * decreases the stock of a product
     * @param amount stock to be decreased
     */
    public abstract boolean decreaseStock(int amount);

    /**
     * @param user for whom the price is to be calculated
     * @return the price for the user
     */
    public abstract float getPrice(CommonUser user);

    /**
     * @return A list of the products it contains
     */
    public abstract List<Product> getProducts();

    public void addCondition(int condition){
        conditions.add(condition);
    }
    public void addCondition(List<Integer> condition){
        conditions.addAll(condition);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + productCategory +
                '}';
    }

    //--------------------------------------------------------------------------------------------//
    //------------------------        Getters && Setters         ---------------------------------//
    //--------------------------------------------------------------------------------------------//
    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public List<Integer> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    public void setConditions(List<Integer> conditions) {
        this.conditions = conditions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getCategory() {
        return productCategory;
    }

    public void setCategory(int category) {
        this.productCategory = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

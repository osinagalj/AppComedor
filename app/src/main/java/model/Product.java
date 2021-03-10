package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private int imgId;
    private int productCategory;
    private ArrayList<Integer> conditions = new ArrayList<>(); //Condiciones que no pueden consumir este alimento

    protected Product(){}

    protected Product(int id, String name, String description, int imgId, int productCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
        this.productCategory = productCategory;
    }

    public void addCondition(int condition){
        conditions.add(condition);
    }
    public void addCondition(List<Integer> condition){
        conditions.addAll(condition);
    }

    public abstract boolean toHome(); //todo Capaz que hay que hacerlo como atributo
    public abstract int getStock();
    public abstract int getDailyLimit();
    public abstract void addStock(int stock);
    public abstract boolean decreaseStock(int amount);

    public abstract float getPrice(CommonUser user);
    public abstract ArrayList<Product> getProducts();

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
        return conditions;
    }

    public void setConditions(ArrayList<Integer> conditions) {
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

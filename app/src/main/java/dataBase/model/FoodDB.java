package dataBase.model;

import com.example.view.BackEnd;

import java.util.ArrayList;
import java.util.List;

import model.Food;

public class FoodDB {
    private int id;
    private String name;
    private String description;
    private int imgId;
    private int productCategory;
    private List<Integer> conditions = new ArrayList<>();
    private int stock;
    private float price;

    public FoodDB(){}
    public FoodDB(int id,String name, String description, int imgId, int productCategory, List<Integer> conditions, int stock, float price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
        this.productCategory = productCategory;
        this.conditions = conditions;
        this.stock = stock;
        this.price = price;
    }

    public FoodDB(Food food){
        this.conditions = food.getConditions();
        this.description = food.getDescription();
        this.id = food.getId();
        this.name = food.getName();
        this.imgId = food.getImgId();
        this.productCategory = food.getProductCategory();
        this.stock = food.getStock();
        this.price = food.getPrice(BackEnd.getLoggedUser());
    }

    public Food convertToModel(){
        Food f =  new Food(id,name,description,imgId,productCategory,stock,price);
        f.addCondition(this.conditions);
        return f;
    }


    public List<Integer> getConditions() {
        return conditions;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConditions(ArrayList<Integer> conditions) {
        this.conditions = conditions;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}

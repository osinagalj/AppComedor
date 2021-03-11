package dataBase.model;

import com.example.view.BackEnd;

import java.util.List;

import model.Food;

public class FoodDB extends ProductDB{


    private int stock;
    private float price;

    public FoodDB(){}
    public FoodDB(int id,String name, String description, int imgId, int productCategory, List<Integer> conditions, int stock, float price){
        super(id,name,description,imgId,productCategory,conditions);

        this.stock = stock;
        this.price = price;
    }

    public FoodDB(Food food){
        super(food.getId(),food.getName(),food.getDescription(),food.getImgId(),food.getProductCategory(),food.getConditions());
        this.stock = food.getStock();
        this.price = food.getPrice(BackEnd.getLoggedUser());
    }

    public Food convertToModel(){
        Food f =  new Food(super.getId(),super.getName(),super.getDescription(),super.getImgId(),super.getProductCategory(),stock,price);
        f.addCondition(super.getConditions());
        return f;
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

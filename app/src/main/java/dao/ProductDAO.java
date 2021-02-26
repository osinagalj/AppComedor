package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;
import model.CommonUser;
import model.Food;
import model.Product;

public class ProductDAO {

    //@GET
    public static List<Product> getProducts(CommonUser user){
        return Restaurant.getInstance().getAvailableProducts(user);
    }

    //@GET
    public static Product getSpecialProduct(CommonUser user){
        return Restaurant.getInstance().getSpecialMenu(user);
    }

    //@UPDATE
    public static boolean decreaseStock(int productId,int amount){
        return Restaurant.getInstance().decreaseStock(productId,amount);
    }

    //@UPDATE
    public static void increaseStock(int productId,int amount){
         Restaurant.getInstance().increaseStock(productId,amount);
    }


    //@GET
    public static Product getProductById(int id){
        return Restaurant.getInstance().getProduct(id);
    }

    //@POST
    public static void loadProduct(Food food){


        Map<String, Object> new_food = new HashMap<>();
        new_food.put("id", food.getId());
        new_food.put("name", food.getName());
        new_food.put("description", food.getDescription());
        new_food.put("imgId", food.getImgId());
        new_food.put("productCategory", food.getCategory());
        new_food.put("stock", food.getStock());
        new_food.put("price", food.getPrice(null));

        List<Integer> conditions = new ArrayList<>(food.getCondition());
        new_food.put("conditions", conditions);

        // Add a new document with a generated ID
        Restaurant.getInstance().db.collection("foods").document(String.valueOf(1100)).set(new_food);
    }



    //DELETE
    public static void removeProduct(){ }

}

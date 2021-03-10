package dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import dataBase.Restaurant;
import dataBase.model.ComboDB;
import dataBase.model.DailyMenuDB;
import dataBase.model.FoodDB;
import model.Combo;
import model.DailyMenu;
import model.Food;

public class ProductDAO {

    public static void loadProduct(Food food){
        Restaurant.getInstance().db.collection("foods")
                .document(String.valueOf(food.getId()))
                .set(new FoodDB(food));
    }

    public static void loadProduct(DailyMenu food){
        Restaurant.getInstance().db.collection("dailyMenus")
                .document(String.valueOf(food.getId()))
                .set(new DailyMenuDB(food));
    }

    public static void loadProduct(Combo food){
        ComboDB new_food = new ComboDB(food);
        Restaurant.getInstance().db.collection("combos").document(String.valueOf(food.getId())).set(new_food);
    }

    /*
    public static void loadProduct(Combo food){
        Map<String, Object> new_food = new HashMap<>();
        new_food.put("id", food.getId());
        new_food.put("name", food.getName());
        new_food.put("description", food.getDescription());
        new_food.put("imgId", food.getImgId());
        new_food.put("productCategory", food.getCategory());

        new_food.put("discount", "0.3");//todo

        ArrayList<Integer> conditions = new ArrayList<>(food.getConditions());

        new_food.put("conditions", conditions);


        List<Integer> foods = new ArrayList<>();
        for(Product p : food.getComboItems()){
            System.out.println("ID del food en combo = " + p.getId());
            foods.add(p.getId());
        }

        new_food.put("items", foods);

        // Add a new document with a generated ID
        Restaurant.getInstance().db.collection("combos").document(String.valueOf(food.getId())).set(new_food);
    }

*/

    //@DELETE
    public static void removeProduct(int id){

        Restaurant.getInstance().db.collection("foods").document(String.valueOf(id))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error deleting document", e);
                    }
                });
    }


    /*
    //@UPDATE
    public static boolean decreaseStock(int productId,int amount){
        return Restaurant.getInstance().decreaseStock(productId,amount);
    }

    //@UPDATE
    public static void increaseStock(int productId,int amount){
         Restaurant.getInstance().increaseStock(productId,amount);
    }
    */


}

package dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

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



    //@UPDATE
    public static MutableLiveData<Boolean> increaseStock(String productId, int amount){
        MutableLiveData<Boolean> state = new MutableLiveData<>();

        DocumentReference gg = Restaurant.getInstance().db.collection("foods").document(productId);

        Restaurant.getInstance().db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(gg);

                int stock = Integer.parseInt(snapshot.get("stock").toString()) + amount;
                transaction.update(gg, "stock", stock);

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                state.postValue(true);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        state.postValue(false);
                    }
                });

        return state;
    }

    //@UPDATE
    public static MutableLiveData<Boolean> decreaseStock(String productId, int amount){
        MutableLiveData<Boolean> state = new MutableLiveData<>();

        DocumentReference gg = Restaurant.getInstance().db.collection("foods").document(productId);

        Restaurant.getInstance().db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(gg);

                int stock = Integer.parseInt(snapshot.get("stock").toString());
                if(stock <= amount){
                    stock = stock - amount;
                    transaction.update(gg, "stock", stock);
                }else{
                    state.postValue(false);
                }

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                state.postValue(true);
            }
        });


        return state;
    }




}

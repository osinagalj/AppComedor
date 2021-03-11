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

import java.util.HashMap;

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
    public static MutableLiveData<Boolean> increaseStock(String productId, int amount, String collection){
        MutableLiveData<Boolean> state = new MutableLiveData<>();

        DocumentReference gg = Restaurant.getInstance().db.collection(collection).document(productId);

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

    public static HashMap<Integer,Integer> productos_decrementados = new HashMap<>();

    //@UPDATE
    public static MutableLiveData<Boolean> decreaseStock(String productId, int amount, String collection){
        MutableLiveData<Boolean> state = new MutableLiveData<>();

        DocumentReference gg = Restaurant.getInstance().db.collection(collection).document(productId);

        Restaurant.getInstance().db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                if(transaction != null){
                    DocumentSnapshot snapshot = transaction.get(gg);

                    int stock = Integer.parseInt(snapshot.get("stock").toString());
                    if(stock >= amount){
                        stock = stock - amount;
                        transaction.update(gg, "stock", stock);
                        System.out.println("Decremento el producto");
                        productos_decrementados.put(Integer.valueOf(productId),amount);
                        state.postValue(true);
                    }else{
                        System.out.println("No Decremento el producto");
                        state.postValue(false);
                    }
                }

                // Success
                return null;
            }
        });

        return state;
    }

}

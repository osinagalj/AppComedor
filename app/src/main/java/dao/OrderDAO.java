package dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.view.BackEnd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;
import model.CommonUser;
import model.Order;
import model.Product;
import utils.Utils;

public class OrderDAO {

    private static final String TAG = "ok";

    /** Insert an order in the DB
     * @param pending used to distinguish between pending and completed orders
     * @param order is converted into a data type supported by the DB
     * */
    //@POST
    public static void loadOrder(Order order, boolean pending){

        Map<String, Object> new_order = new HashMap<>();
        new_order.put("id", order.getId());
        new_order.put("date", order.getPlaced());
        new_order.put("user_id", order.getPlacedBy().getIdentityCardNumber());
        new_order.put("pending", pending);

        Map<String, Integer> products = new HashMap<>(); //<id,amount>
        for(Product p : order.getItems())
            products.put(String.valueOf(p.getId()),order.getAmount(p));
        new_order.put("products", products);

        //Write in DB
        Restaurant.getInstance().db.collection("orders").document(String.valueOf(order.getId())).set(new_order);
    }

    /** Remove an order in the DB
     * @param id order key
     * */
    //@DELETE
    public static void removeOrder(String id){

        Restaurant.getInstance().db.collection("orders").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

    }

    /** Brings the orders from the database whose id == id_logged_user
     * and the orders whose status is equal to the
     * @param pending
     */
    //@GET
    public static MutableLiveData<List<Order>> getOrders(boolean pending){

        MutableLiveData<List<Order>> list_orders = new MutableLiveData<List<Order>>();
        List<Order> list_of_orders = new ArrayList<>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("orders");
        colRef.whereEqualTo("user_id", BackEnd.getLoggedUser().getIdentityCardNumber()).whereEqualTo("pending", pending)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list_of_orders.add(Utils.dbToOrder(document));
                            }
                            list_orders.postValue(list_of_orders);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list_orders;
    }

    /** @GET  the last orders from the database according to the limit*/
    public static MutableLiveData<List<Order>> getOrders(int limit){

        MutableLiveData<List<Order>> list_orders = new MutableLiveData<List<Order>>();
        List<Order> list_of_orders = new ArrayList<Order>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("orders");
        colRef.limit(limit).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list_of_orders.add(Utils.dbToOrder(document));
                            }
                            list_orders.postValue(list_of_orders);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list_orders;
    }

    /** @GET the id for the next command */
    public static MutableLiveData<Integer> getNumberNextOrder(){

        MutableLiveData<Integer> el_number = new MutableLiveData<>();
        CollectionReference docRef = Restaurant.getInstance().db.collection("orders");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int num = -1;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(Integer.parseInt(document.getData().get("id").toString()) >  num){
                            num = Integer.parseInt(document.getData().get("id").toString());
                        }
                    }
                    if(num == -1)
                        el_number.postValue(0); //primer numero de orden
                    else
                        el_number.postValue(num + 1);
                }
            }
        });
        return el_number;
    }

    //@GET
    public static String timeNextOrder(CommonUser user){
        //TODO devuelve el tiempo que debe esperar el usuario para su la proxima orden
        return "05:02 min";
    }

}

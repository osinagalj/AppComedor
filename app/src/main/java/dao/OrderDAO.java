package dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;
import model.CommonUser;
import model.Order;
import model.Product;

public class OrderDAO {

    private static final String TAG = "ok";

    //@POST
    public static void loadPendingOrder(Order order){

            Map<String, Object> new_order = new HashMap<>();
            new_order.put("id", order.getId());
            new_order.put("date", LocalDate.now().toString());
            new_order.put("user_id", order.getPlacedBy().getIdentityCardNumber());

            Map<String, Integer> products = new HashMap<>(); //<id,amount>
            for(Product p : order.getItems())
                products.put(String.valueOf(p.getId()),order.getAmount(p));
            new_order.put("products", products);

            // Add a new document with a generated ID

            Restaurant.getInstance().db.collection("pending_orders").document(String.valueOf(order.getId())).set(new_order);

        //Restaurant.getInstance().addOrder(order);
    }

    //@POST
    public static void loadConfirmedOrder(Order order){

        Map<String, Object> new_order = new HashMap<>();
        new_order.put("id", order.getId());
        new_order.put("date", LocalDate.now().toString());
        new_order.put("user_id", order.getPlacedBy().getIdentityCardNumber());

        Map<String, Integer> products = new HashMap<>(); //<id,amount>
        for(Product p : order.getItems())
            products.put(String.valueOf(p.getId()),order.getAmount(p));
        new_order.put("products", products);

        // Add a new document with a generated ID

        Restaurant.getInstance().db.collection("confirmed_orders").document(String.valueOf(order.getId())).set(new_order);

        //Restaurant.getInstance().addOrder(order);
    }

    //@GET
    public static List<Order> getCompletedOrders(CommonUser user) {
        return Restaurant.getInstance().getOrdersCompleted(user);
    }

    //@GET
    public static List<Order> getPendingOrders(CommonUser user) {
        return Restaurant.getInstance().getPendingOrders(user);
    }

    //@GET
    public static List<String> nextOrders() {
        return Restaurant.getInstance().getNextOrders();
    }

    //@GET
    public static String timeNextOrder(CommonUser user){
        //TODO devuelve el tiempo que debe esperar el usuario para su la proxima orden
        return "05:02 min";
    }

    //@DELETE
    public static void removeOrder(String collection, String id){

        Restaurant.getInstance().db.collection(collection).document(id)
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

    //@GET
    public static int getNumberNextOrder(){

        int[] number = {0};
        CollectionReference docRef = Restaurant.getInstance().db.collection("pending_orders");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println("Proximo numero de orden xd = " + Integer.parseInt(document.getData().get("id").toString()));
                        if(Integer.parseInt(document.getData().get("id").toString()) >  number[0])
                            number[0] = Integer.parseInt(document.getData().get("id").toString());
                    }
                }
            }
        });

        return number[0];
    }



}

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import dataBase.Restaurant;
import dataBase.model.OrderDB;
import model.CommonUser;
import model.Order;
import model.OrderLine;
import model.OrderState;

public class OrderDAO {

    private static final String TAG = "ok";

    /** Insert an order in the DB
     * @param pending used to distinguish between pending and completed orders
     * @param order is converted into a data type supported by the DB
     * */
    //@POST
    public static void loadOrder(Order order, boolean pending){
        OrderDB new_order = new OrderDB(order);

        //Write in DB
        Restaurant.getInstance().db.collection("orders")
                .document(String.valueOf(order.getId()))
                .set(new_order);

    }



    /** Brings the orders from the database whose id == id_logged_user
     * and the orders whose status is equal to the
     * @param state
     */
    //@GET
    public static MutableLiveData<List<Order>> getOrders(OrderState state){

        MutableLiveData<List<Order>> list_orders = new MutableLiveData<List<Order>>();
        List<Order> list_of_orders = new ArrayList<>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("orders");

        colRef.whereEqualTo("user_id", BackEnd.getLoggedUser().getIdentityCardNumber())
                .whereEqualTo("state", state.toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                OrderDB order = document.toObject(OrderDB.class);
                                list_of_orders.add(order.convertToModel());
                            }
                            list_orders.postValue(list_of_orders);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list_orders;
    }


    public static MutableLiveData<Order> getOrderById(String id){

        MutableLiveData<Order> m_order = new MutableLiveData<>();
        DocumentReference docRef = Restaurant.getInstance().db
                .collection("orders")
                .document(id);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot document) {
                if(document != null){

                    OrderDB or= document.toObject(OrderDB.class);
                    if(or!= null){
                        Order o = or.convertToModel();
                        System.out.println("id =" + o.getId());
                        System.out.println("id =" + o.getPlacedBy().getIdentityCardNumber());
                        System.out.println("id =" + o.getPlaced());
                        System.out.println("lines =" + o.getLines());
                        for(OrderLine line: o.getLines()) {
                            System.out.println("nombre linea = " + line.getProduct().getName());
                            System.out.println("id linea = " + line.getProduct().getId());
                            System.out.println(" stock linea = " + line.getProduct().getStock());
                        }
                        m_order.postValue(o);
                    }

                }
            }
        });

        return m_order;
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
                                OrderDB order = document.toObject(OrderDB.class);
                                Order o = order.convertToModel();
                                System.out.println("id =" + o.getId());
                                System.out.println("id =" + o.getPlacedBy());
                                System.out.println("id =" + o.getPlaced());
                                System.out.println("lines =" + o.getLines());
                                list_of_orders.add(o);
                            }
                            list_orders.postValue(list_of_orders);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list_orders;
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

    public static boolean tryDecreaseStock(Order cart) {
        return true;
    }
}

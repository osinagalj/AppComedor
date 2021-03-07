package dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.view.BackEnd;
import com.example.view.food.nestedRecyclerFood.FoodViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;
import model.CommonUser;
import model.Order;
import model.Product;

public class OrderDAO {

    private static final String TAG = "ok";

    public static MutableLiveData<Integer> next_number = new MutableLiveData<>();



    //@POST
    public static void loadPendingOrder2(Order order){



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

    public static LiveData<List<Order>> getOrders(){

        MutableLiveData<List<Order>> list_orders = new MutableLiveData<List<Order>>();
        ArrayList<Order> list = new ArrayList<Order>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("pending_orders");
        colRef.whereEqualTo("user_id", BackEnd.getLoggedUser().getIdentityCardNumber())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                HashMap<String,Object> elements = (HashMap<String,Object>)document.getData().get("products");
                                Map<Product,Integer> items = new HashMap<>();


                                for (Map.Entry<String, Object> entry : elements.entrySet()) {
                                    System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
                                    for(Product p : FoodViewModel.list_of_foods){
                                        if(p.getId() == Integer.parseInt(entry.getKey()))
                                            items.put(p,Integer.parseInt(entry.getValue().toString()));
                                    }
                                }

                                elements.forEach((k,v) ->
                                        System.out.println("Key: " + k + ": Value: " + v));



                                int nro = Integer.parseInt(document.getData().get("id").toString());
                                Order o = new Order (nro,BackEnd.getLoggedUser(), items);

                                list.add(o);

                            }
                            list_orders.postValue(list);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return list_orders;
    }

    //@GET
    public static MutableLiveData<Integer> setNumberNextOrder2(){
        //Todo  hay que buscar en las ordenes completas tambien
        MutableLiveData<Integer> el_number = new MutableLiveData<>();
        CollectionReference docRef = Restaurant.getInstance().db.collection("pending_orders");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int num = -1;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println("Proximo numero de orden xd = " + Integer.parseInt(document.getData().get("id").toString()));
                        if(Integer.parseInt(document.getData().get("id").toString()) >  num){
                            num = Integer.parseInt(document.getData().get("id").toString());
                        }
                    }
                    if(num == -1)
                        el_number.postValue(0); //primer numero de orden
                    else
                        el_number.postValue(num);
                }
            }
        });
        return el_number;
    }

}

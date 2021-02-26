package com.example.view.myOrders.fragment.confirmados;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.view.BackEnd;
import com.example.view.food.nestedRecyclerFood.FoodViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;
import model.Order;
import model.Product;

public class ConfirmedOrdersViewModel extends ViewModel {


    private static final String TAG = "error: ";
    public MutableLiveData<List<Order>> list_orders = new MutableLiveData<List<Order>>();
    public static List<Order> list_of_orders = new ArrayList<Order>();


    public void setOrders(){

        list_orders = new MutableLiveData<List<Order>>();
        list_of_orders = new ArrayList<Order>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("confirmed_orders");
        colRef
                .whereEqualTo("user_id", BackEnd.getLoggedUser().getIdentityCardNumber())
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




                                Order o = new Order (BackEnd.getLoggedUser(), items);

                                list_of_orders.add(o);

                            }
                            list_orders.postValue(list_of_orders);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}

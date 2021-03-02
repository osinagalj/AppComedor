package com.example.view.food.nestedRecyclerFood;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import dataBase.Repository;
import dataBase.Restaurant;
import model.Combo;
import model.DailyMenu;
import model.Food;
import model.Product;

public class FoodViewModel extends ViewModel {

    private static final String TAG = "a";
    public MutableLiveData<List<Product>> list_foods = new MutableLiveData<List<Product>>();
    public static List<Product> list_of_foods = new ArrayList<Product>();


    Repository repository = new Repository();

    public LiveData<List<DailyMenu>> getDailyMenu() {
        return repository.menuListening();

    }

    public LiveData<List<Food>> getProductFoods() {
        LiveData<List<Food>> list = repository.productListening();
        setCombos();
        return list;
    }


    public LiveData<List<Product>> setCombos(){

        MutableLiveData<List<Product>> combos = new MutableLiveData<List<Product>>();
        List<Product> list_ = new ArrayList<Product>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("combos");
        colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                List<Product> comboItems = new ArrayList<>();

                                ArrayList<Long> arrList = new ArrayList<Long>();
                                arrList = (ArrayList) document.getData().get("items");

                                for(int i=0;i<arrList.size();i++){
                                    for(Product f : list_of_foods){
                                        System.out.println("Numero de food = " + Math.toIntExact((arrList.get(i))));
                                        if(f.getId() == Math.toIntExact((arrList.get(i)))){
                                            comboItems.add(f);
                                        }
                                    }
                                }

                                Combo c = new Combo (Integer.parseInt(document.getData().get("id").toString()),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("imgId").toString()),
                                        Integer.parseInt(document.getData().get("productCategory").toString()),
                                        comboItems,0.3f
                                );

                                list_.add(c);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            combos.postValue(list_);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return combos;
    }





}
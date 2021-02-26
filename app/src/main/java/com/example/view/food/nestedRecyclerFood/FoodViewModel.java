package com.example.view.food.nestedRecyclerFood;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.view.BackEnd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataBase.Restaurant;
import model.Combo;
import model.DailyMenu;
import model.Food;
import model.Menu;
import model.Product;

public class FoodViewModel extends ViewModel {

    private static final String TAG = "a";
    public MutableLiveData<List<Product>> list_foods = new MutableLiveData<List<Product>>();
    public static List<Product> list_of_foods = new ArrayList<Product>();

    public void setDailyMenuFoods(){

        list_foods = new MutableLiveData<List<Product>>();
        list_of_foods = new ArrayList<Product>();

        CollectionReference colRef = Restaurant.getInstance().db.collection("dailyMenus");
        colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            Menu menu = new Menu(LocalDate.now()); //todo get the local date

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                DailyMenu f = new DailyMenu (Integer.parseInt(document.getData().get("id").toString()),
                                                   document.getData().get("name").toString(),
                                                   document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("imgId").toString()),
                                        Integer.parseInt(document.getData().get("productCategory").toString()),
                                        Integer.parseInt(document.getData().get("stock").toString()),
                                        Float.parseFloat(document.getData().get("price").toString()),
                                        2
                                );

                                ArrayList<Long> arrList = new ArrayList<Long>();
                                arrList = (ArrayList) document.getData().get("conditions");

                                for(int i=0;i<arrList.size();i++){
                                   System.out.println("Numero de condicion = " + arrList.get(i));
                                    f.addCondition(Math.toIntExact((arrList.get(i))));
                                }

                                menu.add(f);
                                System.out.println("Data del dailyfood");
                                System.out.println("Id del dailymenu = " + f.getId());
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }

                            list_of_foods.add(menu.getMenu(BackEnd.getLoggedUser()));
                            list_foods.postValue(list_of_foods);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    public void setFoods(){

        CollectionReference colRef = Restaurant.getInstance().db.collection("foods");
        colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Food f = new Food (Integer.parseInt(document.getData().get("id").toString()),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("imgId").toString()),
                                        Integer.parseInt(document.getData().get("productCategory").toString()),
                                        Integer.parseInt(document.getData().get("stock").toString()),
                                        Float.parseFloat(document.getData().get("price").toString())
                                );
                                list_of_foods.add(f);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            list_foods.postValue(list_of_foods);
                            setCombos();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public void setCombos(){

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

                                list_of_foods.add(c);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            list_foods.postValue(list_of_foods);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
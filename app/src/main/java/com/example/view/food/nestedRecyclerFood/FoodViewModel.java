package com.example.view.food.nestedRecyclerFood;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import dataBase.Restaurant;
import model.Food;

public class FoodViewModel extends ViewModel {

    private static final String TAG = "a";
    public MutableLiveData<List<Food>> list_foods = new MutableLiveData<List<Food>>();
    List<Food> list_of_foods = new ArrayList<Food>();

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

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


}
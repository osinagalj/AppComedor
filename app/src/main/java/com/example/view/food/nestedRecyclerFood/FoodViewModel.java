package com.example.view.food.nestedRecyclerFood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dataBase.Repository;
import dataBase.model.ComboDB;
import dataBase.model.DailyMenuDB;
import dataBase.model.FoodDB;
import model.Product;

public class FoodViewModel extends ViewModel {

    private static final String TAG = "a";

    public static List<Product> list_of_foods = new ArrayList<Product>();


    Repository repository = new Repository();

    public LiveData<List<DailyMenuDB>> getDailyMenu() {
        return repository.menuListening();
    }

    public LiveData<List<FoodDB>> getProductFoods() {
        return repository.productListening();
    }

    public LiveData<List<ComboDB>> getProductCombos() {
        return repository.comboListening();

    }
    /*
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

                                ComboDB comboDB = document.toObject(ComboDB.class);
                                Combo model_combo = comboDB.convertToModel();
                                list_.add(model_combo);
                            }
                            combos.postValue(list_);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return combos;
    }

*/


}
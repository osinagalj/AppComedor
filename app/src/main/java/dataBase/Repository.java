package dataBase;

import androidx.lifecycle.LiveData;

import java.util.List;

import dataBase.model.ComboDB;
import dataBase.model.DailyMenuDB;
import dataBase.model.FoodDB;
import dataBase.model.UserDB;

public class Repository {

    public Repository() { }

    public LiveData<List<FoodDB>> productListening() {
      return new FirestoreLiveData<List<FoodDB>>(
              Restaurant.getInstance().db.collection("foods"),
              FoodDB.class);
    }

    public LiveData<List<DailyMenuDB>> menuListening() {
        return new FirestoreLiveData<List<DailyMenuDB>>(
                Restaurant.getInstance().db.collection("dailyMenus"),
                DailyMenuDB.class);
    }

    public LiveData<List<ComboDB>> comboListening() {
        return new FirestoreLiveData<List<ComboDB>>(
                Restaurant.getInstance().db.collection("combos"),
                ComboDB.class);
    }

    public LiveData<List<UserDB>> usersListening() {
        return new FirestoreLiveData<List<UserDB>>(
                Restaurant.getInstance().db.collection("users"),
                UserDB.class);
    }

}

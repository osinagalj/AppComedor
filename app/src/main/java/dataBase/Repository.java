package dataBase;

import androidx.lifecycle.LiveData;

import java.util.List;

import dataBase.model.ComboDB;
import dataBase.model.UserDB;
import model.DailyMenu;
import model.Food;

public class Repository {

    public Repository() { }

    public LiveData<List<Food>> productListening() {
      return new FirestoreLiveData<List<Food>>(
              Restaurant.getInstance().db.collection("foods"),
              Food.class);
    }

    public LiveData<List<DailyMenu>> menuListening() {
        return new FirestoreLiveData<List<DailyMenu>>(
                Restaurant.getInstance().db.collection("dailyMenus"),
                DailyMenu.class);
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

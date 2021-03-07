package dataBase;

import androidx.lifecycle.LiveData;

import java.util.List;

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

}

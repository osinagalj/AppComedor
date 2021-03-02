package dataBase;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.CommonUser;
import model.DailyMenu;
import model.Food;

public class Repository {

    public Repository() {
    }

    public LiveData<List<CommonUser>> usersListening() {
       // return new FirestoreLiveData<CommonUser>(DatabaseRouter.getCollectionRef(group.getGroupCreator()).document(group.getGroupKey()).collection("ProductList"), CommonUser.class);
        return new FirestoreLiveData<List<CommonUser>>(Restaurant.getInstance().db.collection("users2"), CommonUser.class);

    }


    public LiveData<List<Food>> productListening() {
        // return new FirestoreLiveData<CommonUser>(DatabaseRouter.getCollectionRef(group.getGroupCreator()).document(group.getGroupKey()).collection("ProductList"), CommonUser.class);
        return new FirestoreLiveData<List<Food>>(Restaurant.getInstance().db.collection("foods"), Food.class);

    }

    public LiveData<List<DailyMenu>> menuListening() {
        // return new FirestoreLiveData<CommonUser>(DatabaseRouter.getCollectionRef(group.getGroupCreator()).document(group.getGroupKey()).collection("ProductList"), CommonUser.class);
        return new FirestoreLiveData<List<DailyMenu>>(Restaurant.getInstance().db.collection("dailyMenus"), DailyMenu.class);

    }

}

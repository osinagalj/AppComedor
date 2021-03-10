package dao;

import androidx.lifecycle.MutableLiveData;

import com.example.view.BackEnd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import dataBase.Restaurant;
import dataBase.model.UserDB;
import model.CommonUser;

public class UserDAO {



    public static void addUser(CommonUser user){
        Restaurant.getInstance().db.collection("users")
                .document(String.valueOf(user.getIdentityCardNumber()))
                .set(new UserDB(user));
    }

    public static MutableLiveData<CommonUser> getUser(int dni){

        MutableLiveData<CommonUser> m_user = new MutableLiveData<>();
        DocumentReference docRef = Restaurant.getInstance().db.collection("users")
                .document(String.valueOf(dni));

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot document) {
                if(document != null){
                    UserDB user = document.toObject(UserDB.class);
                    CommonUser model_user = user.convertToModel();
                    m_user.postValue(model_user);
                }
            }
        });

        return m_user;
    }

    //@UPDATE
    public static boolean loadMoney(int icn, float amount){
        Restaurant.getInstance().db.collection("users").document(String.valueOf(icn))
                .update(
                        "balance", amount
                );
        BackEnd.getLoggedUser().setBalance(BackEnd.getLoggedUser().getBalance() + amount);
        return true;
    }

    //@UPDATE
    public static void changePassword(int icn, String password){
        Restaurant.getInstance().db.collection("users").document(String.valueOf(icn))
                .update(
                        "password", password
                );
    }

}

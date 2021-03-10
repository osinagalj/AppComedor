package dao;

import androidx.lifecycle.MutableLiveData;

import com.example.view.BackEnd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;

import dataBase.Restaurant;
import dataBase.model.UserDB;
import model.CommonUser;
import model.PriceAntiquity;
import model.PriceFixedDiscount;
import model.PriceSubjects;

public class UserDAO {



    public static void addUser(CommonUser user){
        Restaurant.getInstance().db.collection("users")
                .document(String.valueOf(user.getIdentityCardNumber()))
                .set(user);

        Restaurant.getInstance().db.collection("users5")
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

                    CommonUser user = document.toObject(CommonUser.class);
                    switch (user.getCategory()){
                        case ALUMNO:
                            user.setDiscountCalculator(new PriceFixedDiscount(0.6f));
                            break;
                        case DOCENTE:
                            user.setDiscountCalculator(new PriceSubjects((Integer.parseInt(user.getAttribute("subjects").toString()))));
                            break;
                        case NO_DOCENTE:
                            user.setDiscountCalculator(new PriceAntiquity(new Date()));//TODO
                            break;
                        default:
                            user.setDiscountCalculator(new PriceFixedDiscount(0f));
                    }
                    m_user.postValue(user);
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

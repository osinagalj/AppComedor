package com.example.view.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.time.LocalDate;

import dataBase.Restaurant;
import model.CommonUser;
import model.PriceStudent;
import utils.Utils;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<CommonUser>  live_user = new MutableLiveData<CommonUser>();
    private CommonUser user = new CommonUser();

    public void setUser(String dni, String input_password){

        DocumentReference docRef = Restaurant.getInstance().db.collection("users").document(String.valueOf(dni));

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot document) {
                if(document.exists()){

                    String password = (String)document.getData().get("password");
                    if(input_password.equals(password)){
                        user.setIdentityCardNumber(Integer.parseInt(document.getData().get("identityCardNumber").toString()));
                        user.setPassword(password);
                        user.setBalance(Float.parseFloat(document.getData().get("balance").toString()));
                        user.setNames((String)document.getData().get("names"));
                        user.setLastname((String)document.getData().get("lastName"));
                        user.setCategory(Utils.getCategory(document.getData().get("category").toString()));
                        user.setDiscountCalculator(new PriceStudent(0.6f));

                        //todo
                        String birthDate = (String)document.getData().get("birthDate");
                        int condition = (Integer.parseInt(document.getData().get("condition").toString()));
                        user.setBirthdate(LocalDate.now());
                        user.setCondition(0);

                        //Send the user
                        live_user.postValue(user);
                    }else{
                        user.setIdentityCardNumber(-1);
                        live_user.postValue(user);
                    }
                }
            }
        });
    }


}

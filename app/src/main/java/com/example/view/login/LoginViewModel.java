package com.example.view.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import dataBase.Restaurant;
import model.CommonUser;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<CommonUser> getUser(int dni){

        MutableLiveData<CommonUser> m_user = new MutableLiveData<>();
        DocumentReference docRef = Restaurant.getInstance().db.collection("users2").document(String.valueOf(dni));

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot document) {
                if(document != null){
                    CommonUser user = document.toObject(CommonUser.class);
                    m_user.postValue(user);
                }else{
                    System.out.println("Contraseña incorrecta");
                }
            }
        });

        return m_user;
    }


}

package dao;

import com.example.view.BackEnd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Source;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import dataBase.Restaurant;
import model.Category;
import model.CommonUser;
import model.Condition;
import model.PriceStudent;

public class UserDAO {


    //@GET

    public static void getUser(int dni, String password){

        DocumentReference docRef = Restaurant.getInstance().db.collection("users").document(String.valueOf(dni));

        // Source can be CACHE, SERVER, or DEFAULT.
        Source source = Source.CACHE;

        CommonUser user = new CommonUser();


        // Get the document, forcing the SDK to use the offline cache
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot document) {
                if(document.exists()){

                    int dni = Integer.parseInt(document.getData().get("identityCardNumber").toString());

                    String password = (String)document.getData().get("password");
                    String names = (String)document.getData().get("names");
                    String lastName = (String)document.getData().get("lastName");
                    String birthDate = (String)document.getData().get("birthDate");
                    float balance = Float.parseFloat(document.getData().get("balance").toString());
                    String condition = (String)document.getData().get("condition");

                    Category category = null;

                    switch (document.getData().get("category").toString()){
                        case "ALUMNO":
                            category = Category.ALUMNO;
                            break;
                        case "EXTERNO":
                            category = Category.EXTERNO;
                            break;
                        case "NO_DOCENTE":
                            category = Category.NO_DOCENTE;
                            break;
                        case "DOCENTE":
                            category = Category.DOCENTE;
                            break;
                    }
                    System.out.println();
                    user.setIdentityCardNumber(dni);
                    user.setPassword(password);
                    user.setBalance(balance);
                    user.setBirthdate(LocalDate.now());
                    user.setCondition(Condition.NONE);
                    user.setNames(names);
                    user.setLastname(lastName);
                    user.setCategory(Category.ALUMNO);
                    user.setDiscountCalculator(new PriceStudent(0.6f));

                    BackEnd.setLoggedUser(user);

                    System.out.println("datos del user 1 = " + user.getIdentityCardNumber());
                    // {condition=NONE, names=555, birthdate=0001-01-01, balance=0.0, identityCardNumber=555,
                    // passwrod=555, category=ALUMNO, lastname=555}
                }


                }
            }

            /*
            @Override
            public void onSuccess(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();

                    System.out.println("User data del xd = " + document.getData());

                    //todo validar data password
                    if(document.getData() != null){


                }
                else {
                    System.out.println("error al encontrar el usuario" + task.getException());
                }
*/

        );


    }

    //@POST
    public static void addUser(CommonUser user){

        // Create a new user with a first and last name
        Map<String, Object> user_db = new HashMap<>();
        user_db.put("identityCardNumber", user.getIdentityCardNumber());
        user_db.put("password", user.getPassword());
        user_db.put("names", user.getNames());
        user_db.put("lastName", user.getLastname());
        user_db.put("birthDate", user.getBirthdate().toString());
        user_db.put("balance", user.getBalance());
        user_db.put("condition", user.getCondition().toString());
        user_db.put("category", user.getCategory().toString());

        // Add a new document with a generated ID
        Restaurant.getInstance().db.collection("users").document(String.valueOf(user.getIdentityCardNumber())).set(user_db);


    }

    //@UPDATE
    public static boolean loadMoney(int icn, float amount){
        return Restaurant.getInstance().loadMoney(icn,amount); //Return false if the operation fails
    }

    //@UPDATE
    public static void changePassword(int dni, String password){
        Restaurant.getInstance().changePassword(dni,password);
    }



}

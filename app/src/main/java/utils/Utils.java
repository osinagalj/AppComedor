package utils;

import com.example.view.BackEnd;
import com.example.view.food.nestedRecyclerFood.FoodViewModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.Category;
import model.Order;
import model.Product;

public class Utils {

    static public LocalDate getDate(String date) {
        //convertir el string 0001-01-01 de la base de datos a un tipo LocalDate
        return LocalDate.now();
    }

    static public Category getCategory(String name){
        Category category;

        switch (name){
            case "ALUMNO":
                category = Category.ALUMNO;
                break;
            case "NO_DOCENTE":
                category = Category.NO_DOCENTE;
                break;
            case "DOCENTE":
                category = Category.DOCENTE;
                break;
            default:
                category = Category.EXTERNO; //Por defecto es externo ya que a este tipo no se le aplica ninguna descuento
        }

        return category;
    }

    public static Order dbToOrder(QueryDocumentSnapshot document){

        HashMap<String,Object> elements = (HashMap<String,Object>)document.getData().get("products");
        Map<Product,Integer> items = new HashMap<>();

        for (Map.Entry<String, Object> entry : elements.entrySet()) {
            for(Product p : FoodViewModel.list_of_foods){
                if(p.getId() == Integer.parseInt(entry.getKey()))
                    items.put(p,Integer.parseInt(entry.getValue().toString()));
            }
        }

        Timestamp date = (Timestamp)document.getData().get("date");
        Date d = date.toDate();

        int nro = Integer.parseInt(document.getData().get("id").toString());
        Order o = new Order (nro, BackEnd.getLoggedUser(), items,d);
        return o;
    }

    public static  String getCondition(int number){
        switch (number){

            case 1 :
                return "VEGETARIANA";
            case 2 :
                return "VEGANA";
            case 3 :
                return "CELIACO";
            default:
                return "NINGUNA";
        }
    }

}

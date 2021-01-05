package DataBase;

import com.example.view.MyOrders.Fragment.Pendientes.Order;
import com.example.view.R;

import java.util.ArrayList;

public class Repositorio {

    private int id;
    ArrayList<Order> ordenes = new ArrayList<Order>();


    public Repositorio(){
        Order o1 = new Order("prueba77","$50", R.drawable.food_alfajor_pepitos);
        Order o3 = new Order("prueba79","$60",R.drawable.food_empanada);
        Order o4 = new Order("prueba80","$80",R.drawable.food_empanada);

        ordenes.add(o1);
        ordenes.add(o3);
        ordenes.add(o4);
    }

    public void putOrder(Order o){
        ordenes.add(o);
    }
    public  ArrayList<Order> getOrders(){
        return ordenes;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
}

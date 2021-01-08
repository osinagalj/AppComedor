package DataBase;

import com.example.view.MyOrders.Fragment.Pendientes.Order;
import com.example.view.R;

import java.util.ArrayList;

public class Repositorio {

    private int id;
    ArrayList<Order> ordenes = new ArrayList<Order>();
    ArrayList<Order> confirmedOrders = new ArrayList<Order>();
    ArrayList<Integer> filaPendientes = new ArrayList<Integer>();

    public Repositorio(){
        Order o1 = new Order("Pedido 1","2$",R.drawable.food_vaso_coca);
        Order o2 = new Order("Pedido 2","6$",R.drawable.food_milanesas_con_fritas);
        Order o3 = new Order("Pedido 3","20$",R.drawable.food_turron_arcor);

        confirmedOrders.add(o1);
        confirmedOrders.add(o2);
        confirmedOrders.add(o3);

        filaPendientes.add(20001);
        filaPendientes.add(20002);
        filaPendientes.add(20003);

        ordenes.add(o1);
        ordenes.add(o2);
        ordenes.add(o3);

    }
    public void eliminarPrimeraOrdenPendiente(){
        ordenes.remove(0);
    }

    public  ArrayList<Integer> getFilaPendientes(){
        return filaPendientes;
    }

    public void putOrder(Order o){
        ordenes.add(o);
    }
    public  ArrayList<Order> getOrders(){
        return ordenes;
    }

    public void addConfirmedOrder(Order o){
        confirmedOrders.add(o);
    }
    public  ArrayList<Order> getConfirmedOrders(){
        return confirmedOrders;
    }

    public  ArrayList<Order> getOrder(int id){
        return confirmedOrders;
    } // implementar desp

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
}

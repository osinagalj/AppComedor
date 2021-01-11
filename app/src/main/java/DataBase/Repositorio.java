package DataBase;

import com.example.view.R;

import java.util.ArrayList;

public class Repositorio {

    private int id;
    ArrayList<Order> ordenes = new ArrayList<Order>();
    ArrayList<Order> confirmedOrders = new ArrayList<Order>();
    ArrayList<Integer> filaPendientes = new ArrayList<Integer>();

    public static Repositorio repo = new Repositorio();
    private int numero_order = 100007;

    public int getNroOrden(){
        int result = numero_order;
        numero_order++;
        return result;
    }

    public Repositorio(){
        Order o1 = new Order(100001,1,"Milanesas con fritas","2$",R.drawable.food_vaso_coca);
        Order o2 = new Order(100002,5,"Una coka","6$",R.drawable.food_milanesas_con_fritas);
        Order o3 = new Order(100003,2,"Otra koka","20$",R.drawable.food_pepas_trio);

        confirmedOrders.add(o1);
        confirmedOrders.add(o2);
        confirmedOrders.add(o3);

        filaPendientes.add(20001);
        filaPendientes.add(20002);
        filaPendientes.add(20003);

        ordenes.add(o1);


    }

    public void removePendOrder(int nro_orden){
        System.out.println("Nroor = "+ nro_orden);
        for(int i=0; i<ordenes.size();i++){

            System.out.println("Numero de orden = " + ordenes.get(i).getId());
            if (ordenes.get(i).getId() == nro_orden){
                ordenes.remove(i);
                System.out.println("Eliminoooo");
            }

        }
        System.out.println("repositorio size = " + ordenes.size());
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

package DataBase;

import com.example.view.R;

import java.util.ArrayList;

import ModeloGian.Food;
import ModeloGian.Product;
import ModeloGian.ProductCategory;

public class Repositorio {

    private int id;
    ArrayList<Order> ordenes = new ArrayList<Order>();
    ArrayList<Order> confirmedOrders = new ArrayList<Order>();
    ArrayList<Integer> filaPendientes = new ArrayList<Integer>();
    ArrayList<Product> miCarrito = new ArrayList<>();

    public static Repositorio repo = new Repositorio();
    private int numero_order = 100007;

    public int getNroOrden(){
        int result = numero_order;
        numero_order++;
        return result;
    }

    public Repositorio(){
        Order o1 = new Order(100001,1,"Milanesas con fritas","22.8$",R.drawable.food_vaso_coca);
        Order o2 = new Order(100002,1,"Milanesas con fritas","22.8$",R.drawable.food_vaso_coca);
        Order o3 = new Order(100003,1,"Milanesas con fritas","22.8$",R.drawable.food_vaso_coca);
        Order o4 = new Order(100004,1,"Milanesas con fritas","22.8$",R.drawable.food_vaso_coca);
        Order o5 = new Order(100005,1,"Milanesas con fritas","22.8$",R.drawable.food_vaso_coca);

        confirmedOrders.add(o1);
        confirmedOrders.add(o1);
        ordenes.add(o1);
        ordenes.add(o2);
        ordenes.add(o3);
        ordenes.add(o4);
        ordenes.add(o5);


        filaPendientes.add(20001);
        filaPendientes.add(20002);
        filaPendientes.add(20003);

        //Mi carrito
        miCarrito.add(new Food(0002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));

    }

    public boolean carritoVacio(){
        if(miCarrito.size()>0){
            return false;
        }
        return true;
    }
    //Para elimianr las ordenes en MisOrdenes
    public void removePendOrder(int nro_orden){
        for(int i=0; i<ordenes.size();i++) {
            if (ordenes.get(i).getId() == nro_orden) {
                ordenes.remove(i);
            }
        }
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

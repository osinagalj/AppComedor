package DAO;

import java.util.ArrayList;
import java.util.List;

import ModeloGian.CommonUser;
import ModeloGian.Order;
import ModeloGian.Product;
import ModeloGian.Restaurant;

public class OrderDAO {

    static ArrayList<Order> Confirmedorders = new ArrayList<>();
    static ArrayList<Order> PendingOrders = new ArrayList<>();


    public static void addPendingOrder(Order o){
        PendingOrders.add(o);
    }
    public static void addConfirmedOrder(Order o){
        Confirmedorders.add(o);
    }

    public static Order getOrder(int id){
        Order o = new ModeloGian.Order(1, Restaurant.getInstance().miOrden);
        //TODO obtener una orden por su id para mostrar en el detalle de la orden
        return o;
    }

    public static List<Order> getPendingOrders(CommonUser user){
        List<Order> products = new ArrayList<>();
        //TODO para mostrar en ordenes pendientes, creo que es mejor sacarls de la base de datos y no del usuario
        List<Order> ar = Restaurant.getInstance().ordenesPendientes;
        for(Order o : ar){
            products.add(o);
        }
        List<Product> lista = new ArrayList<>();
        products.add(new Order(213113,lista));
        products.add(new Order(213114,lista));
        products.add(new Order(213115,lista));


        return products;
    }

    public static List<Order> getConfirmedOrders(CommonUser user){

        //TODO lo mismo
        List<Order> products = new ArrayList<>();
        List<Order> ar = Restaurant.getInstance().ordenesPendientes;
        for(Order o : ar){
            products.add(o);
        }
        List<Product> lista = new ArrayList<>();
        products.add(new Order(200001,lista));
        products.add(new Order(200002,lista));

        return products;
    }

    public static List<Order> nextOrders(CommonUser user){
        List<Order> products = new ArrayList<>();
        //TODO las proximas ordenes para mostrar en FILA
        return products;
    }
}

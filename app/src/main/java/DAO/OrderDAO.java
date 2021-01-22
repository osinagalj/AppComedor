package DAO;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

import Model.CommonUser;
import Model.Food;
import Model.Order;
import Model.Product;
import Model.ProductCategory;
import Model.Restaurant;

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
        Order o = new Model.Order(1, Restaurant.getInstance().miCarrito);
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

        lista.add(new Food(0002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));
        lista.add(new Food(0003,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        lista.add(new Food(0004,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        lista.add(new Food(0005,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));

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

    public static List<Order> nextOrders() {
        //TODO obtener las proximas 20 ordenes
        //TODO las proximas ordenes para mostrar en FILA
        List<Order> products = new ArrayList<>();

        List<Product> lista = new ArrayList<>();
        products.add(new Order(200001,lista));
        products.add(new Order(200002,lista));
        products.add(new Order(200003,lista));
        products.add(new Order(200004,lista));
        products.add(new Order(200005,lista));
        products.add(new Order(200006,lista));
        products.add(new Order(200004,lista));
        products.add(new Order(200005,lista));
        products.add(new Order(200006,lista));
        products.add(new Order(200007,lista));
        products.add(new Order(200008,lista));
        products.add(new Order(200009,lista));
        return products;
    }

    public static Order nextOrder(CommonUser user) {
        //TODO obtener la proxima orden del usuario para mostrar en FILA
        List<Product> lista = new ArrayList<>();
        Order o = new Order(1,lista);

        return o;
    }

    public static String timeToNextOrder(CommonUser user) {
        //TODO calcular el tiempo que hay entre la orden actual y
        // la orden proxima del usuario, osea el tiempo que tarda cada orden lo podemos ahcer con un random

        return "05:01 min";
    }
}

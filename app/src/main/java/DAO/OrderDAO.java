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



    /*
    //order ID, Instant placed, DNI placed by
    private static final String ORDERS = System.getProperty("user.home") + File.separator + "comedor" + File.separator + "orders.csv";
    //order ID, Product barcode
    private static final String BUYED_PRODUCTS = System.getProperty("user.home") + File.separator + "comedor" + File.separator + "buyed_products.csv";


    public static List<Order> getOrders() throws FileNotFoundException {
        List<Order> orders = new ArrayList<>();
        List<Product> products = ProductDAO.avalaibleProducts();
        List<CommonUser> users = UserDAO.registeredUsers();
        try {
            BufferedReader orderReader = new BufferedReader(new FileReader(ORDERS));
            String row;
            String[] data = null;
            List<Product> orderProducts;
            while ((row = orderReader.readLine()) != null) {
                data = row.split(",");
                orderProducts = getProducts(data[0],)
                orders.add(new Order())
            }
            orderReader.close();
        } catch (FileNotFoundException e) { //If the file dont exists, create them
            try {
                FileWriter csvWriter = new FileWriter(ORDERS);
                csvWriter.flush();
                csvWriter.close();
                return orders; //return empty list because dont exist previous orders
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileNotFoundException("Error intentando abrir el archivo de ordenes");
    }

    private static HashMap<Integer, List<Product>> getProductsLists() {
        BufferedReader productsReader = new BufferedReader((new FileReader(BUYED_PRODUCTS)));

        String row;
        String[] data;
        while ((row = products.readLine()) != null) {
            data = row.split(",");

        }
    }

    public static void main(String[] args) {
        try {
            OrderDAO.getOrdersPlaced();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    */
}

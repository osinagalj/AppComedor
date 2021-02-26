package dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Restaurant;
import model.CommonUser;
import model.Order;
import model.Product;

public class OrderDAO {

    //@POST
    public static void loadPendingOrder(Order order){

            Map<String, Object> new_order = new HashMap<>();
            new_order.put("id", order.getId());
            new_order.put("date", LocalDate.now().toString());
            new_order.put("user_id", order.getPlacedBy().getIdentityCardNumber());

            Map<String, Integer> products = new HashMap<>(); //<id,amount>
            for(Product p : order.getItems())
                products.put(String.valueOf(p.getId()),order.getAmount(p));
            new_order.put("products", products);

            // Add a new document with a generated ID

            Restaurant.getInstance().db.collection("pending_orders").document(String.valueOf(order.getId())).set(new_order);

        //Restaurant.getInstance().addOrder(order);
    }

    //@POST
    public static void loadConfirmedOrder(Order order){

        Map<String, Object> new_order = new HashMap<>();
        new_order.put("id", order.getId());
        new_order.put("date", LocalDate.now().toString());
        new_order.put("user_id", order.getPlacedBy().getIdentityCardNumber());

        Map<String, Integer> products = new HashMap<>(); //<id,amount>
        for(Product p : order.getItems())
            products.put(String.valueOf(p.getId()),order.getAmount(p));
        new_order.put("products", products);

        // Add a new document with a generated ID

        Restaurant.getInstance().db.collection("confirmed_orders").document(String.valueOf(order.getId())).set(new_order);

        //Restaurant.getInstance().addOrder(order);
    }

    //@GET
    public static List<Order> getCompletedOrders(CommonUser user) {
        return Restaurant.getInstance().getOrdersCompleted(user);
    }

    //@GET
    public static List<Order> getPendingOrders(CommonUser user) {
        return Restaurant.getInstance().getPendingOrders(user);
    }

    //@GET
    public static List<String> nextOrders() {
        return Restaurant.getInstance().getNextOrders();
    }

    //@GET
    public static String timeNextOrder(CommonUser user){
        //TODO devuelve el tiempo que debe esperar el usuario para su la proxima orden
        return "05:02 min";
    }

    //@DELETE
    public static Order removeOrder(int id){
        //TODO
        return null;
    }


}

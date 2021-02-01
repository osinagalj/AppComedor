package DAO;

import java.util.List;

import DataBase.Restaurant;
import Model.CommonUser;
import Model.Order;

public class OrderDAO {

    //@POST
    public static void loadPendingOrder(Order order){
        Restaurant.getInstance().addOrder(order);
    }

    //@POST
    public static void loadConfirmedOrder(Order order){
        Restaurant.getInstance().addOrder(order);
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

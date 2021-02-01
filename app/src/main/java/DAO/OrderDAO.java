package DAO;

import java.util.ArrayList;
import java.util.List;

import DataBase.Restaurant;
import Model.CommonUser;
import Model.Order;

public class OrderDAO {



    public static void loadOrder(Order order){
        Restaurant.getInstance().addOrder(order);
    }

    public static void confimOrder(Order order){
        //DataBase.confimr
    }

    public static List<Order> nextOrders() {
        //TODO obtener las proximas 20 ordenes
        //TODO las proximas ordenes para mostrar en FILA
        return Restaurant.getInstance().getNextOrders();
    }

    public static boolean removeOrder(int id){
        //DataBase.getInstance().removeOrder(id)
        return true; //true si se elimino correctamente, false sino
    }

    //No se si hay que diferenciar en la base de datos entre ordenes confimadas y pendientes
    public static List<Order> getCompletedOrders(CommonUser user) {
        List<Order> completedOrders = new ArrayList<>();
        return completedOrders;
    }

    //No se si hay que diferenciar en la base de datos entre ordenes confimadas y pendientes
    public static List<Order> getPendingOrders(CommonUser user) {
        return Restaurant.getInstance().getPendingOrders(user);
    }
}

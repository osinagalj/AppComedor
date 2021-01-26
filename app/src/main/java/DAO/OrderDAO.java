package DAO;

import com.example.view.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.CommonUser;
import Model.Food;
import Model.Order;
import Model.Product;
import Model.ProductCategory;
import Model.Restaurant;

public class OrderDAO {

    static ArrayList<Order> Confirmedorders = new ArrayList<>();

    public static void addConfirmedOrder(Order o) {
        Confirmedorders.add(o);
    }

    public static List<Order> nextOrders() {
        //TODO obtener las proximas 20 ordenes
        //TODO las proximas ordenes para mostrar en FILA
        List<Order> products = new ArrayList<>();

        return products;
    }

    public static List<Order> getCompletedOrders(CommonUser user) {
        Product randomProduct = ProductDAO.avalaibleProducts().get(0);
        Map<Product,Integer> orderItems = new HashMap<>();
        orderItems.put(randomProduct,1);
        Order completedOrder = new Order(user,orderItems,new HashMap<>());
        List<Order> completedOrders = new ArrayList<>();
        completedOrders.add(completedOrder);
        return completedOrders;
    }
}

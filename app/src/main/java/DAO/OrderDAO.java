package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.CommonUser;
import Model.Order;
import Model.Product;

public class OrderDAO {
    /**
     * Al estar hardcodeadas las ordenes, este numero tiene que ser consistente
     * con la cantidad de ordenes que se les cargan a los usuarios en getCompletedOrders()
     * Al momento de hacer esto, hay 1 solo usuario al que se la carga solo una orden en el
     * historial
     * TODO queda feo que sean de 1 digito xd
     */
    public static final int LAST_ORDER_NUMBER = 100000;
    //para mi el moquear la base de datos seria tener aca una lista de ordenes, y cuando hacemos una
    // consulta al backend usamos estos dao, no tenemos persistencia pero no importa

    static List<Order> products = new ArrayList<>();

    public static void loadOrders(){
        //products.add()
    }

    public static List<Order> nextOrders() {
        //TODO obtener las proximas 20 ordenes
        //TODO las proximas ordenes para mostrar en FILA
        return products;
    }

    public static List<Order> getCompletedOrders(CommonUser user) {
        Product randomProduct = ProductDAO.avalaibleProducts(null).get(0);
        Map<Product,Integer> orderItems = new HashMap<>();
        orderItems.put(randomProduct,1);
        Order completedOrder = new Order(1,user,orderItems,new HashMap<>());
        List<Order> completedOrders = new ArrayList<>();
        completedOrders.add(completedOrder);
        return completedOrders;
    }
}

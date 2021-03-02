package com.example.view;

import com.example.view.myOrders.fragment.pendientes.PendingOrdersViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.CommonUser;
import model.Order;
import model.Product;


//Aca va la logica de negocio, por ejemplo para pedir un menu del dia, primero se
// hacen los chekeos: que tenga saldo y que tenga menus disponibles en el dia(el maximo es 2)
public class BackEnd {

    private static CommonUser loggedUser;
    private static Order myOrder; //todo Capaz que hay que tener la lista de productos y no la orden

    public static CommonUser getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(CommonUser user) {
        BackEnd.loggedUser = user;


        myOrder = new Order(1,loggedUser,new HashMap<>()); //todo
    }

    public static void setOrderNumber(int id){
        myOrder.setId(id);
    }
    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------   OrderDAO   -----------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    //TODO
    public static List<Order> getConfirmedOrders() {
        return OrderDAO.getCompletedOrders(loggedUser);
    }

    public static List<Order> getPendingOrders() {
        return OrderDAO.getPendingOrders(loggedUser);
    }

    public static List<String> getNextOrders() {
        return OrderDAO.nextOrders();
    }


    public static boolean addProduct(Product product, int amount, boolean toHome) {
        //TODO

        //Tengo que devolver true si se pudo agregar el pedido,sino false porque no hay stock
        //Si hay stock
        //
        myOrder.addProduct(product, amount,toHome);
        /*
        if(!ProductDAO.decreaseStock(product.getId(),amount)){      //SI Hay stock
            return false;
        }else{
            if (!myOrder.getItems().contains(product)){         //Si no existe en la orden lo agrego
                 myOrder.addProduct(product, amount,toHome);
            }else{
                 myOrder.changeAmount(product, amount, toHome); //Si existe en la orden aumento la cantidad
            }
        }*/
        return true;
    }

    public static int getMenusRestantes(LocalDate date) {
        int suma = 0;
        for (Order o : PendingOrdersViewModel.list_of_orders)
            //if(o.getPlacedInstant() == date) todo
            for (Product p : o.getItems()) {
                if (p.getCategory() == 1) {
                    suma++;
                }
            }
        if(suma < 2)
            return 2 - suma;
        else
            return 0;
    }

    public static Order getOrder(){
        return myOrder;
    }
    public static void confirmOrder(int id){
        myOrder.setId(id);
        OrderDAO.loadPendingOrder2(myOrder);
        BackEnd.clearOrder();
    }

    public static void clearOrder(){
        //todo capaz hay que liberar memoria
        myOrder = new Order(1,loggedUser,new HashMap<>()); //todo
    }

    public static float getOrderPrice(){
        return myOrder.getPrice();
    }

    public static List<Product> getProductsOrder(){
        List<Product> list = new ArrayList<>();
        list.addAll(myOrder.getItems());
        //list.addAll(myOrder.getToHome());
        return list;
    }


    /**
     *     //Primero se elimina el producto y despues se incrementa el stock para evitar que un usuario agregue un producto
     *     // (suponiendo que ya se actualizo el stock pero en realidad el usuario loggeado lo sigue teniendo en el carro)
     */
    public static void removeProduct(Product product){
        int amount = myOrder.getAmount(product);
        myOrder.removeProduct(product);
        ProductDAO.increaseStock(product.getId(),amount);
    }

    public static int getAmount(Product product){
        return myOrder.getAmount(product);
    }

    public static String getTimeToNextOrder(){
        return OrderDAO.timeNextOrder(loggedUser);
    }
    public static String getNextOrder(){
        return "#100322";
    }

    public static boolean orderIsEmpty(){
        if(myOrder.getItems().isEmpty())
            return true;
        return false;
    }


    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------ ProductDAO   -----------------------------------------//
    //-------------------------------------------------------------------------------------------------//
    public static void getProductById(int id){
        ProductDAO.getProductById(id);
    }

    public static List<Product> getProducts(){
        //TODO aca se aplicaria la logica de negocio capaz para el descuento y eso
        List<Product> products = new ArrayList<>();
        //products.add(dailyMenu);
        products.addAll(ProductDAO.getProducts(loggedUser));
        return products;
    }

    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------ UserDAO   --------------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    public static void addUser(CommonUser user){
        UserDAO.addUser2(user);
    }

    public static boolean loadMoney(float amount){
        return UserDAO.loadMoney(loggedUser.getIdentityCardNumber(),amount);
    }
    public static boolean transferMoney(int dni, float amount){
        if(UserDAO.loadMoney(loggedUser.getIdentityCardNumber(),amount * -1)) //Si se pudo descontar el dinero al usuario
            return UserDAO.loadMoney(dni,amount);                                //agregar dinero al destinatario
        else
            return false;                                                 //Sino devolver false indicando que no se pudo
    }

    public static void changePassword(String password){
        UserDAO.changePassword(loggedUser.getIdentityCardNumber(),password);
    }

}

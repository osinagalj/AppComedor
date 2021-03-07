package com.example.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dao.OrderDAO;
import dao.UserDAO;
import model.CommonUser;
import model.Order;
import model.Product;

public class BackEnd {

    private static CommonUser loggedUser;
    private static Order myOrder;

    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------ UserDAO   --------------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    public static CommonUser getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(CommonUser user) {
        BackEnd.loggedUser = user;
        myOrder = new Order(1,loggedUser,new HashMap<>()); //todo
    }

    public static void addUser(CommonUser user){
        UserDAO.addUser2(user);
    }

    public static boolean loadMoney(float amount){
        return UserDAO.loadMoney(loggedUser.getIdentityCardNumber(),loggedUser.getBalance() + amount);
    }
    public static boolean transferMoney(int dni, float amount){
        if(UserDAO.loadMoney(loggedUser.getIdentityCardNumber(),loggedUser.getBalance() - amount)) //Si se pudo descontar el dinero al usuario
            return UserDAO.loadMoney(dni,amount);                                //agregar dinero al destinatario
        else
            return false;                                                 //Sino devolver false indicando que no se pudo
    }

    public static void changePassword(String password){
        UserDAO.changePassword(loggedUser.getIdentityCardNumber(),password);
    }

    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------   OrderDAO   -----------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    public static void setOrderNumber(int id){
        myOrder.setId(id);
    }

    public static void addProduct(Product product, int amount, boolean toHome) {

        myOrder.addProduct(product, amount);
            if (!myOrder.getItems().contains(product)){         //Si no existe en la orden lo agrego
                 myOrder.addProduct(product, amount);
            }else{
                 myOrder.changeAmount(product, amount, toHome); //Si existe en la orden aumento la cantidad
            }
    }

    /** Obtiene la cantidad de menus del dia que ha pedido un usuario en el dia actual
    */
    public static int getMenusRestantes(Date date, List<Order> orders) {

        String today = new SimpleDateFormat("dd/MM/yyyy").format(date);

        int total = 0;
        for (Order o : orders){
            if(o.getPlacedBy().getIdentityCardNumber() == loggedUser.getIdentityCardNumber()){
                String dat = new SimpleDateFormat("dd/MM/yyyy").format(o.getPlaced());
                if(today.equals(dat)){
                    for (Product p : o.getItems()) {
                        if (p.getCategory() == 1) {
                            total++;
                        }
                    }
                }
            }
        }

        if(total < 2)
            return 2 - total;
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
        myOrder = new Order(1,loggedUser,new HashMap<>()); //todo
    }

    public static float getOrderPrice(){
        return myOrder.getPrice();
    }

    public static List<Product> getProductsOrder(){
        List<Product> list = new ArrayList<>();
        list.addAll(myOrder.getItems());
        return list;
    }


    /**
     *     //Primero se elimina el producto y despues se incrementa el stock para evitar que un usuario agregue un producto
     *     // (suponiendo que ya se actualizo el stock pero en realidad el usuario loggeado lo sigue teniendo en el carro)
     */
    public static void removeProduct(Product product){
        int amount = myOrder.getAmount(product);
        myOrder.removeProduct(product);
        //ProductDAO.increaseStock(product.getId(),amount); todo
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
/*
    public static List<Product> getProducts(){
        //TODO aca se aplicaria la logica de negocio capaz para el descuento y eso
        List<Product> products = new ArrayList<>();
        //products.add(dailyMenu);
        products.addAll(ProductDAO.getProducts(loggedUser));
        return products;
    }
*/


}

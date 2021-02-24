package com.example.view;

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

    private static CommonUser loggedUser; //TODO inicializado solo para saltear el login en el manifest
    private static Order myOrder = new Order(loggedUser,new HashMap<>(),new HashMap<>());
    private static Product dailyMenu;


    public static CommonUser getLoggedUser() {
        return loggedUser;
    }

    public static boolean setLoggedUser(int dni, String password) throws InterruptedException {
        UserDAO.getUser(dni, password);
        if(BackEnd.loggedUser == null)
            return false;
        return true;
       // Thread.sleep(10000);
    }



    public static void setLoggedUser(CommonUser user) {
        BackEnd.loggedUser = user;
    }


    public static void setDailyMenu(){
        dailyMenu = ProductDAO.getSpecialProduct(loggedUser);
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
        //Tengo que devolver true si se pudo agregar el pedido,sino false porque no hay stock
        //Si hay stock
        //
        if(!ProductDAO.decreaseStock(product.getId(),amount)){      //SI Hay stock
            return false;
        }else{
            if (!myOrder.getItems().contains(product)){         //Si no existe en la orden lo agrego
                 myOrder.addProduct(product, amount,toHome);
            }else{
                 myOrder.changeAmount(product, amount, toHome); //Si existe en la orden aumento la cantidad
            }
        }
        return true;
    }

    public static Order getOrder(){
        return myOrder;
    }
    public static void confirmOrder(){
        OrderDAO.loadPendingOrder(myOrder);
        BackEnd.clearOrder();
    }

    public static void clearOrder(){
        //todo capaz hay que liberar memoria
        myOrder = new Order(loggedUser,new HashMap<>(),new HashMap<>());
    }

    public static float getOrderPrice(){
        return myOrder.getPrice();//todo
    }

    public static List<Product> getProductsOrder(){
        List<Product> list = new ArrayList<>();
        list.addAll(myOrder.getItems());
        list.addAll(myOrder.getToHome());
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
        if(myOrder.getItems().isEmpty() && myOrder.getToHome().isEmpty())
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
        products.add(dailyMenu);
        products.addAll(ProductDAO.getProducts(loggedUser));
        return products;
    }

    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------ UserDAO   --------------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    public static void addUser(CommonUser user){
        UserDAO.addUser(user);
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

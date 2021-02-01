package com.example.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.CommonUser;
import Model.Order;
import Model.Product;

//Aca va la logica de negocio, por ejemplo para pedir un menu del dia, primero se
// hacen los chekeos: que tenga saldo y que tenga menus disponibles en el dia(el maximo es 2)
public class BackEnd {

    private static CommonUser loggedUser = UserDAO.getUser(111, "111"); //TODO inicializado solo para saltear el login en el manifest
    private static Order myOrder = new Order(loggedUser,new HashMap<>(),new HashMap<>());

    public static CommonUser getLoggedUser() {
        return UserDAO.getUser(loggedUser.getIdentityCardNumber(), loggedUser.getPassword());
    }

    public static boolean setLoggedUser(int dni, String password) {
        BackEnd.loggedUser = UserDAO.getUser(dni, password);
        if(BackEnd.loggedUser == null)
            return false;
        return true;
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

    public static void addProduct(Product product, int amount ) {
        if (!myOrder.getItems().contains(product))
            myOrder.addProduct(product, amount);
        else
            myOrder.changeAmount(product, amount);
    }

    public static void addProductHome(Product product, int amount ){
        //todo agregar chequeo de cantidad disponible
        if (!myOrder.getToHome().contains(product))
            myOrder.addProductToHome(product, amount);
        else
            myOrder.changeAmount(product, amount);//todo change this
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

    public static void removeProduct(Product product){

        myOrder.removeProduct(product); //todo hacerlo bien
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

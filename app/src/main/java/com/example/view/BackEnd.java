package com.example.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import DataBase.Restaurant;
import Model.CommonUser;
import Model.Order;
import Model.Product;

//Aca va la logica de negocio, por ejemplo para pedir un menu del dia, primero se
// hacen los chekeos: que tenga saldo y que tenga menus disponibles en el dia(el maximo es 2)
public class BackEnd {

    private static CommonUser loggedUser = UserDAO.getUser(111, "111"); //TODO inicializado solo para saltear el login en el manifest
    private static Order myOrder = new Order(loggedUser,new HashMap<>(),new HashMap<>());

    public static CommonUser getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(int dni, String password) { BackEnd.loggedUser = UserDAO.getUser(dni, password); }


    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------   OrderDAO   -----------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    //TODO
    public static void getConfirmedOrders() {
        OrderDAO.getCompletedOrders(loggedUser);
    }

    public static List<Order> getPendingOrders() {
        return OrderDAO.getPendingOrders(loggedUser);
    }

    public static void getNextOrders() {
        OrderDAO.nextOrders();
    }//todo

    public static void addProduct(Product product, int amount ) {
        System.out.println("ENTRO EN ADD PRODUCT");
        if (!myOrder.getItems().contains(product))
            myOrder.addProduct(product, amount);
        else
            myOrder.changeAmount(product, amount);
    }

    public static void addProductHome(Product product, int amount ){
        //Chequear si se puede
        myOrder.addProductToHome(product,amount);
    }

    public static Order getOrder(){
        return myOrder;
    }
    public static void confirmOrder(){
        OrderDAO.loadOrder(myOrder);
        BackEnd.clearOrder();
    }

    public static void clearOrder(){
        //todo capaz hay que liberar memoria
        myOrder = new Order(loggedUser,new HashMap<>(),new HashMap<>());
    }

    public static float getOrderPrice(){
        return 1770f;
    }

    public static List<Product> getProductsOrder(){
        List<Product> list = new ArrayList<>();
        list.addAll(myOrder.getItems());
        list.addAll(myOrder.getToHome());
        return list;
    }

    public static void removeProduct(Product product){
        myOrder.getItems().remove(product); //todo hacerlo bien
    }

    public static int getAmount(Product product){
        return myOrder.getAmount(product);
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
        ProductDAO.getDailyMenu(loggedUser);
        products.addAll(ProductDAO.getProducts(loggedUser));
        return products;
    }

    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------ UserDAO   --------------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    public static void addUser(CommonUser user){
        UserDAO.addUser(user);
    }

    public static CommonUser getUser(int dni, String password){
        return Restaurant.getInstance().getUser(dni,password); //devolver una copia no esto
    }

    public static void loadMoney(float amount){
        UserDAO.loadMoney(loggedUser.getIdentityCardNumber(),amount);
    }
    public static boolean trasnferMoney(int dni, float amount){
        if(UserDAO.chargeUser(loggedUser.getIdentityCardNumber(),amount)) //Si se pudo descontar el dinero al usuario
            UserDAO.loadMoney(dni,amount);                                //agregar dinero al destinatario
        else
            return false;                                                 //Sino devolver false indicando que no se pudo
        return true;
    }

    public static void changePassword(String password){
        UserDAO.changePassword(loggedUser.getIdentityCardNumber(),password);
    }


    /**
     * EL RESTO DE METODOS IRIAN ACA, SON BASICAMENTE LOS MISMOS UQE EL DAO, ENTONCES
     * EL CONTROLADOR NO SE COMUNICA CON EL DAO, SINO CON ESTE BACKEND, Y SE PODRIA APLICAR LA LOGICA QUE SE NECESITE
     * lO BUENO ES QUE YA TENEMOS EL USUARIO LOGGEADO ACA
     */

}

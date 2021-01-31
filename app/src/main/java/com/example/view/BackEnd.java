package com.example.view;

import java.util.List;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.CommonUser;
import Model.Product;

public class BackEnd {
    private static CommonUser loggedUser = UserDAO.getUser(111, "111"); //TODO inicializado solo para saltear el login en el manifest

    public static CommonUser getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(int dni, String password) {
        BackEnd.loggedUser = UserDAO.getUser(dni, password);
    }
    
    
    //Aca va la logica de negocio, por ejemplo para pedir un menu del dia, primero se
    // hacen los chekeos: que tenga saldo y que tenga menus disponibles en el dia(el maximo es 2)
    //TODO
    public static void getConfirmedOrders() {
        OrderDAO.getCompletedOrders(loggedUser);
    }

    public static void getPendingOrders() {
        OrderDAO.getPendingOrders(loggedUser);
    }

    public static void getNextOrders() {
        OrderDAO.nextOrders();
    }

    public static List<Product> getProducts(){
        //TODO aca se aplicaria la logica de negocio capaz para el descuento y eso
        return ProductDAO.getProducts(loggedUser);
    }

    public static void getProductById(int id){
        ProductDAO.getProductById(id);
    }

    public static void addUser(CommonUser user){
        UserDAO.addUser(user);
    }
    //todo faltan los del usuario y algunas ordenes

    /**
     * EL RESTO DE METODOS IRIAN ACA, SON BASICAMENTE LOS MISMOS UQE EL DAO, ENTONCES
     * EL CONTROLADOR NO SE COMUNICA CON EL DAO, SINO CON ESTE BACKEND, Y SE PODRIA APLICAR LA LOGICA QUE SE NECESITE
     * lO BUENO ES QUE YA TENEMOS EL USUARIO LOGGEADO ACA
     */

}

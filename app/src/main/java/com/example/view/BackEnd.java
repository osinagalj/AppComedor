package com.example.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.OrderDAO;
import dao.UserDAO;
import model.CommonUser;
import model.Order;
import model.OrderLine;
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
        myOrder = new Order(1,loggedUser); //todo
    }

    public static void addUser(CommonUser user){
        UserDAO.addUser(user);
    }

    public static boolean loadMoney(float amount){
        return UserDAO.loadMoney(loggedUser.getIdentityCardNumber(), loggedUser.getBalance()+amount);
    }

    public static boolean transferMoney(int dni, float amount){
        if(UserDAO.loadMoney(loggedUser.getIdentityCardNumber(), loggedUser.getBalance()- amount)) //Si se pudo descontar el dinero al usuario
            return UserDAO.loadMoney(dni,amount);                                                          //agregar dinero al destinatario
        else
            return false;                                                                                  //Sino devolver false indicando que no se pudo
    }

    public static void changePassword(String password){
        UserDAO.changePassword(loggedUser.getIdentityCardNumber(),password);
        loggedUser.setPassword(password);
    }

    //-------------------------------------------------------------------------------------------------//
    //------------------------------------------   OrderDAO   -----------------------------------------//
    //-------------------------------------------------------------------------------------------------//

    public static void addProduct(Product product, int amount, boolean toHome) {

            myOrder.addProduct(product, amount, toHome);
    }

    /** Obtains the number of menus of the day that a user has requested on the current day*/
    public static int getDailyMenusRemaining(Product p, Date date, List<Order> orders) {

        String today = new SimpleDateFormat("dd/MM/yyyy").format(date);
        int total = 0;
        for (Order o : orders)
            if(o.getPlacedBy().getIdentityCardNumber() == loggedUser.getIdentityCardNumber()){
                String dat = new SimpleDateFormat("dd/MM/yyyy").format(o.getPlaced());
                if(today.equals(dat))
                    for (OrderLine line : o.getLines()){
                        if(line.getProduct() != null){
                            if (line.getProduct().getCategory() == 1)
                                total++;
                        }
                    }
            }

        if(total < p.getDailyLimit())
            return p.getDailyLimit() - total;
        else
            return 0;
    }

    public static Order getOrder(){
        return myOrder;
    }

    public static void confirmOrder(int id){
        myOrder.setId(id);
        OrderDAO.loadOrder(myOrder,true);
        BackEnd.clearOrder();
    }

    public static void clearOrder() {
        myOrder.clearOrder();
    }

    public static float getOrderPrice(){
        return myOrder.getPrice();
    }



    public static void removeProduct(Product product){
        myOrder.removeProduct(product.getId());
    }

    public static int getAmount(Product product){
        return myOrder.getAmount(product.getId());
    }

    public static String getTimeToNextOrder(){
        return OrderDAO.timeNextOrder(loggedUser);
    }
    public static String getNextOrder(){
        return "#100322";
    }

    public static boolean orderIsEmpty(){
        if(myOrder.getLines().isEmpty())
            return true;
        return false;
    }

}

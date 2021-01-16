package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ModeloGian.CommonUser;
import ModeloGian.Order;
import ModeloGian.Product;


public class OrderDAO {
    //order ID, Instant placed, DNI placed by
    private static final String ORDERS = System.getProperty("user.home") + File.separator + "comedor" + File.separator + "orders.csv";
    //order ID, Product barcode
    private static final String BUYED_PRODUCTS = System.getProperty("user.home") + File.separator + "comedor" + File.separator + "buyed_products.csv";

    private OrderDAO(){}
/*
    public static List<Order> getOrders() throws FileNotFoundException {
        List<Order> orders = new ArrayList<>();
        List<Product> products = ProductDAO.avalaibleProducts();
        List<CommonUser> users = UserDAO.registeredUsers();
        try {
            BufferedReader orderReader = new BufferedReader(new FileReader(ORDERS));
            String row;
            String[] data = null;
            List<Product> orderProducts;
            while ((row = orderReader.readLine()) != null) {
                data = row.split(",");
                //orderProducts = getProducts(data[0],)
                //orders.add(new Order())
            }
            orderReader.close();
        } catch (FileNotFoundException e) { //If the file dont exists, create them
            try {
                FileWriter csvWriter = new FileWriter(ORDERS);
                csvWriter.flush();
                csvWriter.close();
                return orders; //return empty list because dont exist previous orders
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileNotFoundException("Error intentando abrir el archivo de ordenes");
    }

    private static HashMap<Integer, List<Product>> getProductsLists() {
        BufferedReader productsReader = new BufferedReader((new FileReader(BUYED_PRODUCTS)));

        String row;
        String[] data;
        while ((row = productsReader.readLine()) != null) {
            data = row.split(",");

        }
    }

    public static void main(String[] args) {
        try {
            OrderDAO.getOrdersPlaced();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

 */
}

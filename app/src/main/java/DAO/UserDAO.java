package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ModeloGian.CommonUser;
import ModeloGian.Condition;
import ModeloGian.Discount;
import ModeloGian.Order;
import ModeloGian.Product;

public class UserDAO {
    //icn,password,names,lastname,birthdate,balance,condition,price calculator type, price calculator parameter
    private static final String USERS_PATH = System.getProperty("user.home") + File.separator + "comedor" + File.separator + "users.csv";

    public static List<CommonUser> registeredUsers(){
        /*
        try {
            BufferedReader orderReader = new BufferedReader(new FileReader(USERS_PATH));
            String row;
            String[] data = null;
            List<Product> orderProducts;
            while ((row = orderReader.readLine()) != null) {
                data = row.split(",");
                orderProducts = getProducts(data[0],)
                orders.add(new Order())
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

         */
        return null;
    }


}

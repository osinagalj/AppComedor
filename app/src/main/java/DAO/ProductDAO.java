package DAO;

import java.util.List;

import DataBase.Restaurant;
import Model.CommonUser;
import Model.Product;

public class ProductDAO {


    public static List<Product> getProducts(CommonUser user){
        return Restaurant.getInstance().getAvailableProducts(user);
    }

    public static Product getProductById(int id){
        return Restaurant.getInstance().getProduct(id);
    }
    public static void loadProduct(){

    }
    public static void removeProduct(){

    }

}

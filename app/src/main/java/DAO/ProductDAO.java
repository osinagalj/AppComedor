package DAO;

import java.util.List;

import DataBase.Restaurant;
import Model.CommonUser;
import Model.Product;

public class ProductDAO {

    //@GET
    public static List<Product> getProducts(CommonUser user){
        return Restaurant.getInstance().getAvailableProducts(user);
    }

    //@GET
    public static Product getProductById(int id){
        return Restaurant.getInstance().getProduct(id);
    }

    //@POST
    public static void loadProduct(){ }

    //DELETE
    public static void removeProduct(){ }

}

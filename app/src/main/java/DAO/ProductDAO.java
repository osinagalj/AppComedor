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

    //@UPDATE
    public static boolean decreaseStock(int productId,int amount){
        return Restaurant.getInstance().decreaseStock(productId,amount);
    }

    //@UPDATE
    public static void increaseStock(int productId,int amount){
         Restaurant.getInstance().increaseStock(productId,amount);
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

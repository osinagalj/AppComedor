package DAO;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;
import ModeloGian.Product;
import ModeloGian.Food;

public class ProductDAO {
    public static List<Product> avalaibleProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Food(0001,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, 6, 88.0f, new ArrayList<>()));
        products.add(new Food(0002,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, 2, 85.0f, new ArrayList<>()));
        products.add(new Food(0003,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos,6, 20.2f, new ArrayList<>()));
        products.add(new Food(0004,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, 6, 20.2f, new ArrayList<>()));
        return products;
    }
}

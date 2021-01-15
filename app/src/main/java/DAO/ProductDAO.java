package DAO;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;
import ModeloGian.Product;
import ModeloGian.Food;
import ModeloGian.ProductCategory;

public class ProductDAO {
    public static List<Product> avalaibleProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Food(0001,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, ProductCategory.DAILY_MENU, 6, 88.0f, new ArrayList<>()));
        //TODO habria que hacer un random para el menu del dia, o algo asi, cosa que sea distinta cada dia

        products.add(new Food(0002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));
        products.add(new Food(0003,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        products.add(new Food(0004,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        products.add(new Food(0005,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        //TODO carga todos los productos vago
        return products;
    }

    public static int maxDailyMenus() {
        return 2;
    }
}

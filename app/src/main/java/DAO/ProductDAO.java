package DAO;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

import Model.CommonUser;
import Model.Food;
import Model.Product;
import Model.ProductCategory;

public class ProductDAO {

    public static List<Product> avalaibleProducts = new ArrayList<>();


    public static void getDailyMenu(){
        //Aplicar el descuento y los filtos de categoria

        avalaibleProducts.add((new Food(1001,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, ProductCategory.DAILY_MENU, 6, 88.0f, new ArrayList<>())));

        //TODO habria que hacer un random para el menu del dia, o algo asi, cosa que sea distinta cada dia
        //TODO Hay que hacer la logica para devolver un menu distinto dependeindo el tipo de usuario
    }

    public static void loadProducts(){

        getDailyMenu();

        //Buffet
        avalaibleProducts.add(new Food(1002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));
        avalaibleProducts.add(new Food(1003,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        avalaibleProducts.add(new Food(1007,"Cafe con leche", "Con queso", R.drawable.food_cafe_con_leche, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        avalaibleProducts.add(new Food(1008,"Pebete de JyQ","Con chips de chocolate", R.drawable.food_pebete_jyq, ProductCategory.BUFFET,6, 20.2f, new ArrayList<>()));

        //Kiosko
        avalaibleProducts.add(new Food(1006,"Galletitas 9 de oro","Con cebolla, morron y queso", R.drawable.food_9_de_oro_agridulce, ProductCategory.KIOSKO, 6, 88.0f, new ArrayList<>()));
        avalaibleProducts.add(new Food(1004,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        avalaibleProducts.add(new Food(1005,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        avalaibleProducts.add(new Food(1009,"Frutigram de chocolate","Rellenas de membrillo", R.drawable.food_frutigran_chocolate, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
     //TODO carga todos los productos vago

    }

    public static List<Product> avalaibleProducts(CommonUser user){
        return avalaibleProducts;//no romper encapsulamiento
    }


    public static int maxDailyMenus() {
        return 2;
    }

}

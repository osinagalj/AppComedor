import com.example.view.R;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import ModeloGian.Combo;
import ModeloGian.Food;
import ModeloGian.Product;
import ModeloGian.ProductCategory;

import static DAO.ProductDAO.avalaibleProducts;
import static DAO.ProductDAO.saveAvailableProducts;

public class ProductDAOTest extends TestCase {

    public void testAvalaibleProducts() {
        System.out.println(avalaibleProducts());
    }

    public void testSaveAvailableProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Food(0001,"Tarta de Pollo","Con cebolla morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));
        products.add(new Food(0002,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        products.add(new Food(0003,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        products.add(new Food(0004,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        List<Product> a = new ArrayList<>();
        a.add(products.get(2));
        a.add(products.get(3));
        products.add(new Combo(0005,"Alfajor y pepas","combo",R.drawable.food_alfajor_pepitos,ProductCategory.BUFFET,a,5));
        System.out.println(products);
        saveAvailableProducts(products);
    }
}
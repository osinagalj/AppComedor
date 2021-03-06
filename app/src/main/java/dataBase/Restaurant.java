package dataBase;

import com.example.view.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Category;
import model.Combo;
import model.CommonUser;
import model.DailyMenu;
import model.DiscountMax;
import model.FixedDiscount;
import model.Food;
import model.Order;
import model.PriceAntiquity;
import model.PriceFixedDiscount;
import model.PriceSubjects;
import model.Product;

public class Restaurant {

    private int id;
    private String name;
    private String university;
    private String timeTable;

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final int MAX_STOCK = 10000;
    public List<Integer> productsCategories = new ArrayList<>();
    private List<Product> products = new ArrayList<>(); //Para no pedir siempre los productos en la DB

    public static final Restaurant INSTANCE = new Restaurant();
    public static Restaurant getInstance() { return INSTANCE; }

    public void addProduct(Product product){
        if(!products.contains(product))
            products.add(product);
    }


    public List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }

    public Product getProduct(int id){
        for(Product p: products)
            if(p.getId() == id)
                return p;

        return null;
    }

    private Restaurant(){
        productsCategories.add(1); //Menu del Dia
        productsCategories.add(2); //Buffet
        productsCategories.add(3); //Kiosko
    }

    public void loadOrdersDB(){
        loadDataToDataBase();
        ProductDAO.increaseStock("1000",1,"foods");

        /*
        // Get a new write batch
        WriteBatch batch = db.batch();

// Set the value of 'NYC'
        DocumentReference nycRef = db.collection("cities").document("NYC");
        batch.set(nycRef, new City());


        List<Product> products = new ArrayList<>();
        for(Product p : products){
            DocumentReference sfRef = db.collection("foods").document(String.valueOf(p.getId()));
            batch.update(sfRef, "stock", 1000000L);
        }
// Update the population of 'SF'



// Commit the batch
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // ...
            }
        });
        */

    }


    private void loadDataToDataBase(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

     //todo Users
        CommonUser user1 = new CommonUser(111,"111",1200f,"Lautaro1", "Osinaga", date,0, Category.ALUMNO, new PriceFixedDiscount(0.6f));
        user1.setDiscountCalculator(new PriceFixedDiscount());

        CommonUser user2 = new CommonUser(222,"222",1200f,"Lautaro2", "Osinaga", date,1, Category.DOCENTE, new PriceSubjects(2));
        user2.addAttribute("subjects",2);
        CommonUser user3 = new CommonUser(333,"333",1200f,"Lautar3o", "Osinaga", date,2, Category.NO_DOCENTE, new PriceAntiquity(new Date()));
        user3.addAttribute("startDate",new Date());
        CommonUser user4 = new CommonUser(444,"444",1200f,"Lautaro3", "Osinaga", date,3, Category.EXTERNO, new PriceFixedDiscount(0));

        UserDAO.addUser(user1);
        UserDAO.addUser(user2);
        UserDAO.addUser(user3);
        UserDAO.addUser(user4);


        //todo dallyMenu

        //0 NONE,
        //1 VEGETARIAN,
        //2 VEGAN,
        //3 CELIAC
        //La alternativa es poner las condiciones que no pueden comer ese menu, pero es un poco mas complicado

        //Para los que no tienen nada
        DailyMenu m1 = new DailyMenu(100,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, 1,  MAX_STOCK, 100.0f, 2);
        m1.addCondition(1);m1.addCondition(2);m1.addCondition(3);

        //Para vegetariano
        DailyMenu m2 = new DailyMenu(101,"Milanesa con papas fritas","Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1, MAX_STOCK, 100.0f, 2);
        m2.addCondition(0);m2.addCondition(2);m2.addCondition(3);

        //Para vegano
        DailyMenu m3 =new DailyMenu(102,"Ensalada con papas fritas","Ensalada y papas McCain", R.drawable.food_carne_papas, 1,  MAX_STOCK, 100.0f, 2);
        m3.addCondition(0);m3.addCondition(1);m3.addCondition(3);

        //Para celiaco
        DailyMenu m4 =new DailyMenu(103,"Carne con papas fritas","sin TACC, de Berenjena y papas McCain", R.drawable.food_carne_papas, 1,  MAX_STOCK, 100.0f, 2);
        m4.addCondition(0);m4.addCondition(1);m4.addCondition(2);

        ProductDAO.loadProduct(m1);
        ProductDAO.loadProduct(m2);
        ProductDAO.loadProduct(m3);
        ProductDAO.loadProduct(m4);

    //TODO foods

        //Buffet
        Food f1 = new Food(1000,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo,2,6, 88.0f);
        f1.addCondition(3);
        Food f2 = new Food(1001,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, 2, 2, 85.0f);
        Food f3 = new Food(1002,"Cafe con leche", "Con queso", R.drawable.food_cafe_con_leche, 2, 2, 50.0f);
        Food f4 = new Food(1003,"Pebete de JyQ","Con chips de chocolate", R.drawable.food_pebete_jyq, 2,6, 65.0f);
        Food f5 = new Food(1004,"Pizza","Porcion de 200 g", R.drawable.food_porcion_pizza, 2,6, 20.0f);
        Food f6 = new Food(1005,"Tostado","de JyQ", R.drawable.food_tostado_jyq, 2,6, 45.0f);
        Food f7 = new Food(1006,"Coca-Cola 500 ml","Botella de Coca-Cola", R.drawable.food_botella_coca, 2,6, 70.0f);
        Food f8 = new Food(1007,"Empanada","De carne, cebolla y morron", R.drawable.food_empanada, 2,6, 15.0f);
        Food f9 = new Food(1008,"Vaso de Coca-Cola","200 ml", R.drawable.food_vaso_coca, 2,6, 30.0f);
        //Kiosko
        Food f10 = new Food(1009,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, 3,6, 50.0f);
        Food f11 = new Food(1010,"Galletitas 9 de oro","Agridulce", R.drawable.food_9_de_oro_agridulce, 3, 6, 105.0f);
        Food f12 = new Food(1011,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, 3,6, 110.0f);
        Food f13 = new Food(1012,"Frutigram de chocolate","Con chips de chocolate", R.drawable.food_frutigran_chocolate, 3,6, 90.0f);
        Food f14 = new Food(1013,"Pepas 9 de Oro","Rellenas con membrillo", R.drawable.food_pepas_9_de_oro, 3,6, 105.0f);
        Food f15 = new Food(1014,"Pepas chocotrio","Rellenas con membrillo recubiertas de chocolate", R.drawable.food_pepas_trio_chocotrio, 3,6, 110.0f);

        //Combo 1
        List<Product> promo1 = new ArrayList<>();
        promo1.add(f1);promo1.add(f9);promo1.add(f5);
        Combo combo1 = new Combo(2000,"Combo 1","Tarta de Pollo + Coca-Cola + Pizza",R.drawable.food_porcion_pizza,2,promo1,new FixedDiscount(0.3f));

        //Combo 2
        List<Product> promo2 = new ArrayList<>();
        promo2.add(f12);promo2.add(f14);promo2.add(f15);
        Combo combo2 = new Combo(2001,"Combo 2","Promo de pepas: Pepas trio + Pepas 9 de oro + Pepas chocotrio ",R.drawable.food_pepas_9_de_oro,3,promo2,new DiscountMax(200.0f,45.0f));


        ProductDAO.loadProduct(f1);
        ProductDAO.loadProduct(f2);
        ProductDAO.loadProduct(f3);
        ProductDAO.loadProduct(f4);
        ProductDAO.loadProduct(f5);
        ProductDAO.loadProduct(f6);
        ProductDAO.loadProduct(f7);
        ProductDAO.loadProduct(f8);
        ProductDAO.loadProduct(f9);
        ProductDAO.loadProduct(f10);
        ProductDAO.loadProduct(f11);
        ProductDAO.loadProduct(f12);
        ProductDAO.loadProduct(f13);
        ProductDAO.loadProduct(f14);
        ProductDAO.loadProduct(f15);

        ProductDAO.loadProduct(combo1);
        ProductDAO.loadProduct(combo2);



    //TODO Orders

        Map<Product,Integer> products = new HashMap<>();
        products.put(f1,2);
        products.put(f2,1);
        products.put(f3,1);

        Map<Product,Integer> products2 = new HashMap<>();
        products2.put(f4,5);
        products2.put(f2,3);
        products2.put(f8,2);

        Order order1 = new Order(1,user1);
        order1.addProduct(f11,2,false);
        order1.addProduct(f12,1,false);
        order1.addProduct(f13,4,false);

        OrderDAO.loadOrder(order1,false);

    }

}

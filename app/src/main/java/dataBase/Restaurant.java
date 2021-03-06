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
import model.Food;
import model.Menu;
import model.Order;
import model.Product;

public class Restaurant {

    private int id;
    private String name;
    private String university;
    private String timeTable;

    private int nextOrderNumber = 10000;
    private final List<CommonUser> registeredUsers = new ArrayList<>();
    private final List<Product> availableProducts = new ArrayList<>();
    final List<Order> orders = new ArrayList<>();
    final List<Order> ordersCompleted = new ArrayList<>();
    private final List<Menu> menus = new ArrayList<>();

    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    public List<Integer> productsCategories = new ArrayList<>();

    private final int MAX_STOCK = 10000;

    public static final Restaurant INSTANCE = new Restaurant();

    public static Restaurant getInstance() { return INSTANCE; }


    private Restaurant(){

        productsCategories.add(1); //Menu del Dia
        productsCategories.add(2); //Buffet
        productsCategories.add(3); //Kiosko
    }

    public void loadOrdersDB(){

       // loadDataToDataBase();
    }




    public List<Order> getOrdersCompleted(CommonUser user){
        List<Order> user_orders = new ArrayList<>();
        for(Order order : ordersCompleted)
            if(order.getPlacedBy().getIdentityCardNumber() == user.getIdentityCardNumber())
                user_orders.add(order);
        return user_orders;
    }

    public Product getProduct(int id){
        return availableProducts.get(0);//TODO
    }
    public List<Order> getOrders(CommonUser user){
        return orders;
    }

    //Get next orders
    public List<String> getNextOrders(){

        List<String> next_orders = new ArrayList<>();
        int i = 0;
        while(i<20 && i < orders.size()){
            next_orders.add("#"+ String.valueOf(orders.get(i).getId()));
            i++;
        }
        return next_orders;
    }


    public Product getSpecialMenu(CommonUser user){

        System.out.println("TAMAÑO D EMENUS = " + menus.size());
        //Add the menu
        for(Menu menu: menus){
           // if(menu.getDate().equals(LocalDate.now())){ todo
                return menu.getMenu(user);
           // }
        }
        return null;
    }

    public void removeDailyFood(){
        for(Product p : availableProducts){
            if(p.getCategory() == 0)
                availableProducts.remove(p);
        }
    }
    //TODO no devolver los elementos que no cuenten con stock
    public List<Product> getAvailableProducts(CommonUser user){

        List<Product> products = new ArrayList<>();

        //Add the products
        for(Product p : availableProducts){
            if(p.getStock()> 0){
                products.add(p);
            }
        }

        return products;//todo
    }




    public boolean decreaseStock(int productId, int amount) {

        for (Product p : availableProducts) {
            if (p.getId() == productId) {
                if (p.decreaseStock(amount))
                    return true;
            }
        }
        return false;
    }

    public void increaseStock(int productId, int amount) {
        for (Product p : availableProducts) {
            if (p.getId() == productId) {
                p.addStock(amount);
            }
        }
    }


    //-------------------------------------------------------------------------------------------//
    //-----------------------------     Orders   ------------------------------------------------//
    //-------------------------------------------------------------------------------------------//

    /***
     * search the orders of a specific user
     //* @param user user to search orders
     * @return unmodifiable list with the orders of the user
     */
    public List<Order> getOrders2(CommonUser user){
        List<Order> userOrders = new ArrayList<>();
        for(Order order: orders)
            if (order.getPlacedBy().equals(user))
                userOrders.add(order);

        return Collections.unmodifiableList(userOrders);
    }

    /***
     * filter the products that a user cant consume
     //* @param user user who wants to know what he can consume
     * @return unmodifiable list with consumable products
     */
/*
    public List<Product> getUserConsumableProducts(CommonUser user){
        List<Product> consumableProducts = new ArrayList<>();
        for (Product product : availableProducts)
            if (user.canConsume(product))
                consumableProducts.add(product);
        return Collections.unmodifiableList(consumableProducts);
    }
*/


    public void addStock(int barcode,int stock){
        for(Product product : availableProducts){
            if(product.getId() == barcode){
                product.addStock(stock);
            }
        }
    }

    public List<Order> getPendingOrders(CommonUser u){
        List<Order> userPendingOrders = new ArrayList<>();
        if (registeredUsers.contains(u)) {
            for (Order pendingOrder : orders){
                if (pendingOrder.getPlacedBy().equals(u)){
                    userPendingOrders.add(pendingOrder);
                }
            }
        }
        return userPendingOrders;
    }

    /*
    public void addOrder(Order order){
        for (Product product : order.getItems()){
            availableProducts.get(availableProducts.indexOf(product)).decreaseStock(order.getAmount(product));
        }
        orders.add(order);
    }
    */

    public void addOrder(Order order){
        orders.add(order);
    }

    public int nextOrderNum() {
        nextOrderNumber++;
        return nextOrderNumber-1;
    }

    public void cancelPending(Order order) {
        orders.remove(order);
        order.getPlacedBy().addBalance(order.getPrice());
    }

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------            LOAD DATA    -----------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    public void removeOrders(){//Al no ser una base de datos, si no vacio las ordenes
        ordersCompleted.clear();
        orders.clear();
    }



    private void loadDataToDataBase(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

     //todo Users
        CommonUser user1 = new CommonUser(111,"111",1200f,"Lautaro1", "Osinaga", date,0, Category.ALUMNO);
        CommonUser user2 = new CommonUser(222,"222",1200f,"Lautaro2", "Osinaga", date,1, Category.ALUMNO);
        CommonUser user3 = new CommonUser(333,"333",1200f,"Lautar3o", "Osinaga", date,2, Category.ALUMNO);
        CommonUser user4 = new CommonUser(444,"444",1200f,"Lautaro3", "Osinaga", date,3, Category.ALUMNO);

        UserDAO.addUser2(user1);
        UserDAO.addUser2(user2);
        UserDAO.addUser2(user3);
        UserDAO.addUser2(user4);


        //todo dallyMenu

        //0 NONE,
        //1 VEGETARIAN,
        //2 VEGAN,
        //3 CELIAC
        //La alternativa es poner las condiciones que no pueden comer ese menu, pero es un poco mas complicado

        //Para los que no tienen nada
        DailyMenu m1 = new DailyMenu(100,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, 1,  MAX_STOCK, 88.0f, 2);
        m1.addCondition(0);

        //Para vegetariano
        DailyMenu m2 = new DailyMenu(101,"Milanesa con papas fritas","Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1, MAX_STOCK, 88.0f, 2);
        m2.addCondition(1);

        //Para vegano
        DailyMenu m3 =new DailyMenu(102,"Ensalada con papas fritas","Ensalada y papas McCain", R.drawable.food_carne_papas, 1,  MAX_STOCK, 88.0f, 2);
        m3.addCondition(2);

        //Para celiaco
        DailyMenu m4 =new DailyMenu(103,"Milanesa con papas fritas","Harina sin TACC, de Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1,  MAX_STOCK, 88.0f, 2);
        m4.addCondition(3);

        ProductDAO.loadProduct(m1);
        ProductDAO.loadProduct(m2);
        ProductDAO.loadProduct(m3);
        ProductDAO.loadProduct(m4);

    //TODO foods



        //Buffet
        Food f1 = new Food(1000,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo,2,6, 88.0f);
        Food f2 = new Food(1001,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, 2, 2, 85.0f);
        Food f3 = new Food(1002,"Cafe con leche", "Con queso", R.drawable.food_cafe_con_leche, 2, 2, 85.0f);
        Food f4 = new Food(1003,"Pebete de JyQ","Con chips de chocolate", R.drawable.food_pebete_jyq, 2,6, 20.2f);
        Food f5 = new Food(1004,"Pizza","Porcion de 200 g", R.drawable.food_porcion_pizza, 2,6, 20.2f);
        Food f6 = new Food(1005,"Tostado","de JyQ", R.drawable.food_tostado_jyq, 2,6, 20.2f);
        Food f7 = new Food(1006,"Coca-Cola 500 ml","Botella de Coca-Cola", R.drawable.food_botella_coca, 2,6, 20.2f);
        Food f8 = new Food(1007,"Empanada","De carne, cebolla y morron", R.drawable.food_empanada, 2,6, 20.2f);
        Food f9 = new Food(1008,"Vaso de Coca-Cola","200 ml", R.drawable.food_vaso_coca, 2,6, 20.2f);
        //Kiosko
        Food f10 = new Food(1009,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, 3,6, 20.2f);
        Food f11 = new Food(1010,"Galletitas 9 de oro","Agridulce", R.drawable.food_9_de_oro_agridulce, 3, 6, 88.0f);
        Food f12 = new Food(1011,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, 3,6, 20.2f);
        Food f13 = new Food(1012,"Frutigram de chocolate","Con chips de chocolate", R.drawable.food_frutigran_chocolate, 3,6, 20.2f);
        Food f14 = new Food(1013,"Pepas 9 de Oro","Rellenas con membrillo", R.drawable.food_pepas_9_de_oro, 3,6, 20.2f);
        Food f15 = new Food(1014,"Pepas chocotrio","Rellenas con membrillo recubiertas de chocolate", R.drawable.food_pepas_trio_chocotrio, 3,6, 20.2f);

        //Combos
        List<Product> promo1 = new ArrayList<>();
        promo1.add(f1);promo1.add(f9);promo1.add(f5);
        Combo combo1 = new Combo(2000,"Combo1","Tarta de Pollo + Coca-Cola + Pizza",R.drawable.food_porcion_pizza,2,promo1,0.3f);

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






    //TODO Orders

        Map<Product,Integer> products = new HashMap<>();
        products.put(f1,2);
        products.put(f2,1);
        products.put(f3,1);

        Map<Product,Integer> products2 = new HashMap<>();
        products2.put(f4,5);
        products2.put(f2,3);
        products2.put(f8,2);

        Order order1 = new Order(3,user1,products);
        Order order2 = new Order(1,user1,products2);
        Order order3 = new Order(4,user2,products);
        Order order4 = new Order(2,user2,products2);

        OrderDAO.loadPendingOrder2(order1);
        OrderDAO.loadPendingOrder2(order3);

        OrderDAO.loadConfirmedOrder(order2);
        OrderDAO.loadConfirmedOrder(order4);

    }

}

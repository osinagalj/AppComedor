package dataBase;

import com.example.view.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dao.ProductDAO;
import model.Category;
import model.CommonUser;
import model.Condition;
import model.DailyMenu;
import model.Food;
import model.Menu;
import model.Order;
import model.PriceAntiquity;
import model.PriceExternal;
import model.PriceProfessor;
import model.PriceStudent;
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

    public List<Integer> productsCategories = new ArrayList<>(); //TODO Capaz esto tiene uqe ser int y despues hacer el mapeo. Preguntar



    private final int MAX_STOCK = 10000;

    public static final Restaurant INSTANCE = new Restaurant();

    public static Restaurant getInstance() { return INSTANCE; }


    private Restaurant(){
        loadUsers();
        //loadProducts();
        loadMenus();

        productsCategories.add(1); //Menu del Dia
        productsCategories.add(2); //Buffet
        productsCategories.add(3); //Kiosko
    }

    public void loadOrdersDB(){
//        loadOrders(); //TODO hay que cargar las despues porque se rompe sino al no estar creada la instancia y querer acceder al proximo numero de orden
       // loadProducts();
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
            if(menu.getDate().equals(LocalDate.now())){
                return menu.getMenu(user);
            }
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


    //-------------------------------------------------------------------------------------------//
    //-----------------------------      User    ------------------------------------------------//
    //-------------------------------------------------------------------------------------------//

    /***
     * check if a user is registered in the system
     * @param icn icn to validate
     * @param password password to validad
     * @return a CommonUser instance for which the icn was entered or null in case the user doesnt exist
     */
    public CommonUser validateLoginData(int icn, String password){
        for (CommonUser user : registeredUsers){
            if (user.getIdentityCardNumber() == icn && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }


    public boolean addUser(CommonUser user){
        if (user == null || isRegistered(user)){
            return false;
        }
        else {
            registeredUsers.add(user);
            System.out.println(registeredUsers);
            return true;
        }
    }

    public boolean removeUser(int icn){
        return registeredUsers.removeIf(registeredUser -> registeredUser.getIdentityCardNumber() == icn);
    }

    public boolean isRegistered(CommonUser user){
        for (CommonUser registeredUser : registeredUsers) {
            if (registeredUser.equals(user))
                return true;
        }
        return false;
    }

    public boolean isRegistered(int icn){
        for (CommonUser registeredUser : registeredUsers) {
            if (registeredUser.getIdentityCardNumber() == icn)
                return true;
        }
        return false;
    }

    public boolean loadMoney(int icn, float amount){
        for (CommonUser user : registeredUsers) {
            if (user.getIdentityCardNumber() == icn){
                user.addBalance(amount);
                return true;
            }
        }
        return false;
    }

    public boolean changePassword(int icn, String new_password){
        for (CommonUser user : registeredUsers) {
            if (user.getIdentityCardNumber() == icn){
                user.setPassword(new_password);
                return true;
            }
        }
        return false;
    }

    //-------------------------------------------------------------------------------------------//
    //-----------------------------   Products   ------------------------------------------------//
    //-------------------------------------------------------------------------------------------//
    public boolean addProduct(Product product){
        if (product != null && existingProduct(product.getId())){
            return false;
        }
        else {
            return availableProducts.add(product);
        }
    }

    public boolean removeFood(int barcode){
        return availableProducts.removeIf(product -> product.getId() == (barcode));
    }

    public boolean existingProduct(int barcode){
        for (Product product : availableProducts) {
            if (product.getId() == barcode)
                return true;
        }
        return false;
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

    private void loadOrders(){
        HashMap<Product,Integer> products = new HashMap<>();
        products.put(availableProducts.get(0),4);

        //Completed orders
        ordersCompleted.add( new Order(registeredUsers.get(0),products,new HashMap<>()));
        ordersCompleted.add(new Order(registeredUsers.get(0),products,new HashMap<>()));

        //Pending orders
        orders.add( new Order(registeredUsers.get(1),products,new HashMap<>()));
        orders.add( new Order(registeredUsers.get(0),products,new HashMap<>()));
        orders.add( new Order(registeredUsers.get(2),products,new HashMap<>()));

    }

    public void loadUsers(){

        registeredUsers.add(new CommonUser(111,"111",1200f,"Lautaro", "Osinaga", LocalDate.of(1999,5,20), Condition.NONE, Category.ALUMNO,new PriceStudent(0.6f)));
        registeredUsers.add(new CommonUser(222,"222",700f,"Gian", "Capozzo", LocalDate.of(1999,5,20), Condition.CELIAC, Category.DOCENTE,new PriceProfessor(6)));
        registeredUsers.add(new CommonUser(333,"333",800f,"Juan", "Perez", LocalDate.of(1999,5,20), Condition.VEGETARIAN, Category.NO_DOCENTE,new PriceAntiquity(LocalDate.now())));
        registeredUsers.add(new CommonUser(444,"444",800f,"Leo", "Messi", LocalDate.of(1999,5,20), Condition.VEGAN, Category.EXTERNO,new PriceExternal()));
    }

    private void loadProducts(){

        ProductDAO.loadProduct(new Food(1009,"Pizza","Porcion de 200 g", R.drawable.food_porcion_pizza, 2,6, 20.2f ));

        ProductDAO.loadProduct(new Food(1009,"Vaso de Coca-Cola","200 ml", R.drawable.food_vaso_coca, 2,6, 20.2f));

        //Buffet
        ProductDAO.loadProduct(new Food(1002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo,2,6, 88.0f));
        ProductDAO.loadProduct(new Food(1003,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, 2, 2, 85.0f));
        ProductDAO.loadProduct(new Food(1007,"Cafe con leche", "Con queso", R.drawable.food_cafe_con_leche, 2, 2, 85.0f));
        ProductDAO.loadProduct(new Food(1008,"Pebete de JyQ","Con chips de chocolate", R.drawable.food_pebete_jyq, 2,6, 20.2f));
        ProductDAO.loadProduct(new Food(1009,"Pizza","Porcion de 200 g", R.drawable.food_porcion_pizza, 2,6, 20.2f));
        ProductDAO.loadProduct(new Food(1009,"Tostado","de JyQ", R.drawable.food_tostado_jyq, 2,6, 20.2f));

        ProductDAO.loadProduct(new Food(1009,"Coca-Cola 500 ml","Botella de Coca-Cola", R.drawable.food_botella_coca, 2,6, 20.2f));
        ProductDAO.loadProduct(new Food(1009,"Empanada","De carne, cebolla y morron", R.drawable.food_empanada, 2,6, 20.2f));

        //List<Product> promo1 = new ArrayList<>();
        //promo1.add(f1);
        //promo1.add(f1);
        //promo1.add(f2);

        //availableProducts.add(new Combo(123,"Combo1","2 porciones de pizza + un vaso de Coca-Cola",R.drawable.food_porcion_pizza,2,promo1,0.3f));

        //Kiosko
        ProductDAO.loadProduct(new Food(1004,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, 3,6, 20.2f));
        ProductDAO.loadProduct(new Food(1006,"Galletitas 9 de oro","Agridulce", R.drawable.food_9_de_oro_agridulce, 3, 6, 88.0f));
        ProductDAO.loadProduct(new Food(1005,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, 3,6, 20.2f));
        ProductDAO.loadProduct(new Food(1009,"Frutigram de chocolate","Con chips de chocolate", R.drawable.food_frutigran_chocolate, 3,6, 20.2f));
        ProductDAO.loadProduct(new Food(1009,"Pepas 9 de Oro","Rellenas con membrillo", R.drawable.food_pepas_9_de_oro, 3,6, 20.2f));
        ProductDAO.loadProduct(new Food(1009,"Pepas chocotrio","Rellenas con membrillo recubiertas de chocolate", R.drawable.food_pepas_trio_chocotrio, 3,6, 20.2f));

    }

    private void loadMenus() {

        Menu menu = new Menu(LocalDate.now());
        menu.add(new DailyMenu(1001,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, 1,  MAX_STOCK, 88.0f, 2));
        menu.add(new DailyMenu(1001,"Milanesa con papas fritas","Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1, MAX_STOCK, 88.0f, 2));
        menu.add(new DailyMenu(1001,"Carne con papas fritas","Carne vacuna y papas McCain", R.drawable.food_carne_papas, 1,  MAX_STOCK, 88.0f, 2));
        menu.add(new DailyMenu(1001,"Milanesa con papas fritas","Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1,  MAX_STOCK, 88.0f, 2));

        Menu menu2 = new Menu(LocalDate.of(2021,1,13));
        menu.add(new DailyMenu(1001,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, 1, MAX_STOCK, 88.0f, 2));
        menu.add(new DailyMenu(1001,"Milanesa con papas fritas","Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1, MAX_STOCK, 88.0f, 2));
        menu.add(new DailyMenu(1001,"Carne con papas fritas","Carne vacuna y papas McCain", R.drawable.food_carne_papas, 1, MAX_STOCK, 88.0f, 2));
        menu.add(new DailyMenu(1001,"Milanesa con papas fritas","Berenjena y papas McCain", R.drawable.food_milanesas_con_fritas, 1, MAX_STOCK, 88.0f, 2));

        menus.add(menu);
        menus.add(menu2);

    }
}

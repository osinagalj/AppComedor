package DataBase;

import com.example.view.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import DAO.UserDAO;
import Model.Category;
import Model.CommonUser;
import Model.Condition;
import Model.Discount;
import Model.Food;
import Model.Order;
import Model.Product;
import Model.ProductCategory;

public class Restaurant {

    private int id;
    private String name;
    private String university;
    private String timeTable;

    public static final int LAST_ORDER_NUMBER = 100000;

    private int nextOrderNumber = 2;
    private final List<CommonUser> registeredUsers = new ArrayList<>();
    private final List<Product> availableProducts = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Condition> conditions = UserDAO.usersConditions();

    public static final Restaurant INSTANCE = new Restaurant();
    public static Restaurant getInstance() { return INSTANCE; }

    private Restaurant(){
        loadOrders();
        loadProducts();
        loadUsers();
    }


    public Product getProduct(int id){
        return availableProducts.get(0);//TODO
    }
    public List<Order> getOrders(CommonUser user){
        return orders;
    }

    //Get next orders
    public List<Order> getNextOrders(){
        //TODO obtener las proximas 20 ordenes
        //TODO las proximas ordenes para mostrar en FILA
        return orders;
    }

    public void changePassword(int dni,String password){
        //todo
    }

    public List<Product> getAvailableProducts(CommonUser user){
        return availableProducts;//TODO
    }
    public void getDailyMenu(CommonUser user){
        //TODO



    }



    public CommonUser getUser(int dni, String password){
        return registeredUsers.get(0);//TODO si estan mal la password y dni entonces retorna null, si en la vista retorna null muestra el mensaje de error
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

    public List<Product> getUserConsumableProducts(CommonUser user){
        List<Product> consumableProducts = new ArrayList<>();
        for (Product product : availableProducts)
            if (user.canConsume(product))
                consumableProducts.add(product);
        return Collections.unmodifiableList(consumableProducts);
    }

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


    public Condition getCondition(String conditionName) {
        for (Condition condition : conditions){
            if (condition.getName().equals(conditionName)){
                return condition;
            }
        }
        return null;
    }

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------            LOAD DATA    -----------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    private void loadOrders(){

    }

    public void loadUsers(){
        CommonUser user = new CommonUser("111","Lautaro", "Osinaga", LocalDate.of(1999,5,20), 111,new Condition("Celiaco",new HashSet<>()), Category.ALUMNO,new Discount(10));
        user.setBalance(1000);
        registeredUsers.add(user);
    }

    private void loadProducts(){


        Food f = new Food(1001,"Milanesa con papas fritas","Carne vacuna y papas McCain", R.drawable.food_milanesas_con_fritas, ProductCategory.DAILY_MENU, 6, 88.0f, new ArrayList<>());
        availableProducts.add(f);

        //Buffet
        availableProducts.add(new Food(1002,"Tarta de Pollo","Con cebolla, morron y queso", R.drawable.food_tarta_pollo, ProductCategory.BUFFET, 6, 88.0f, new ArrayList<>()));
        availableProducts.add(new Food(1003,"Tarta de Calabaza", "Con queso", R.drawable.food_tarta_calabaza, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        availableProducts.add(new Food(1007,"Cafe con leche", "Con queso", R.drawable.food_cafe_con_leche, ProductCategory.BUFFET, 2, 85.0f, new ArrayList<>()));
        availableProducts.add(new Food(1008,"Pebete de JyQ","Con chips de chocolate", R.drawable.food_pebete_jyq, ProductCategory.BUFFET,6, 20.2f, new ArrayList<>()));

        //Kiosko
        availableProducts.add(new Food(1006,"Galletitas 9 de oro","Con cebolla, morron y queso", R.drawable.food_9_de_oro_agridulce, ProductCategory.KIOSKO, 6, 88.0f, new ArrayList<>()));
        availableProducts.add(new Food(1004,"Alfajor Pepitos","Con chips de chocolate", R.drawable.food_alfajor_pepitos, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        availableProducts.add(new Food(1005,"Pepas trio","Rellenas de membrillo", R.drawable.food_pepas_trio, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        availableProducts.add(new Food(1009,"Frutigram de chocolate","Rellenas de membrillo", R.drawable.food_frutigran_chocolate, ProductCategory.KIOSKO,6, 20.2f, new ArrayList<>()));
        //TODO carga todos los productos vago
    }
}

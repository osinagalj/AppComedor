package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DAO.ProductDAO;
import DAO.UserDAO;

public class Restaurant {
    public static final int MAX_SPECIAL_ORDERS = ProductDAO.maxDailyMenus();

    private int id;
    private String name;
    private String university;
    private String timeTable;
    private final List<CommonUser> registeredUsers = UserDAO.registeredUsers();
    private final List<Product> availableProducts = ProductDAO.avalaibleProducts();
    private final List<Condition> conditions = UserDAO.usersConditions();
    private final List<Order> orders = new ArrayList<>();
    private final List<Order> pendingOrders = new ArrayList<>();
    public static final Restaurant INSTANCE = new Restaurant();

    //para testeo
    public ArrayList<Order> ordenesPendientes = new ArrayList<>();

    private Restaurant(){}

    public static Restaurant getInstance() { return INSTANCE; }

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
        return availableProducts.removeIf(product -> product.getId() == (id));
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
    public List<Order> getOrders(CommonUser user){
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
/*
    public void addStock(int barcode,int stock){
        for(Product product : availableProducts){
            if(product.getId() == barcode){
                product.addStock(stock);
            }
        }
    }
*/
    public void getPendingOrders(CommonUser u){
        if (registeredUsers.contains(u)) {
            //users.add(u);
        }
    }

    public void addOrder(Order order){

    }


    public Condition getCondition(String conditionName) {
        for (Condition condition : conditions){
            if (condition.getName().equals(conditionName)){
                return condition;
            }
        }
        return null;
    }
}

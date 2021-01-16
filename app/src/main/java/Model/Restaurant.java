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
    private final List<Order> orders = new ArrayList<>();
    private final List<Order> pendingOrders = new ArrayList<>();
    public static final Restaurant INSTANCE = new Restaurant();

    //para testeo
    public ArrayList<Product> miOrden = new ArrayList<>(); //todo esto no va aca claramente
    public ArrayList<Order> ordenesPendientes = new ArrayList<>();

    public ArrayList<Order> getOrdenesPendientes(){
        return ordenesPendientes;
    }
    public void addOrderPend(Order o){
        this.ordenesPendientes.add(o);
    }

    private Restaurant(){}

    public static Restaurant getInstance() { return INSTANCE; }

    public boolean addUser(CommonUser user){
        if (user != null && !isRegistered(user)){
            return false;
        }
        else {
            return registeredUsers.add(user);
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
     * @param user user to search orders
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
     * @param user user who wants to know what he can consume
     * @return unmodifiable list with consumable products
     */
    public List<Product> getUserConsumableProducts(CommonUser user){
        List<Product> consumableProducts = new ArrayList<>();
        for (Product product : availableProducts)
            if (user.canConsume(product))
                consumableProducts.add(product);
        return Collections.unmodifiableList(consumableProducts);
    }

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

    public void getPendingOrders(CommonUser u){
        if (registeredUsers.contains(u)) {
            //users.add(u);
        }
    }

    public void addOrder(Order order){

    }
}

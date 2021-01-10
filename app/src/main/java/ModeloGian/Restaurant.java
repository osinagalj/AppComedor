package ModeloGian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private String university;
    private String timeTable;
    private final ArrayList<CommonUser> registeredUsers = new ArrayList<>();
    private final ArrayList<Product> availableProducts = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();
    private final ArrayList<Order> pendingOrders = new ArrayList<>();
    public static final Restaurant INSTANCE = new Restaurant();

    private Restaurant(){}

    public static Restaurant getInstance() { return INSTANCE; }

    public boolean addUser(CommonUser user){
        if (user != null && isRegistered(user.getUsername())){
            return false;
        }
        else {
            return registeredUsers.add(user);
        }
    }
/*
    public boolean removeUser(String username){
        return registeredUsers.removeIf(registeredUser -> registeredUser.getUsername().equals(username));
    }
*/
    public boolean isRegistered(String username){
        for (CommonUser registeredUser : registeredUsers) {
            if (registeredUser.getUsername().equals(username))
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
/*
    public boolean removeFood(int barcode){
        return availableProducts.removeIf(product -> product.getId() == (id));
    }
*/
    public boolean existingProduct(int barcode){
        for (Product product : availableProducts) {
            if (product.getId() == barcode)
                return true;
        }
        return false;
    }

    /***
     * search the orders of a specific user
     * @param username username to search orders
     * @return unmodifiable list with the orders of the user
     */
    public List<Order> getOrders(String username){
        List<Order> userOrders = new ArrayList<>();
        for(Order order: orders)
            if (order.getPlacedBy().getUsername().equals(username))
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
}

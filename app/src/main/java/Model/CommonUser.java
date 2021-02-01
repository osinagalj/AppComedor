package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommonUser implements Serializable {
    private String password;
    private String names;
    private String lastname;
    private LocalDate birthdate;
    private int identityCardNumber;
    private float balance;
    private Condition condition;
    private Category category; //Esto probablemente lo borre
    private PriceCalculator priceCalculator;
    private List<Order> completedOrders; //no se si va
    private int dailySpecialRemaining; //se consulta a la base esto

    private float cartAmount = 0; // ??????????????Variable para no realizar el calculo en cada producto que se agrega
    //TODO falta una imagen del perfil

    public CommonUser(int identityCardNumber,String password, float balance, String names, String lastname, LocalDate birthdate,  Condition condition,Category category, PriceCalculator priceCalculator) {
        this.password = password;
        this.names = names;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.identityCardNumber = identityCardNumber;
        this.category = category;
        this.condition = condition;
        this.priceCalculator = priceCalculator;

        this.balance = balance;
        this.completedOrders = new ArrayList<>();
        this.dailySpecialRemaining = 2; //todo
    }

    /***
     * recalculates the order total and confirms the order if
     * sufficient balance and stock is available
     * @return true or false whether the order could be confirmed or not
     */
    /*
    public boolean confirmOrder(){ //todo ESTO NO VA ACA TAMPOCO
        float updatedCartPrice = 0;
        for (Product product: cart.keySet()){
            updatedCartPrice+=product.getPrice()*cart.get(product);
            if (product.getStock() < cart.get(product)){
                return false;
            }
        }
        if (updatedCartPrice > getBalance()){
            return false;
        }
        balance-=updatedCartPrice;
        Order newOrder = new Order(this,cart,new HashMap<>());
        Restaurant.getInstance().addOrder(newOrder);
        cart = new HashMap<>();
        cartAmount = 0;
        return true;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(int identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getConditionName() {
        return condition.getName();
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public int getDailySpecialRemaining() {
        return dailySpecialRemaining;
    }

    public void setDailySpecialRemaining(int dailySpecialRemaining) {
        this.dailySpecialRemaining = dailySpecialRemaining;
    }

    public boolean canConsume(Product product){
        return condition.canConsume(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonUser)) return false;
        CommonUser that = (CommonUser) o;
        return Objects.equals(identityCardNumber, that.identityCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityCardNumber);
    }

    /*public boolean cartIsEmpty() {
        return cart.isEmpty();
    }
*/
    /*
    public boolean addProductToCart(Product product, int qty){
        if (getCartTotalPrice() + product.getPrice()*qty > balance){
            return false;
        }
        else {
            if (cart.containsKey(product))
                cart.put(product,cart.get(product)+qty);
            else
                cart.put(product,qty);
            cartAmount+=product.getPrice()*qty;
        }
        return true;
    }

    public List<Product> getCartProducts() {
        return Collections.unmodifiableList(new ArrayList<>(cart.keySet()));
    }

    public void clearCart() {
        cart = new HashMap<>();
        cartAmount = 0;
    }

    public float getCartTotalPrice(){
        return cartAmount;
    }

    public int getCartProductAmount(Product product) {
        if (product != null)
            return cart.get(product);
        return 0;
    }
*/
    public List<Order> getConfirmedOrders(){
        return Collections.unmodifiableList(completedOrders);
    }

    public boolean addConfirmedOrder(Order order){
        return completedOrders.add(order);
    }
/*
    public void removeCartProduct(Product product) {
        cart.remove(product);
    }
*/
    //todo price puede ser negativo, usando cuando se hace una transferencia y se quiere restar plata al usuario
    public void addBalance(float price) {
        balance+=price;
    }
}


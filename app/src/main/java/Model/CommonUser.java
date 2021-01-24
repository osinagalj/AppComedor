package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<Order> completedOrders;
    private int dailySpecialRemaining;
    //TODO falta una imagen del perfil

    public CommonUser(String password, String names, String lastname, LocalDate birthdate, int identityCardNumber, Condition condition, PriceCalculator priceCalculator) {
        this.password = password;
        this.names = names;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.identityCardNumber = identityCardNumber;
        this.condition = condition;
        this.priceCalculator = priceCalculator;

        this.balance = 0;
        this.completedOrders = new ArrayList<>();
        this.dailySpecialRemaining = Restaurant.MAX_SPECIAL_ORDERS;
    }

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

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(List<Order> completedOrders) {
        this.completedOrders = completedOrders;
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
}

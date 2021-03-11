package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommonUser implements Serializable,Cloneable {
    private String password;
    private String names;
    private String lastName;
    private Date birthDate;
    private int identityCardNumber;
    private float balance;
    private int condition;
    private Category category;
    private Map<String,Object> attributes = new HashMap<>();
    private PriceCalculator priceCalculator;

    public CommonUser(){}

    public CommonUser(int identityCardNumber,String password, float balance, String names, String lastName, Date birthDate,  int condition, Category category) {
        this.password = password;
        this.names = names;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.identityCardNumber = identityCardNumber;
        this.category = category;
        this.condition = condition;
        this.balance = balance;
    }

    public CommonUser(int identityCardNumber,String password, float balance, String names, String lastName, Date birthDate,  int condition, Category category, PriceCalculator priceCalculator) {
        this.password = password;
        this.names = names;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.identityCardNumber = identityCardNumber;
        this.category = category;
        this.condition = condition;
        this.priceCalculator = priceCalculator;
        this.balance = balance;
    }

    /**
     * @param price base price to which the price is adjusted
     * @return the adjusted price
     */
    public float getPrice(float price){
        return priceCalculator.getPrice(price);
    }

    /**Si la condicion del usuario esta en la lista de condiciones, entonces no puede consumir el producto*/
    public boolean canConsume(List<Integer> conditions){
        return !conditions.contains(this.condition);
    }

    /**
     * add a dynamic attribute
     * @param key of attribute
     * @param value of the attribute
     */
    public void addAttribute(String key, Object value){
        attributes.put(key,value);
    }

    /**
     * @param key of the attribute is searched
     * @return the value of the attribute if the key exists, null if the map not contains the key
     */
    public Object getAttribute(String key){
        return attributes.get(key);
    }

    /**
     * increase the user's balance, negative values decrease the balance
     * @param price amount to be increased, negative number to decrease
     */
    public void addBalance(float price) {
        balance+=price;
    } //price puede ser negativo, usado cuando se hace una transferencia y se quiere restar plata al usuario

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

    //---------------------------------------------------------------------------------------------//
    //------------------------------ Getters && Setters -------------------------------------------//
    //---------------------------------------------------------------------------------------------//
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public int getConditionName() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getCondition() {
        return condition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PriceCalculator getDiscountCalculator() {
        return (PriceCalculator) priceCalculator.clone();
    }

    public void setDiscountCalculator(PriceCalculator discountCalculator) {
        this.priceCalculator = discountCalculator;
    }

}


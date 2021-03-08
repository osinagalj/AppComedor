package model;


import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@IgnoreExtraProperties
public class CommonUser  implements Serializable {
    private String password;
    private String names;
    private String lastName;
    private Date birthDate;
    private int identityCardNumber;
    private float balance;
    private int condition;
    private Category category;

    private HashMap<String,Object> attributes = new HashMap<>();

    @Exclude
    private PriceCalculator discountCalculator;

    public float getPrice(float price){
        return discountCalculator.getPrice(price);
    }

    public CommonUser(){}

    public CommonUser(int identityCardNumber,String password, float balance, String names, String lastName, Date birthDate,  int condition, Category category) {
        this.password = password;
        this.names = names;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.identityCardNumber = identityCardNumber;
        this.category = category;
        this.condition = condition;
       // this.discountCalculator = discountCalculator;
        this.balance = balance;
    }


    public void addAttribute(String key, Object value){
        attributes.put(key,value);
    }
    public Object getAttribute(String key){
        return attributes.get(key);
    }


    public float getPrice(PriceCalculator calculator, float price){
        return calculator.getPrice(price);
    }

    public void addBalance(float price) {
        balance+=price;
    }//price puede ser negativo, usado cuando se hace una transferencia y se quiere restar plata al usuario


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
    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
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

    @Exclude
    public PriceCalculator getDiscountCalculator() {
        return discountCalculator;
    }

    @Exclude
    public void setDiscountCalculator(PriceCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

}


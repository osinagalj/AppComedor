package dataBase.model;

import java.util.Date;
import java.util.HashMap;

import model.Category;
import model.CommonUser;
import model.PriceFixedDiscount;

public class UserDB {

    private String password;
    private String names;
    private String lastName;
    private Date birthDate;
    private int identityCardNumber;
    private float balance;
    private int condition;
    private Category category;

    private HashMap<String,Object> attributes = new HashMap<>();

    public UserDB(){}

    public UserDB(CommonUser user) {
        this.password = user.getPassword();
        this.names = user.getNames();
        this.lastName = user.getLastName();
        this.birthDate = user.getBirthDate();
        this.identityCardNumber = user.getIdentityCardNumber();
        this.category = user.getCategory();
        this.condition = user.getCondition();
        this.balance = user.getBalance();
    }

    public CommonUser convertToModel(){
        CommonUser user = new CommonUser(
                this.identityCardNumber,
                this.password,
                this.balance,
                this.names,
                this.lastName,
                this.birthDate,
                this.condition,
                this.category
        );
        user.setDiscountCalculator(new PriceFixedDiscount(0.5f)); //todo
        //user.addAttribute(); todo add all atributes
        return user;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

}

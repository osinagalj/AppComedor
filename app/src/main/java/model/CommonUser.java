package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CommonUser implements Serializable {
    private String password;
    private String names;
    private String lastname;
    private LocalDate birthdate;
    private int identityCardNumber = -1;
    private float balance;
    private Condition condition = Condition.NONE;
    private Category category = Category.ALUMNO; //Este enumerado se justifica porque no va a ser posible agregar mas categorias, por lo que no tiene que ser dinamico
    private PriceCalculator discountCalculator;



    //todo falta la cantidad de materias y la fecha de inicio para guardar en la database

    private int dailySpecialRemaining; //se consulta a la base esto

    //TODO falta una imagen del perfil

    public CommonUser(){}

    public CommonUser(int identityCardNumber,String password, float balance, String names, String lastname, LocalDate birthdate,  Condition condition,Category category, PriceCalculator discountCalculator) {
        this.password = password;
        this.names = names;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.identityCardNumber = identityCardNumber;
        this.category = category;
        this.condition = condition;
        this.discountCalculator = discountCalculator;
        this.balance = balance;
        this.dailySpecialRemaining = 2; //todo
    }

    public float getPrice(DailyMenu product){
        return discountCalculator.getPrice(this,product);
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

    public Condition getConditionName() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public PriceCalculator getDiscountCalculator() {
        return discountCalculator;
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

    public void setDiscountCalculator(PriceCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public int getDailySpecialRemaining() {
        return dailySpecialRemaining;
    }

    public void setDailySpecialRemaining(int dailySpecialRemaining) {
        this.dailySpecialRemaining = dailySpecialRemaining;
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


    //todo price puede ser negativo, usando cuando se hace una transferencia y se quiere restar plata al usuario
    public void addBalance(float price) {
        balance+=price;
    }
}


package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class CommonUser implements Serializable {
    private String password;
    private String names;
    private String lastname;
    private Date birthdate;
    private int identityCardNumber = -1;
    private float balance;
    private int condition = 0; // por defecto pueden comer todo
    private Category category = Category.ALUMNO; //Este enumerado se justifica porque no va a ser posible agregar mas categorias, por lo que no tiene que ser dinamico

    public ArrayList<Integer> getCondi() {
        return condi;
    }

    public void setCondi(ArrayList<Integer> condi) {
        this.condi = condi;
    }

    //private PriceCalculator discountCalculator;
    private ArrayList<Integer> condi = new ArrayList<>();


    //todo falta la cantidad de materias y la fecha de inicio para guardar en la database

    //TODO falta una imagen del perfil

    public CommonUser(){}

    public CommonUser(int identityCardNumber,String password, float balance, String names, String lastName, Date birthDate,  int condition,Category category) {
        this.password = password;
        this.names = names;
        this.lastname = lastName;
        this.birthdate = birthDate;
        this.identityCardNumber = identityCardNumber;
        this.category = category;
        this.condition = condition;
       // this.discountCalculator = discountCalculator;
        this.balance = balance;
        condi.add(6);
    }



    public float getPrice(PriceCalculator calculator, float price){
        return calculator.getPrice(price);
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

    public int getConditionName() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

   /* public PriceCalculator getDiscountCalculator() {
        return discountCalculator;
    }
*/
    public int getCondition() {
        return condition;
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
    }
/*
    public void setDiscountCalculator(PriceCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }
*/


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


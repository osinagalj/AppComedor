package model;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class CommonUser extends model.User {

    private double balance;
    private Condition condition;
    private Vector<Order> myOrders;
    private UserType userType;

    public CommonUser(Condition condition, UserType userType, String password, String name, String lastname, int DNI){
        super(password, name, lastname, DNI);
        this.condition = condition;
        this.userType = userType;
        this.balance = 0;
        myOrders = new Vector<Order>();
    }

    public CommonUser(Condition condition, UserType userType, String password, String name, String lastname, int DNI, double balance, Vector<Order> orders){
        super(password, name, lastname, DNI);
        this.condition = condition;
        this.userType = userType;
        this.balance = balance;
        this.myOrders = orders;
    }

    public void loadBalance(double amount){
        this.setBalance(this.balance + amount);
    }
    public void addOrder(Order o){
        myOrders.add(o);
    }
    public int getSpecialOrdersOfToday(){
       Calendar today = Calendar.getInstance();
        int SpecialOrdersOfToday = 0;
        for(Order o: myOrders){
            if((o.getTime().get(Calendar.DATE) == today.get(Calendar.DATE)) && (o.getTime().get(Calendar.MONTH) == today.get(Calendar.MONTH)) && (o.getTime().get(Calendar.YEAR) == today.get(Calendar.YEAR)) ){
                SpecialOrdersOfToday += o.getSpecialOrders();
            }
        }
        return SpecialOrdersOfToday;
    }



    //Getters && Setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ArrayList<Order> getMyOrders() {
        ArrayList<Order> r_myOrders = new ArrayList<Order>();
        for(int i = 0; i < this.myOrders.size(); i++){
            r_myOrders.add(this.myOrders.elementAt(i));
        }
        return r_myOrders;
    }
}

package model;

import com.example.view.R;

import java.util.*;

public class Restaurant {

    public static Restaurant restaurant = new Restaurant(1,"Comedor Unicen","Paraje Arroyo Seco",900,2000);

    private static final int MAX_SPECIAL_ORDERS = 2;
    private int id;
    private String name;
    private String address;
    private int openTime;
    private int closeTime;
    private Vector<User> users = new Vector<User>();
    private Vector<Food> products = new Vector<Food>();;
    private Vector<Order> orders = new Vector<Order>();;
    private HashMap<Integer, Condition> usersConditions;


   public Restaurant(int id, String name, String address, int openTime, int closeTime){
       this.id = id;
       this.name = name;
       this.address = address;
       this.openTime = openTime;
       this.closeTime = closeTime;
       this.usersConditions = new HashMap<Integer, Condition>();

       loadProducts();
   }

   private void loadProducts(){
       products.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
       products.add(new Food(0002,"Name 1",6, 20.2f, new Vector<String>(), R.drawable.food_empanada));
       products.add(new Food(0003,"Name 2",6, 20.2f, new Vector<String>(), R.drawable.food_alfajor_pepitos));
       products.add(new Food(0004,"Name 3",6, 20.2f, new Vector<String>(), R.drawable.food_vaso_coca));
       products.add(new Food(0005,"Name 4",6, 20.2f, new Vector<String>(), R.drawable.food_pepas_trio));
       products.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
       products.add(new Food(0002,"Name 1",6, 20.2f, new Vector<String>(), R.drawable.food_empanada));
       products.add(new Food(0003,"Name 2",6, 20.2f, new Vector<String>(), R.drawable.food_alfajor_pepitos));
       products.add(new Food(0004,"Name 3",6, 20.2f, new Vector<String>(), R.drawable.food_vaso_coca));
       products.add(new Food(0005,"Name 4",6, 20.2f, new Vector<String>(), R.drawable.food_pepas_trio));
       products.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
       products.add(new Food(0002,"Name 1",6, 20.2f, new Vector<String>(), R.drawable.food_empanada));
       products.add(new Food(0003,"Name 2",6, 20.2f, new Vector<String>(), R.drawable.food_alfajor_pepitos));
       products.add(new Food(0004,"Name 3",6, 20.2f, new Vector<String>(), R.drawable.food_vaso_coca));
       products.add(new Food(0005,"Name 4",6, 20.2f, new Vector<String>(), R.drawable.food_pepas_trio));
   }
   //los listar irian en la interfaz usando el getProducts o getOrders

    public int getPendingOrders(){
       return orders.size();
    }
    public void addUser(User u){
        if(!this.users.contains(u)){
            users.add(u);
        }
    }
    public boolean checkUser(User u){
       if(users.contains(u)){
           return true;
       }
       else{
           return false;
       }
    }
    public void removeUser(User u){
        users.remove(u);
    }
    public void addFood(Food f){
        if(!this.products.contains(f)) {
            products.add(f);
        }
    }
    public void modifyPrice(int idFood, int newPrice){

    }
    public void addStock(int idFood,int stock){
        for(int i = 0; i < this.products.size(); i++){
            if(this.products.elementAt(i).getBarcode() == idFood){
                this.products.elementAt(i).setStock(this.products.elementAt(i).getStock() + stock);
            }
        }
    }
    public void removeFood(Food f){
        products.remove(f);
    }
    public void addOrder(Order o){
        if(!this.users.contains(o)) {
            //ver si usar o no el contains
            if(o.getSpecialOrders() + o.getUser().getSpecialOrdersOfToday() <= MAX_SPECIAL_ORDERS){
                orders.add(o);
                o.getUser().addOrder(o);
            }
        }
    }
    public void removeOrder(Order o){
        orders.remove(o);
    }
    public void addCondition(Integer userID, Condition condition){
        if(!usersConditions.containsKey(userID)){   //para que un usuario pueda tener solo un tipo alimenticio
            usersConditions.put(userID,condition);
        }
    }

    public void removeCondition(Integer userID){
        usersConditions.remove(userID);
    }
    public Condition getCondition(Integer userID){
        return usersConditions.get(userID);
    }


    public ArrayList<User> getUsers() {
        ArrayList<User> r_users = new ArrayList<User>();
        for(int i = 0; i < this.users.size(); i++){
            r_users.add(this.users.elementAt(i));
        }
        return r_users;
    }

    public ArrayList<Food> getProducts() {
        ArrayList<Food> r_products = new ArrayList<Food>();
        for(int i = 0; i < this.products.size(); i++){
            r_products.add(this.products.elementAt(i));
        }
        return r_products;
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> r_orders = new ArrayList<Order>();
        for(int i = 0; i < this.orders.size(); i++){
            r_orders.add(this.orders.elementAt(i));
        }
        return r_orders;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
}
package model;

import java.util.Calendar;
import java.util.Vector;

public class OrderSimple extends Order{


    private int cant;
    private Food food;
    public OrderSimple(Food food,int cant, int id, CommonUser user, Calendar time){
        super(id,user,time);
        this.food = food;
        this.cant = cant;
    }

    public int size(){
        return 1;
    }
    public float getPrice(){
        return this.food.getPrice() * cant;
    }

    public int getSpecialOrders(){
        return 0;
    }

    public Vector<OrderSimple> getOrderProducts(){
        Vector<OrderSimple> r_products = new Vector<OrderSimple>();
        r_products.add(this);
        return r_products;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}

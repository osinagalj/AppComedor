package model;

import java.util.Calendar;
import java.util.Vector;

public abstract class Order {
    private int id;
    private CommonUser user;
    private OrderState state;
    private Calendar time;

    public Order(int id,CommonUser user,  Calendar time){

        this.user = user;
        this.time = time;
        state = OrderState.pendiente;
    }
    public abstract int size();
    public abstract float getPrice();
    public abstract Vector<OrderSimple> getOrderProducts();
    public abstract int getSpecialOrders();



    //agregar los metodos para modificar el estado del pedido

    //Getters && Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommonUser getUser() {
        return user;
    }

    public void setUser(CommonUser user) {
        this.user = user;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}

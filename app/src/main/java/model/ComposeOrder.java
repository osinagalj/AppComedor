package model;

import java.util.Calendar;
import java.util.Vector;

public class ComposeOrder extends Order {
    private Vector<Order> orderList;
    private static final int MAX_SPECIAL_ORDERS = 2;
    public ComposeOrder(int id, CommonUser user, Calendar time){
        super(id,user,time);
        orderList = new Vector<Order>();
    }

    //cuando agrego una order, tengo que chekear que no supere el maximo dentro de la orden y que el usuario no tenga pedidos especiales en el dia
    public void addOrder(Order o){//tendiramos que chekear que el o.user == this.user
        if(o.getUser().getId() == super.getUser().getId()){
            if((o.getSpecialOrders() + this.getSpecialOrders()) + super.getUser().getSpecialOrdersOfToday() <= MAX_SPECIAL_ORDERS){
                orderList.add(o);
            }
        }
    }
    public float getPrice(){
        float price = 0;
        for(int i = 0; i < this.orderList.size(); i++){
            price = price + this.orderList.elementAt(i).getPrice();
        }
        return price;
    }
    public int getSpecialOrders(){
        int suma = 0;
        for(Order o : orderList) {
            suma += o.getSpecialOrders();
        }
        return suma;
    }
    //probar si anda
    public Vector<OrderSimple> getOrderProducts(){
        Vector<OrderSimple> r_orders= new Vector<OrderSimple>();
        Vector<OrderSimple> aux_orders = new Vector<OrderSimple>();
        for(Order o : orderList){
            aux_orders = o.getOrderProducts();
            for(OrderSimple o2 : aux_orders){
                r_orders.add(o2);
            }
        }
        return r_orders;
    }

    public int size(){
        int suma = 0;
        for(Order o : orderList) {
            suma += o.size();
        }
        return suma;
    }
}

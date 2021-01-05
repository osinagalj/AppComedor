package model;

import java.util.Calendar;

public class SpecialOrder extends OrderSimple {

    private static final int CANT_MAX = 2;
    private Condition condition;
    private int toHome;

    //hay que chekear la cantidad max sea 2 cuando se llama al metodo de model.SpecialOrder
    public SpecialOrder(int toHome,Food food,int cant, int id, CommonUser user, Calendar time){
        super(food,cant,id,user,time);
        condition = super.getUser().getCondition();
        this.toHome = toHome;
    }
   /* @Override
    public void addFood(){

    }*/
    public int getSpecialOrders(){
        return super.getCant();
    }
    @Override
    public float getPrice() {
        float price = super.getPrice();
        return super.getUser().getUserType().getPrice(price);
    }
}

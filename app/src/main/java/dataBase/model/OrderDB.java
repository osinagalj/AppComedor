package dataBase.model;

import com.example.view.BackEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataBase.Restaurant;
import model.Order;
import model.OrderLine;
import model.OrderState;

public class OrderDB {
    private int id;
    private Date date;
    private int user_id;
    private OrderState state;
    private ArrayList<LineDB> lines = new ArrayList<LineDB>();

    public OrderDB(){}

    private ArrayList<LineDB> getLines(Order o){
        ArrayList<LineDB> list = new ArrayList<>();
        LineDB l = new LineDB(1111,11,true,60f);
        list.add(l);
        /*
        for(OrderLine line: o.getLines()){
            LineDB l = new LineDB(line.getProduct().getId(),line.getAmount(),line.isToHome(),line.getPrice());
            list.add(l);
        }
        */
        return list;
    }

    public OrderDB(Order order){
        this.id = order.getId();
        this.date = order.getPlaced();
        this.state = order.getState();
        this.user_id = order.getPlacedBy().getIdentityCardNumber();
        //lines.addAll(getLines(order));
        this.lines = getLines(order);
    }

    public Order convertToModel(){
        Order o = new Order();
        o.setId(this.id);
        o.setPlaced(this.date);
        o.setState(this.state);
        List<OrderLine> new_lines = new ArrayList<>();


        for(LineDB line: this.lines)
            new_lines.add(new OrderLine(
                    Restaurant.getInstance().getProduct(line.getProductId()),
                    line.getAmount(),
                    line.isToHome(),
                    line.getPrice()
                    )

            );

        o.setLines(new_lines);
        o.setPlacedBy(BackEnd.getLoggedUser());
        return o;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<LineDB> getLines() {
        return lines;
    }

    public void setLines(ArrayList<LineDB> lines) {
        this.lines = lines;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }


}

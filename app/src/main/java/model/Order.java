package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private int id;
    private Date placed;
    private CommonUser placedBy;
    private OrderState state = OrderState.PENDING;
    private List<OrderLine> lines = new ArrayList<OrderLine>();

    public Order(){}

    public Order(int id,CommonUser placedBy){
        this.id = id;
        this.placed = new Date();
        this.placedBy = placedBy;
    }

    public Order(int id,CommonUser placedBy, Date date){
        this.id = id;
        this.placed = date;
        this.placedBy = placedBy;
    }

    public void addLines(List<OrderLine> new_lines){
        lines.addAll(new_lines);
    }

    public int getAmount(int id){
        int amount = 0;
        for (OrderLine line : lines){
            if(line.getProduct().getId() == id){
                amount++;
            }
        }
        return amount;
    }

    public void clearOrder(){
        lines.removeAll(lines);
    }


    public void addProduct(Product p, int amount, boolean toHome){

        for(OrderLine line : lines)
            if(line.getProduct().equals(p) && line.isToHome() == toHome){
                line.increaseAmount(amount);
                return;
            }

        //If the product, toHome did not exist, then I add it
        lines.add(new OrderLine(p,amount,toHome,p.getPrice(placedBy)));
    }


    public float getPrice() {
        float totalPrice = 0;
        for (OrderLine line : lines){
            totalPrice+= line.getPrice();
        }
        return totalPrice;
    }

    public int getId() {
        return id;
    }


    public CommonUser getPlacedBy() {
        return placedBy;
    }

    public List<OrderLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public void removeProduct(int product_id){
        for (OrderLine line : lines){
            if(line.getProduct().getId() == product_id){
                lines.remove(line);
                return;
            }
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public Date getPlaced() {
        return placed;
    }

    public void setPlaced(Date placed) {
        this.placed = placed;
    }

    public void setPlacedBy(CommonUser placedBy) {
        this.placedBy = placedBy;
    }

/*
    public int getNumber_line() {
        return number_line;
    }

    public void setNumber_line(int number_line) {
        this.number_line = number_line;
    }
*/


    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

}

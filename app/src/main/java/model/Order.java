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

    /**
     * @param new_lines new lines to add to the order
     */
    public void addLines(List<OrderLine> new_lines){
        lines.addAll(new_lines);
    }

    /**
     * @param id of the product from which the amount is to be obtained
     * @return the amount in the order
     */
    public int getAmount(int id){
        int amount = 0;
        for (OrderLine line : lines){
            if(line.getProduct().getId() == id){
                amount++;
            }
        }
        return amount;
    }

    /**
     * delete all the lines in the order, consequently the products
     */
    public void clearOrder(){
        lines.removeAll(lines);
    }

    /**
     * @param p product to add to the order
     * @param amount of p to add
     * @param toHome true if the product is to be brought to the home, otherwise false
     */
    public void addProduct(Product p, int amount, boolean toHome){
        for(OrderLine line : lines)
            if(line.getProduct().equals(p) && line.isToHome() == toHome){
                line.increaseAmount(amount);
                return;
            }

        //If the product, toHome did not exist, then I add it
        lines.add(new OrderLine(p,amount,toHome,p.getPrice(placedBy)));
    }

    public String getDescription(){
        String description = "";
        for(OrderLine line : lines)
            description = description + line.getProduct().getName() + "\n";

        return description;
    }

    /**
     * @return the sum of the prices of the products multiplied by the quantity on order
     */
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
        return (CommonUser) placedBy.clone();
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
        return (Date) placed.clone();
    }

    public void setPlaced(Date placed) {
        this.placed = placed;
    }

    public void setPlacedBy(CommonUser placedBy) {
        this.placedBy = placedBy;
    }


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

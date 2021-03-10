package dataBase.model;

public class LineDB {
    private int amount;
    private int product_id;
    private boolean toHome;
    private float price;

    public LineDB(){}

    public LineDB(int amount, int product_id, boolean toHome, float price) {
        this.amount = amount;
        this.product_id = product_id;
        this.toHome = toHome;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public boolean isToHome() {
        return toHome;
    }

    public void setToHome(boolean toHome) {
        this.toHome = toHome;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}

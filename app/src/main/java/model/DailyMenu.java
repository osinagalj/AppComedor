package model;

public class DailyMenu extends Food  {

    private int limit;

    public DailyMenu(){}

    public DailyMenu(int id,String name, String description, int imgId, int productCategory, int stock, float price, int limit) {
        super(id,name, description, imgId, productCategory,stock,price);
        this.limit = limit;
    }


    @Override
    public boolean toHome() {
        return true;
    }

    @Override
    public String toString() {

        return "Menu del Dia {" +
                "stock=" + super.getStock() +
                ", price=" + super.getPrice(null) +
                "} " + super.toString();
    }


    @Override
    public float getPrice(CommonUser user ) {
        return user.getPrice(super.getPrice(user));

    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    @Override
    public void addStock(int stock) {
        super.addStock(stock);;
    }

    @Override
    public int getDailyLimit() {
        return limit;
    }

    @Override
    public boolean decreaseStock(int amount) {
        return super.decreaseStock(amount);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /* Se rompe con la DB
    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(this);
        return products; //Collections.unmodifiableList(
    }
*/

}

package model;

public class DailyMenu extends Food  {

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    private int limit;
    private boolean home = false;

    public DailyMenu(){}

    public DailyMenu(int id,String name, String description, int imgId, int productCategory, int stock, float price, int limit) {
        super(id,name, description, imgId, productCategory,stock,price);
        this.limit = limit;

    }

    public void setHome(){
        this.home = true;
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
    //todo tener el tohone en bool y agregarlo al to strng
/*
    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(this);
        return products; //Collections.unmodifiableList(
    }
*/
    @Override
    public float getPrice(CommonUser user ) {
        //todo hacer un case con las condiciones, dependiendo la cateogria, crear un descuento distinto
        return user.getPrice(new PriceStudent(0.6f),super.getPrice(user));
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
}

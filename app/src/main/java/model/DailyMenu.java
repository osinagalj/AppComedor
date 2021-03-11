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

    /**
     * The menus of the day have a different price depending on the user who orders it.
     */
    @Override
    public float getPrice(CommonUser user) {
        return user.getPrice(super.getPrice(user));
    }

    @Override
    public int getDailyLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

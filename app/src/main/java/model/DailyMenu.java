package model;

public class DailyMenu extends Food  {


    private int limit;
    private boolean home = false;

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
    //todo tener el tohone en bool y agregarlo al to strng

    @Override
    public float getPrice(CommonUser user ) {
        //todo hacer un case con las condiciones, dependiendo la cateogria, crear un descuento distinto
        switch(user.getCategory())
        {
            case ALUMNO:
                return user.getPrice(new PriceStudent(0.6f),super.getPrice(user));
            case DOCENTE:
                return user.getPrice(new PriceProfessor(user.getSubjects()),super.getPrice(user));
            case NO_DOCENTE:
                return user.getPrice(new PriceAntiquity(user.getStartDate()),super.getPrice(user));
            default:
                return user.getPrice(new PriceExternal(),super.getPrice(user));
        }

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

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
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

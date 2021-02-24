package model;

public class PriceExternal extends PriceCalculator {

    public PriceExternal() {}

    @Override
    public String toString() {
        return "Sin descuento";
    }

    @Override
    public float getPrice(CommonUser user, DailyMenu product) {
        return product.getPrice(user);
    }
}
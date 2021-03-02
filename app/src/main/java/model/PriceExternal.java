package model;

public class PriceExternal implements PriceCalculator {

    public PriceExternal() {}

    @Override
    public String toString() {
        return "Sin descuento";
    }

    @Override
    public float getPrice(float price) {
        return price;
    }
}
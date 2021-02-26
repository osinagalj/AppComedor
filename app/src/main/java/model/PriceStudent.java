package model;

public class PriceStudent extends PriceCalculator {

    private float discountPercentage;

    public PriceStudent(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Descuento de estudiante de: " + String.format("%.2f", discountPercentage);
    }

    @Override
    public float getPrice(CommonUser user, DailyMenu product) {
        return product.getPrice(user) - product.getPrice(user) * discountPercentage;
    }
}

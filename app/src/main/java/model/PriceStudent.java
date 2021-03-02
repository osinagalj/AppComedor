package model;

public class PriceStudent implements PriceCalculator {

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    private float discountPercentage;

    public PriceStudent(){}

    public PriceStudent(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Descuento de estudiante de: " + String.format("%.2f", discountPercentage);
    }

    @Override
    public float getPrice(float price) {
        return price - price * discountPercentage;
    }
}
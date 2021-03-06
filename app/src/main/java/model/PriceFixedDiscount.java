package model;

import androidx.annotation.NonNull;

public class PriceFixedDiscount implements PriceCalculator {

    private float discountPercentage;

    public PriceFixedDiscount(){}

    public PriceFixedDiscount(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Descuento de estudiante de: " + String.format("%.2f", discountPercentage);
    }

    @Override
    public float getPrice(float price) {
        return java.lang.Math.round(price - price * discountPercentage);
    }

    @NonNull
    @Override
    public Object clone() {
        return new PriceFixedDiscount(discountPercentage);
    }
}

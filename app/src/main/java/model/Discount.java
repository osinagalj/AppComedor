package model;

import java.io.Serializable;

public class Discount implements PriceCalculator, Serializable {
    private float discountPercentage;

    public Discount(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Descuento por porcentaje fijo: " + String.format("%.2f", discountPercentage);
    }

    @Override
    public float getPrice(float standardPrice) {
        return standardPrice-(standardPrice*discountPercentage);
    }
}

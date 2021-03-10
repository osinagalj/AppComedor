package model;

import com.example.view.BackEnd;

import java.io.Serializable;
import java.util.List;

public class FixedDiscount implements DiscountCalculator, Serializable {


    private float discountPercentage;

    public FixedDiscount(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Descuento por porcentaje fijo: " + String.format("%.2f", discountPercentage);
    }

    @Override
    public float getPrice(List<Product> products) {
        float new_price = 0f;
        for(Product p: products)
            new_price = new_price + p.getPrice(BackEnd.getLoggedUser());
        return new_price-(new_price*discountPercentage);
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}

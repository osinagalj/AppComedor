package model;

import com.example.view.BackEnd;

import java.io.Serializable;
import java.util.List;

public class DiscountMax implements DiscountCalculator, Serializable {
    private float limit;    //El limite minimo que debe tener el combo para aplicar el amount
    private float amount;   //Descuento maximo

    public DiscountMax(float limit, float amount) {
        this.limit = limit;
        if(amount <= limit)
            this.amount = amount;
        else
            this.amount = 0; //Para no aplicar un descuento menor al limite
    }

    @Override
    public String toString() {
        return "Descuento por superar el minimo: " + String.format("%.2f", amount);
    }

    @Override
    public float getPrice(List<Product> products) {
        float new_price = 0f;
        for(Product p: products)
            new_price = new_price + p.getPrice(BackEnd.getLoggedUser());

        if(new_price >= limit)
            return new_price - amount;
        return new_price;
    }
}

package model;

import java.util.List;

public interface DiscountCalculator {
    public abstract float getPrice(List<Product> products);
}

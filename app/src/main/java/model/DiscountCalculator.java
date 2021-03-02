package model;

import java.util.List;

public interface DiscountCalculator {
    float getPrice(List<Product> products);
}

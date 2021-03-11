package model;

import java.util.List;

public interface DiscountCalculator extends Cloneable{
    float getPrice(List<Product> products);
    Object clone();
}

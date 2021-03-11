package model;

public interface PriceCalculator extends Cloneable{
    float getPrice(float price);
    Object clone();
}

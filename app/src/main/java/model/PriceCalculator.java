package model;

public interface PriceCalculator  {
    public String toString();
    public abstract float getPrice(float standardPrice);
}

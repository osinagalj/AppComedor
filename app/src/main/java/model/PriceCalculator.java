package model;

public abstract class PriceCalculator {
    //private String category; todo preguntar si esto va aca o en common user
    public abstract float getPrice(CommonUser user ,DailyMenu product);
}

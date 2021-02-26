package model;

import java.io.Serializable;

public abstract class PriceCalculator implements Serializable {
    //private String category; todo preguntar si esto va aca o en common user
    public abstract float getPrice(CommonUser user ,DailyMenu product);
}

package model;

import java.util.Date;

public class PriceAntiquity implements PriceCalculator {
    private final Date startDate;

    public PriceAntiquity(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Descuento por antiguedad. Fecha de inicio: " + startDate.toString();
    }

    @Override
    public float getPrice(float price) {
        int milisecondsByDay = 86400000;
        int daysBetween = (int) (((new Date()).getTime()-startDate.getTime()) / milisecondsByDay);
        float daysLog2 = (float)(Math.log(daysBetween)/Math.log(2));
        float discount = daysLog2/100;
        return java.lang.Math.round(price - (price*discount));
    }
}

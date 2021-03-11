package model;

import androidx.annotation.NonNull;

import java.util.Date;

public class PriceAntiquity implements PriceCalculator {

    private final Date startDate;

    public PriceAntiquity(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return (Date) startDate.clone();
    }

    @Override
    public String toString() {
        return "Descuento por antiguedad. Fecha de inicio: " + startDate.toString();
    }

    @Override
    public float getPrice(float price) {
        int daysBetween = (int) (((new Date()).getTime()-startDate.getTime()) / 86400000); //ms de un dia
        float daysLog2 = (float)(Math.log(daysBetween)/Math.log(2));
        float discount = daysLog2/100;
        return java.lang.Math.round(price - (price*discount));
    }

    @NonNull
    @Override
    public Object clone() {
        return new PriceAntiquity((Date) startDate.clone());
    }

}

package model;

import java.time.Duration;
import java.time.LocalDate;

public class PriceAntiquity implements PriceCalculator {
    private final LocalDate startDate;

    public PriceAntiquity(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Descuento por antiguedad. Fecha de inicio: " + startDate.toString();
    }

    @Override
    public float getPrice(float price) {
        long daysBetween = Duration.between(startDate,LocalDate.now()).toDays();
        float daysLog2 = (float)(Math.log(daysBetween)/Math.log(2));
        float discount = daysLog2/100;
        return java.lang.Math.round(price - (price*discount));
    }
}

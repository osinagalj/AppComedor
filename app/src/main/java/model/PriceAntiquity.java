package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now().atStartOfDay(),startDate.atStartOfDay());
        float daysLog2 = (float)(Math.log(daysBetween)/Math.log(2));
        float discount = daysLog2/100;
        return price - (price*discount);
    }
}
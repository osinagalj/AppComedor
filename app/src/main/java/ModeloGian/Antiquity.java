package ModeloGian;

import java.time.LocalDate;

public class Antiquity implements PriceCalculator{
    private final LocalDate startDate;

    public Antiquity(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Descuento por antiguedad. Fecha de inicio: " + startDate.toString();
    }

    @Override
    public float getPrice(float standardPrice) {
        //long daysBetween = ChronoUnit.DAYS.between(LocalDate.now().atStartOfDay(),startDate.atStartOfDay());
        //float daysLog2 = (float)(Math.log(daysBetween)/Math.log(2));
       // float discount = daysLog2/100;
        //return standardPrice-(standardPrice*discount);
        return 2f;
    }
}

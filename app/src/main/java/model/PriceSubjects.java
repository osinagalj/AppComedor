package model;

public class PriceSubjects implements PriceCalculator {

    private int subjects;

    public PriceSubjects(int subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Descuento de profesor ";
    }

    @Override
    public float getPrice(float price) {
        if(subjects > 3)
            return price - price * 0.3f;
        return java.lang.Math.round(price - price *subjects * 0.1f);
    }
}
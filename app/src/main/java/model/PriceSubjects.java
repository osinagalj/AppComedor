package model;

import androidx.annotation.NonNull;

public class PriceSubjects implements PriceCalculator {

    private int subjects;

    public PriceSubjects(int subjects) {
        this.subjects = subjects;
    }

    public int getSubjects() {
        return subjects;
    }

    public void setSubjects(int subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Descuento de profesor. Materias: "+subjects;
    }

    @Override
    public float getPrice(float price) {
        if(subjects > 3)
            return price - price * 0.3f;
        return java.lang.Math.round(price - price * subjects * 0.1f);
    }

    @NonNull
    @Override
    public Object clone() {
        return new PriceSubjects(subjects);
    }
}
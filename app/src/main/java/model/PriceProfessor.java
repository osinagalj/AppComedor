package model;

public class PriceProfessor extends PriceCalculator {

    private int subjects;

    public PriceProfessor(int subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Descuento de profesor ";
    }

    @Override
    public float getPrice(CommonUser user,DailyMenu product) {
        if(subjects > 3)
            return product.getPrice(user) - product.getPrice(user) * 0.3f;
        return product.getPrice(user) - subjects * 0.1f;
    }
}
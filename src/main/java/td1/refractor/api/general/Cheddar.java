package td1.refractor.api.general;

public class Cheddar implements Product {
    // 400 kcal / 100g
    private double kcal_100g = 400;
    private double weight;
    private double BASE_PRICE = 4;

    public Cheddar(double weight) {
        this.weight = weight;
    }

    @Override
    public double kcal() {
        return (kcal_100g * weight) / 100;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public double price() {
        return BASE_PRICE * weight / 100;
    }

    @Override
    public String toString() {
        return String.format("cheddar (%.0fg) -- %.2f€", weight(), price());
    }
}

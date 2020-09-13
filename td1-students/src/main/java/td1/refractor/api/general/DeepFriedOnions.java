package td1.refractor.api.general;

public class DeepFriedOnions implements Product {
    // 590 kcal / 100g
    private double kcal_100g = 590;
    private double weight;
    private double BASE_PRICE = 1.5;

    public DeepFriedOnions(double weight) {
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
        return String.format("deep fried onions (%.0fg) -- %.2f€", weight(), price());
    }

}

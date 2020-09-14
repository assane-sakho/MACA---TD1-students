package td1.original.api.general;

public class Sauce implements Product {

    public static enum SauceType {
        BURGER, BARBECUE, BEARNAISE;
        // BURGER : 240 kcal / 100g
        // BARBECUE : 130 kcal / 100g
        // BEARNAISE : 550 kcal / 100g
    }

    private static double BASE_PRICE = 1;

    private SauceType type;
    private double weight;

    public Sauce(SauceType type, double weight) {
        this.type = type;
        this.weight = weight;
    }

    @Override
    public double price() {
        return BASE_PRICE;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s sauce (%.0fg) -- %.2f€", type, weight(), price());
    }
}

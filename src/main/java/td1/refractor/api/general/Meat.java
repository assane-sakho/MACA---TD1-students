package td1.refractor.api.general;

public class Meat implements Product {

    public enum MeatType {
        BEEF, WHITEFISH;
        // BEEF : 200 kcal / 100g
        // WHITEFISH : 170 kcal / 100g

        public double price() {
            double rtr;
            switch (this) {
                case WHITEFISH:
                    rtr = 6;
                    break;
                case BEEF:
                default:
                    rtr = 4;
            }
            return rtr;
        }
    }

    private MeatType type;
    private double weight;
    private double kcal_100g;

    public Meat(MeatType type, double weight) {
        this.type = type;
        this.weight = weight;
        kcal_100g = type == MeatType.BEEF ? 200 : 170;
    }

    @Override
    public double kcal() {
        return (kcal_100g * weight) / 100;
    }

    @Override
    public double price() {
        return type.price() * weight / 100;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s (%.0fg) -- %.2f€", type, weight(), price());
    }
}

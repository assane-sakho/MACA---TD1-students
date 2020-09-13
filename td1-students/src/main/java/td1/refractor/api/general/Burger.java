package td1.refractor.api.general;

import java.util.List;

public class Burger implements Product {

    String name;
    List<Product> items;

    public Burger(String name, List<Product> items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public double weight() {
        return items.stream().map(Product::weight).reduce(0.0, Double::sum);
    }

    @Override
    public double price() {
        return items.stream().map(Product::price).reduce(0.0, Double::sum);
    }

    @Override
    public double kcal() {
        return items.stream().map(Product::kcal).reduce(0.0, Double::sum);
    }

    public double kcal_100g() {
        return Math.round((kcal() * 100) / items.stream().map(Product::weight).reduce(0.0, Double::sum));
    }

    @Override
    public String toString() {
        final String DELIM = "--------------------\n";
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("*** Menu %s ***\n", name));
        for (Object item : items) {
            buffer.append(String.format("- %s\n", item));
        }
        buffer.append(DELIM);
        buffer.append(String.format("price:         %.2f\n", price()));
        buffer.append(DELIM);
        buffer.append(String.format("calories:      %.2f\n", kcal()));
        buffer.append(String.format("calories/100g: %.2f\n", kcal_100g()));
        buffer.append(DELIM);
        return buffer.toString();
    }
}

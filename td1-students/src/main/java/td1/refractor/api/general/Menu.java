package td1.refractor.api.general;

import java.util.ArrayList;
import java.util.List;

import static td1.refractor.api.general.Restaurant.Product_Type.*;

public class Menu {
    List<Product> products;
    Restaurant restaurantFrom;
    String name;

    public Menu(String name, Restaurant restaurant)
    {
        this.name = name;
        this.restaurantFrom = restaurant;
        products = new ArrayList<Product>();
    }

    public Menu addProduct(Product product)
    {
        products.add(product);
        return this;
    }

    public Menu with_onions()
    {
        products.add(new DeepFriedOnions(restaurantFrom.productsWeight.get(ONION)));
        return this;
    }

    public Menu with_cheese()
    {
        products.add(new Cheddar(restaurantFrom.productsWeight.get(CHEESE)));
        return this;
    }

    public Menu with_sauce(Sauce.SauceType sauceType)
    {
        products.add(new Sauce(sauceType, restaurantFrom.productsWeight.get(SAUCE)));
        return this;
    }

    public Burger cook()
    {
        return new Burger(name, products);
    }

}

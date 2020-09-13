package td1.refractor.api.general;

import java.util.HashMap;
import java.util.Map;

import static td1.refractor.api.general.Meat.MeatType.*;
import static td1.refractor.api.general.Restaurant.MeatSize.*;
import static td1.refractor.api.general.Restaurant.MenuType.*;
import static td1.refractor.api.general.Restaurant.Product_Type.*;
import static td1.refractor.api.general.Sauce.SauceType.*;

public class Advisor {
    Map<RestaurantType, Restaurant> restaurantList;

    public static enum RestaurantType {
        CHEAP, EXPENSIVE;
    }

    public Advisor() {
        restaurantList = new HashMap<RestaurantType, Restaurant>();

        Restaurant macDeau = new Restaurant(50, 100, 200, 40, 10, 10, 15);
        macDeau =
                macDeau
                        .addMenu(
                                MEAT_MENU,
                                new Menu("Beefy", macDeau)
                                        .addProduct(new Meat(BEEF, macDeau.meatWeight.get(AVERAGE)))
                                        .addProduct(new Sauce(BURGER, macDeau.productsWeight.get(SAUCE)))
                                        .addProduct(new DeepFriedOnions(macDeau.productsWeight.get(ONION)))
                                        .addProduct(new Cheddar(macDeau.productsWeight.get(CHEESE))))
                        .addMenu(
                                FISH_MENU,
                                new Menu("Fishy", macDeau)
                                        .addProduct(new Meat(WHITEFISH, macDeau.meatWeight.get(SMALL)))
                                        .addProduct(new Sauce(BEARNAISE, macDeau.productsWeight.get(SAUCE))))
                        .addMenu(
                                CHEESE_MENU,
                                new Menu("Cheesy", macDeau)
                                        .addProduct(new Meat(BEEF, macDeau.meatWeight.get(SMALL)))
                                        .addProduct(new Cheddar(macDeau.productsWeight.get(CHEESE))))
                        .addMenu(
                                OWN_STYLE_MENU,
                                new Menu("Own style", macDeau));

        Restaurant bigBurger = new Restaurant(100, 200, 400, 20, 20, 10, 30);
        bigBurger = bigBurger
                .addMenu(
                        MEAT_MENU,
                        new Menu("BigMarcel", bigBurger)
                                .addProduct(new Meat(BEEF, macDeau.meatWeight.get(BIG)))
                                .addProduct(new Sauce(BURGER, macDeau.productsWeight.get(SAUCE)))
                                .addProduct(new DeepFriedOnions(macDeau.productsWeight.get(ONION)))
                                .addProduct(new Tomato(macDeau.productsWeight.get(CHEESE))))
                .addMenu(
                        FISH_MENU,
                        new Menu("FiletO'Poisson", bigBurger)
                                .addProduct(new Meat(WHITEFISH, macDeau.meatWeight.get(AVERAGE)))
                                .addProduct(new Sauce(BEARNAISE, macDeau.productsWeight.get(SAUCE))))
                .addMenu(
                        CHEESE_MENU,
                        new Menu("MaxeeCheesee", bigBurger)
                                .addProduct(new Meat(BEEF, macDeau.meatWeight.get(AVERAGE)))
                                .addProduct(new Cheddar(macDeau.productsWeight.get(CHEESE)))
                                .addProduct(new DeepFriedOnions(macDeau.productsWeight.get(ONION)))
                )
                .addMenu(
                        OWN_STYLE_MENU,
                        new Menu("Own style", bigBurger));

        restaurantList.put(RestaurantType.CHEAP, macDeau);
        restaurantList.put(RestaurantType.EXPENSIVE, bigBurger);
    }

    public Restaurant select(RestaurantType restaurantType) {
        return restaurantList.get(restaurantType);
    }
}

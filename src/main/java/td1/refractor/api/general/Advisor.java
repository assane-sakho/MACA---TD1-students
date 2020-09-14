package td1.refractor.api.general;

import java.util.HashMap;
import java.util.Map;

import static td1.refractor.api.general.Meat.MeatType.*;
import static td1.refractor.api.general.Restaurant.MeatSize.*;
import static td1.refractor.api.general.Restaurant.MenuType.*;
import static td1.refractor.api.general.Restaurant.Product_Type.*;
import static td1.refractor.api.general.Sauce.SauceType.*;

public class Advisor {
    public static final Advisor INSTANCE = new Advisor();
    Map<RestaurantType, Restaurant> restaurantList;

    public static enum RestaurantType {
        CHEAP, EXPENSIVE;
    }

    private Advisor() {
        restaurantList = new HashMap<RestaurantType, Restaurant>();

        MacDeau macDeau = new MacDeau(50, 100, 200, 40, 10, 10, 15);
        BigBurger bigBurger = new BigBurger(100, 200, 400, 20, 20, 10, 30);

        restaurantList.put(RestaurantType.CHEAP, macDeau);
        restaurantList.put(RestaurantType.EXPENSIVE, bigBurger);
    }

    public static Advisor getInstance()
    {
        return INSTANCE;
    }
    public Restaurant select(RestaurantType restaurantType) {
        return restaurantList.get(restaurantType);
    }
}

package td1.refractor.api.general;

import java.util.HashMap;
import java.util.Map;

import static td1.refractor.api.general.Restaurant.MeatSize.*;
import static td1.refractor.api.general.Restaurant.Product_Type.*;

public abstract class Restaurant {
    public static enum MeatSize {
        SMALL, AVERAGE, BIG
    }
    public static enum Product_Type {
        SAUCE, ONION, TOMATO, CHEESE
    }
    public static enum MenuType {
        MEAT_MENU, FISH_MENU, CHEESE_MENU, OWN_STYLE_MENU
    }

    Map<Product_Type, Integer> productsWeight;
    Map<MeatSize, Integer> meatWeight;

    Map<MenuType, Menu> menus;

    public Restaurant(int w1, int w2, int w3, int w4, int w5, int w6, int w7) {
        productsWeight = new HashMap<>();
        meatWeight = new HashMap<>();

        meatWeight.put(SMALL, w1);
        meatWeight.put(AVERAGE, w2);
        meatWeight.put(BIG, w3);
        productsWeight.put(SAUCE, w4);
        productsWeight.put(ONION, w5);
        productsWeight.put(TOMATO, w6);
        productsWeight.put(CHEESE, w7);

        menus = new HashMap<>();
    }

    public Menu order_menu(MenuType menuType) {
        return menus.get(menuType);
    }

    public Menu order_personal(MeatSize meatSize, Meat.MeatType meatType) {
        return menus.get(MenuType.OWN_STYLE_MENU).addProduct(new Meat(meatType,meatWeight.get(meatSize)));
    }

    public abstract void setMenus();
}

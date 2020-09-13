package td1.refractor.client;

import td1.refractor.api.general.*;

import java.util.Arrays;
import java.util.List;

import static td1.refractor.api.general.Advisor.RestaurantType.*;
import static td1.refractor.api.general.Meat.MeatType.*;
import static td1.refractor.api.general.Restaurant.MeatSize.*;
import static td1.refractor.api.general.Restaurant.MenuType.*;
import static td1.refractor.api.general.Sauce.SauceType.*;

public class App {
    public static void main(String args[]) {
        Advisor advisor = new Advisor();
        Burger alice_dinner = advisor // appel au conseiller Burger de la ville
                .select(CHEAP) // choix d'un restaurant pas cher
                .order_menu(FISH_MENU) // menu poisson
                .cook(); // rien de plus, on passe la commande

        Burger bob_dinner = advisor // appel au conseiller Burger de la ville
                .select(CHEAP) // choix d'un restaurant pas cher
                .order_menu(MEAT_MENU) // menu viande
                .with_onions() // des oignons en plus
                .with_cheese() // du fromage en plus
                .cook(); // on passe la commande

        Burger charles_dinner = advisor // appel au conseiller burger de la ville
                .select(EXPENSIVE) // choix d'un restaurant cher
                .order_personal(BIG, BEEF) // menu personnalisé avec grosse part de boeuf
                .with_onions() // des oignons en plus
                .with_cheese() // du fromage en plus
                .with_sauce(BARBECUE) // de la sauce
                .with_sauce(BEARNAISE) // encore de la sauce
                .with_sauce(BURGER) // toujours de la sauce
                .with_cheese() // et puis encore du fromage aussi
                .cook(); // on passe la commande

        List<Burger> dinners = Arrays.asList(
                alice_dinner,
                bob_dinner,
                charles_dinner
        );
        for (Burger dinner : dinners) {
            System.out.println(dinner);
        }
    }
}
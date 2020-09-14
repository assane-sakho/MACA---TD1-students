## TD 1

### Exercice x : étude de cas

On dispose d'une librairie très simple permettant de simuler la fabrication de Burgers (```td1.original```). On a différents ingrédients qui ont un poids et un prix à partir desquels on peut créér des Burgers. Dans ```td1.original.client.App``` on peut observer trois créations de Burgers.

```java
...
public class App {
    public static void main(String args[]) {
        Burger alice_dinner = new Burger(
                "Fishy",
                List.of(new Meat(WHITEFISH, 50),
                        new Sauce(BEARNAISE, 40))
        );
        Burger bob_dinner = new Burger(
                "Beefy",
                List.of(new Meat(BEEF, 100),
                        new Sauce(BURGER, 40),
                        new DeepFriedOnions(10),
                        new Cheddar(15),
                        new DeepFriedOnions(10),
                        new Cheddar(15))
        );
        Burger charles_dinner = new Burger(
                "Own Style",
                List.of(new Meat(BEEF,400),
                        new DeepFriedOnions(20),
                        new Cheddar(30),
                        new Sauce(BARBECUE,20),
                        new Sauce(BEARNAISE,20),
                        new Sauce(BURGER,20),
                        new Cheddar(30))
        );
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
```

Affichage avant refactoring :

```java
*** Menu Fishy ***
- WHITEFISH (50g) -- 3,00€
- BEARNAISE sauce (40g) -- 1,00€
--------------------
price:         4,00
--------------------

*** Menu Beefy ***
- BEEF (100g) -- 4,00€
- BURGER sauce (40g) -- 1,00€
- deep fried onions (10g) -- 0,15€
- cheddar (15g) -- 0,60€
- deep fried onions (10g) -- 0,15€
- cheddar (15g) -- 0,60€
--------------------
price:         6,50
--------------------

*** Menu Own Style ***
- BEEF (400g) -- 16,00€
- deep fried onions (20g) -- 0,30€
- cheddar (30g) -- 1,20€
- BARBECUE sauce (20g) -- 1,00€
- BEARNAISE sauce (20g) -- 1,00€
- BURGER sauce (20g) -- 1,00€
- cheddar (30g) -- 1,20€
--------------------
price:         21,70
--------------------
```

Alice commande un Burger poisson avec sauce béarnaise.
Bob commande un Burger boeuf avec sauce burger et deux fois du cheddar et des oignons.
Enfin, Charles commande un Burger original non recommandé par les médecins.

Cette librairie présente plusieurs inconvénients que vous devez corriger.

1. on souhaite prendre en compte des critères de santé dans l'information sur les Burgers. Pour cela il faudrait indiquer les calories totales et aux 100g des commandes (en se basant sur les calories des ingrédients, indiquées en commentaire dans le code). Voir l'affichage post-refactoring ci-dessous.

2. les Burgers rentrent en fait dans différentes catégories-menus : menu viande, menu poisson, menu cheese et menu personnalisé. Il y a différentes tailles pour la viande ou le poisson : petit, moyen, gros. Quand on arrive dans un restaurant on précise le menu souhaité et la taille en cas de menu personnalisé. Il est possible d'y rajouter des ingrédients.

3. différents restaurants instancient différemment les types de menus (voir ci-dessous). Pour choisir son restaurant, on fait appel à un conseiller burger en indiquant si on souhaite un restaurant cher ou non. En conséquence on est fléché vers l'un des deux restaurants de la ville (MacDeau n'est pas cher et BigBurger l'est).

    - MacDeau (pas cher)

        - nos menus :

            - menu viande (Beefy) : part moyenne de boeuf, sauce burger, oignons et fromage
            - menu poisson (Fishy) : petite part de poisson, sauce béarnaise
            - menu cheese (Cheesy) : petite part de boeuf, fromage
  
          - ou les ingrédients de votre choix (Own Style)

        - nos parts d'ingrédients :

            - viande/poisson petit = 50g, moyen = 100g, grand = 200g
            - sauces 40g
            - oignons 10g
            - tomates 10g
            - fromage 10g

    - BigBurger (cher)

        - nos menus :

          - menu viande (BigMarcel) : grosse part de boeuf, sauce burger, oignons et tomates
    
          - menu poisson (FiletO'Poisson) : part moyenne de poisson, sauce béarnaise 

          - menu cheese (MaxeeCheesee) : part moyenne de boeuf, fromage et oignons

          - ou les ingrédients de votre choix (Own Style)

        - nos parts d'ingrédients :

            - viande/poisson petit = 100g, moyen = 200g, grand = 400g
            - sauces 20g
            - oignons 20g
            - tomates 10g
            - fromage 30g

4. chaque restaurant doit pouvoir instancier différemment les types de menus mais aussi la quantité d'ingrédients (dans les menus à la base ou pour les ingrédient additionnels demandés).

5. l'API n'est pas très agréable. On préfèrerait pouvoir chaîner les éléments constitutifs des Burgers un peu comme on le ferait à un comptoir. Ainsi par exemple on aurait pour Alice :

    ```java
    Burger alice_dinner = advisor // appel au conseiller Burger de la ville
            .select(CHEAP) // choix d'un restaurant pas cher
            .order_menu(FISH_MENU) // menu poisson
            .cook(); // rien de plus, on passe la commande
    ```

    pour Bob :

    ```java
    Burger bob_dinner = advisor // appel au conseiller Burger de la ville
            .select(CHEAP) // choix d'un restaurant pas cher
            .order_menu(MEAT_MENU) // menu viande
            .with_onions() // des oignons en plus
            .with_cheese() // du fromage en plus
            .cook(); // on passe la commande
    ```

    et pour Charles :

    ```java
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
    ```

6. il faut permettre l'évolution de la situation (type des menus, instantiation par les restaurants, quantité des ingrédients, etc) au maximum dans votre réalisation.

Affichage après refactoring :

```java
*** Menu Fishy ***
- WHITEFISH (50g) -- 3,00€
- BEARNAISE sauce (40g) -- 1,00€
--------------------
price:         4,00
--------------------
calories:      305
calories/100g: 339
--------------------

*** Menu Beefy ***
- BEEF (100g) -- 4,00€
- BURGER sauce (40g) -- 1,00€
- deep fried onions (10g) -- 0,15€
- cheddar (15g) -- 0,60€
- deep fried onions (10g) -- 0,15€
- cheddar (15g) -- 0,60€
--------------------
price:         6,50
--------------------
calories:      534
calories/100g: 281
--------------------

*** Menu Own style ***
- BEEF (400g) -- 16,00€
- deep fried onions (20g) -- 0,30€
- cheddar (30g) -- 1,20€
- BARBECUE sauce (20g) -- 1,00€
- BEARNAISE sauce (20g) -- 1,00€
- BURGER sauce (20g) -- 1,00€
- cheddar (30g) -- 1,20€
--------------------
price:         21,70
--------------------
calories:      1342
calories/100g: 249
--------------------
```

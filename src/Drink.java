import java.util.HashMap;

public enum Drink {
    COFFEE_PLAIN("Coffee (Plain)", 20, new HashMap<>(){{
        put(Ingredient.COFFEE, 0.05);
        put(Ingredient.WATER, 1.0);
    }}),

    COFFEE_MILK("Coffee (Milk)", 25, new HashMap<>(){{
        put(Ingredient.COFFEE, 0.05);
        put(Ingredient.WATER, 0.75);
        put(Ingredient.MILK, 0.25);
    }}),

    COFFEE_LATTE("Coffee Latte", 32, new HashMap<>(){{
        put(Ingredient.COFFEE, 0.10);
        put(Ingredient.WATER, 0.25);
        put(Ingredient.SUGAR, 0.05);
        put(Ingredient.MILK, 0.75);
    }}),

    COFFEE_LATTE_MACCHIATO("Coffee Latte Macchiato", 35, new HashMap<>(){{
        put(Ingredient.COFFEE, 0.10);
        put(Ingredient.WATER, 0.25);
        put(Ingredient.SUGAR, 0.05);
        put(Ingredient.MILK, 0.75);
    }}),

    COFFEE_MAROCCHINO("Coffee Marocchino", 35, new HashMap<>(){{
        put(Ingredient.COFFEE, 0.2);
        put(Ingredient.WATER, 0.8);
        put(Ingredient.CHOCOLATE, 0.2);
        put(Ingredient.SUGAR, 0.05);
        put(Ingredient.MILK, 0.2);
    }}),

    COFFEE_ICE("Coffee (Ice)", 28, new HashMap<>(){{
        put(Ingredient.COFFEE, 0.05);
        put(Ingredient.WATER, 0.75);
        put(Ingredient.SUGAR, 0.05);
        put(Ingredient.MILK, 0.20);
    }});

    String label;
    int baseCost;
    HashMap<Ingredient,Double> composition;

    Drink(String label, int baseCost, HashMap<Ingredient,Double> composition) {
        setLabel(label);
        setBaseCost(baseCost);
        setComposition(composition);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public void setComposition(HashMap<Ingredient, Double> composition) {
        this.composition = composition;
    }
}

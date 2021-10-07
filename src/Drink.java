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

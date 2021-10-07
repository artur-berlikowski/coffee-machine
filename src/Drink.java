import java.util.HashMap;

public class Drink {
    private String name;
    private int baseCost;
    private HashMap<Ingredient, Double> composition;

    public Drink(String name, int baseCost, HashMap<Ingredient,Double> composition) {
        setName(name);
        setBaseCost(baseCost);
        setComposition(composition);
    }

    public void setComposition(HashMap<Ingredient,Double> composition) {
        this.composition = composition;
    }

    public void setBaseCost(int baseCost) { this.baseCost = baseCost; }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public int getBaseCost() { return baseCost; }

    public HashMap<Ingredient, Double> getComposition() { return composition; }
}

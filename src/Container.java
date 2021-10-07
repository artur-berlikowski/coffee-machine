public class Container {
    private final Ingredient ingredient;
    int volume;
    int maxVolume;

    public Container(Ingredient ingredient, int max_volume) {
        this.ingredient = ingredient;
        this.maxVolume = max_volume;

        volume = 0;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getVolume() {
        return volume;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

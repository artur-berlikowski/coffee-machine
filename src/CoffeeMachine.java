import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    UserInterface ui;
    ArrayList<Container> containers;
    ArrayList<Drink> drinks;
    int totalEarnings;
    boolean running;

    public CoffeeMachine() {
        init();
    }

    public void update() {
        while(running) {
            int choice = displayMenu();
            //If the chosen option is a drink in the drink list prepare a drink
            if(choice <= drinks.size()) prepareDrink(drinks.get(choice-1));
            //If not then return to the service menu (last option)
            else stop();
        }
    }

    public void start() {
        if(!isRunning()) {
            running = true;
            loadingBar();
            update();
        }
    }

    public void stop() { if(isRunning()) { running = false; } }

    public void fill() {
        System.out.println("\nFilling all containers ...\n");
        for(Container container : containers) { container.setVolume(container.getMaxVolume()); }
        loadingBar();
        System.out.println("Filling process completed." +
                "\n");
    }

    public void clean() {
        System.out.println("\nCleaning process started ...\n");
        for(Container container : containers) { container.setVolume(0); }
        loadingBar();
        System.out.println("Cleaning process completed." +
                "\n");
    }

    public void displayIngredients() {
        ArrayList<String[]> rows = new ArrayList<>();
        for(Container container : containers) {
            Ingredient ingredient = container.getIngredient();
            rows.add(new String[] {ingredient.label, container.volume + " " + ingredient.unit.unit});
        }
        ui.displayAsTable(new String[] {"Ingredient", "Volume"}, rows);
    }

    public boolean isRunning() { return running; }

    public void addEarnings(int cost) { totalEarnings += cost; }

    public void loadingBar() {
        int maxWidth = UserInterface.MAX_WIDTH;
        for(int i = 0; i < maxWidth; i++) {
            try { Thread.sleep(25); } catch(Exception e) {};
            System.out.print((i == 0 ? "\n" : "") + "|" + (i ==  maxWidth - 1 ? "\n\n" : ""));
        }
    }

    private void init() {
        ui = new UserInterface();
        createIngredients();
        createDrinks();
    }

    private int displayMenu() {
        ArrayList<String> options = new ArrayList<>();
        for(Drink drink : drinks) { options.add(drink.getName()); }
        return ui.displayTableMenu("Drinks Menu", options, 4);
    }

    private void prepareDrink(Drink drink) {
        String name = drink.getName().toLowerCase();
        int baseCost = drink.getBaseCost();
        HashMap<Ingredient,Double> composition = drink.getComposition();
        Size size = promptSize(baseCost);
        if(size != null) {
            if(checkIngredients(composition, size)) {
                int actualCost = calculatePrize(baseCost, size);
                addEarnings(actualCost);
                System.out.println("\nPreparing a " + size.label + " " + name + " for " + actualCost + "SEK\n");
                useIngredients(composition,size);
                loadingBar();
                System.out.println("You drink is ready, hope you'll enjoy it\n");
            } else {
                System.out.println("\nThere are to few ingredients for this drink, please access the service menu to fill the containers.\n");
                return;
            }
        } else {
            return;
        }
    }

    //Prompt the user on what size of beverage they want
    private Size promptSize(int baseCost) {
        ArrayList<String> options = new ArrayList<>();
        for(Size size : Size.values()) { options.add(Utility.capitalize(size.label) + "(" + size.volume + "ml), " + calculatePrize(baseCost, size) + "SEK"); }

        Size size = null;

        switch(ui.displayTableMenu("What size would you like you drink to be?", options, 4)) {
            case 1 -> size = Size.SMALL;
            case 2 -> size = Size.MEDIUM;
            case 3 -> size = Size.LARGE;
            case 4 -> size = Size.MONSTER;
            case 5 -> size = null;
        }

        return size;
    }

    //Use the necessary ingredients
    private void useIngredients(HashMap<Ingredient,Double> composition, Size size) {
        for(Map.Entry<Ingredient,Double> entry : composition.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double percentage = entry.getValue();
            for(Container container : containers) {
                Ingredient currentIngredient = container.getIngredient();
                int toRemove = calculateVolume(size.volume,percentage);
                if(currentIngredient == ingredient) {
                    container.setVolume(container.getVolume() - toRemove);
                    System.out.println("Used " + toRemove + currentIngredient.unit.unit + " of " + currentIngredient.label + "\n");
                }
            }
        }
    }

    //Check to see if there are enough ingredients
    private boolean checkIngredients(HashMap<Ingredient,Double> composition, Size size) {
        boolean enoughIngredients = true;
        for(Map.Entry<Ingredient,Double> entry : composition.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double percentage = entry.getValue();
            int amount = calculateVolume(size.volume, percentage);
            for(Container container : containers) {
                if(container.getIngredient() == ingredient && amount >= container.volume) {
                    enoughIngredients = false;
                }
            }
        }
        return enoughIngredients;
    }

    private int calculatePrize(int baseCost, Size size) {
        return  (int)Math.floor(baseCost * size.volume / Size.SMALL.volume);
    }

    //Calculate the volume required for an ingredient
    private int calculateVolume(int volume, double percentage) {
        return (int)Math.floor(volume * percentage);
    }

    private void createIngredients() {
        containers = new ArrayList<>();
        containers.add(new Container(Ingredient.COFFEE,1000));
        containers.add(new Container(Ingredient.CHOCOLATE, 1000));
        containers.add(new Container(Ingredient.CHAI, 250));
        containers.add(new Container(Ingredient.BLACK_TEA, 75));
        containers.add(new Container(Ingredient.FRUIT_TEA, 75));
        containers.add(new Container(Ingredient.GREEN_TEA, 75));
        containers.add(new Container(Ingredient.MINT_TEA, 75));
        containers.add(new Container(Ingredient.CREAM_POWDER, 200));
        containers.add(new Container(Ingredient.MILK, 2000));
        containers.add(new Container(Ingredient.WATER, 5000));
    }

    private void createDrinks() {
        drinks = new ArrayList<>();

        //PLAIN COFFEE
        drinks.add(new Drink("Coffee, Plain", 20, new HashMap<Ingredient,Double>(){{
            put(Ingredient.COFFEE, 0.05);
            put(Ingredient.WATER, 1.0);
        }}));

        //COFFEE WITH MILK
        drinks.add(new Drink("Coffee, Milk", 25, new HashMap<Ingredient,Double>(){{
            put(Ingredient.COFFEE, 0.05);
            put(Ingredient.WATER, 0.75);
            put(Ingredient.MILK, 0.25);
        }}));
    }
}

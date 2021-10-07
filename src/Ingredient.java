public enum Ingredient {
    COFFEE("Coffee Beans", Unit.G),
    CHOCOLATE("Chocolate Powder",Unit.G),
    CHAI("Chai Powder",Unit.G),
    MINT_TEA("Mint Tea",Unit.G),
    FRUIT_TEA("Fruit Tea",Unit.G),
    GREEN_TEA("Green Tea",Unit.G),
    BLACK_TEA("Black Tea",Unit.G),
    CREAM_POWDER("Cream Powder", Unit.G),
    MILK("Milk",Unit.ML),
    WATER("Water",Unit.ML),
    SUGAR("Sugar", Unit.G);

    String label;
    Unit unit;

    Ingredient(String label, Unit unit) {
        this.label = label;
        this.unit = unit;
    }
}

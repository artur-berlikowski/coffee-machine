public enum Unit {
    G("g","gram"),
    ML("ml","millilitre");

    String unit;
    String unitLabel;

    Unit(String unit, String unitLabel) {
        this.unit = unit;
        this.unitLabel = unitLabel;
    }
}

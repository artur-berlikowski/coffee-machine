public enum Size {
    SMALL("small", 225),
    MEDIUM("medium", 350),
    LARGE("large", 450),
    MONSTER("monster", 600);

    String label;
    int volume;

    Size(String label, int volume) {
        this.label = label;
        this.volume = volume;
    }
}

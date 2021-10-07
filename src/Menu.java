public enum Menu {
    SERVICE_OFF("Service Menu", new String[] {"Turn On", "Leave Machine"}),
    SERVICE_ON("Service Menu", new String[] {"Fill", "Clean", "Container Levels", "Display Total Earnings", "Drink Menu", "Turn Off", "Leave Machine"});

    String title;
    String[] options;

    Menu(String title, String[] options) {
        this.title = title;
        this.options = options;
    }
}

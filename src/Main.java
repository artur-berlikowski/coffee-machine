public class Main {
    private static final UserInterface UI = new UserInterface();
    private static CoffeeMachine coffeeMachine = null;
    private static boolean on = false;

    public static void main(String[] args) {
        System.out.println("\n" + Utility.centerText("Welcome to the Coffee Machine Simulator 2021", UserInterface.MAX_WIDTH) + "\n");
        main_loop : while(true) {
            if(isOn()) {
                switch (UI.displayMenu(Menu.SERVICE_ON)) {
                    case 1 : coffeeMachine.fill(); break;
                    case 2 : coffeeMachine.clean(); break;
                    case 3 : coffeeMachine.displayIngredients(); break;
                    case 4 : coffeeMachine.start(); break;
                    case 5 : turnOff(); break;
                    case 6 : leave(); break main_loop;
                }
            } else if(!isOn()) {
                switch (UI.displayMenu(Menu.SERVICE_OFF)) {
                    case 1 : turnOn(); break;
                    case 2 : leave(); break main_loop;
                }
            }
        }
    }

    private static void turnOn() {
        System.out.println("\nBooting ...");
        if(coffeeMachine == null) { coffeeMachine = new CoffeeMachine(); }
        coffeeMachine.start();
        on = true;
    }

    private static void turnOff() {
        if(coffeeMachine != null) {
            coffeeMachine.stop();
            on = false;
        }
    }

    private static void leave() {
        System.out.println("\nThank you for using the Coffee Machine Simulator 2021");
    }

    private static boolean isOn() { return on; }
}

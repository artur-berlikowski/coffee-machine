import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static final int MAX_WIDTH = 128;

    private final char CORNER = '+';
    private final char FILLER = '-';
    private final char WRAPPER = '|';

    public int displayMenu(Menu menu) {
        displayMenuTitle(menu.title);
        displayMenuOptions(menu.options);
        return getMenuChoice(menu.options.length);
    }

    public int displayTableMenu(String title, String labelReturn, ArrayList<String> options, int numberOfColumns) {
        ArrayList<String> numericOptions = new ArrayList<>();
        ArrayList<String> table = new ArrayList<>();
        options.add(labelReturn);
        StringBuilder builder;
        int numberOfOptions = options.size();
        int numberOfRows = numberOfOptions / numberOfColumns + (numberOfOptions % numberOfColumns != 0 ? 1 : 0);
        int columnWidth = MAX_WIDTH / numberOfColumns;
        //Numeric options
        for(int i = 0; i < options.size(); i++) numericOptions.add((i+1) + ". " + options.get(i));
        //Create table
        int cell = 0;
        for(int row = 0; row < numberOfRows; row++) {
            builder = new StringBuilder();
            for(int column = 0; column < numberOfColumns; column++) {
                if(cell < numericOptions.size()) builder.append(Utility.centerText(numericOptions.get(cell), columnWidth));
                else builder.append(" ".repeat(columnWidth));
                cell++;
            }
            table.add(wrapRow(splitRow(builder.toString(), 4, WRAPPER), WRAPPER));
            table.add(getSpacerAsString());
        }

        displayMenuTitle(title);
        displaySpacer();
        Utility.printArrayListAsRows(table);

        return getMenuChoice(numericOptions.size());
    }

    public void displaySpacer() { System.out.println(wrapRow(String.valueOf(FILLER).repeat(MAX_WIDTH), CORNER)); }

    public void displayRow(String s) {
        String row = Utility.centerText(s,MAX_WIDTH);
        System.out.println(WRAPPER + row.substring(1,row.length() - 1) + WRAPPER);
    }

    public void displayMenuTitle(String title) {
        displaySpacer();
        displayRow(title);
    }

    public void displayMenuOptions(String[] options) {
        displaySpacer();
        for(int i = 0; i < options.length; i++) {
            displayRow((i+1) + ". " + options[i]);
        }
        displaySpacer();
    }

    public void displayAsTable(String[] header, ArrayList<String[]> content) {
        StringBuilder builder;
        ArrayList<String> table = new ArrayList<>();
        int maxLength = Utility.getMaxLengthFromArrayOfStrings(header);
        for(String[] row : content) {
            for (String s : row) {
                int currentLength = s.length();
                if (currentLength > maxLength) maxLength = currentLength;
            }
        }
        int columnWidth = MAX_WIDTH / header.length;
        builder = new StringBuilder();
        for(String cell : header) builder.append(Utility.centerText(cell, columnWidth));
        table.add(splitRow(builder.toString(),2,'|'));
        table.add(getSpacerAsString());
        for(String[] row : content) {
            builder = new StringBuilder();
            for(String cell : row) {
                builder.append(Utility.centerText(cell, columnWidth));
            }
            table.add(wrapRow(splitRow(builder.toString(),2,'|'), '|'));
            table.add(getSpacerAsString());
        }
        Utility.printArrayListAsRows(table);
    }

    public int getMenuChoice(int max) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
           try {
               System.out.print("Choice: ");
               choice = scanner.nextInt();
               if(choice >= 1 && choice <= max) { break; }
           } catch(NumberFormatException e) {
               scanner.nextLine();
           }
        }
        return choice;
    }

    private String getSpacerAsString() {
        String row = String.valueOf(FILLER).repeat(MAX_WIDTH);
        return CORNER + row.substring(1,row.length() - 1) + CORNER;
    }

    private String splitRow(String s, int n, char splitter) {
        StringBuilder row = new StringBuilder();
        int partLength = s.length() / n;
        for(int i = 0; i < n - 1; i++) {
            String current = s.substring(i * partLength, (i+1) * partLength);
            current = current.substring(0, current.length() - 1) + splitter;
            row.append(current);
        }
        row.append(s.substring((n-1)*partLength));
        return row.toString();
    }

    private String wrapRow(String s, char wrapper) { return wrapper + s.substring(1,s.length()-1) + wrapper; }
}

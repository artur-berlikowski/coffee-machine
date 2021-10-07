import java.util.ArrayList;

public class Utility {
    public static int getMaxLengthFromArrayOfStrings(String[] array) {
        int maxLength = 0;
        for(String s : array) {
            if(maxLength < s.length()) maxLength = s.length();
        }
        return maxLength;
    }

    public static String centerText(String s, int width) {
        StringBuilder row = new StringBuilder();
        int margin = (width - s.length()) / 2;
        row.append(" ".repeat(Math.max(0, margin)));
        row.append(s);
        row.append(" ".repeat(Math.max(0, margin)));
        row.append(" ".repeat(Math.max(0, width - row.toString().length())));
        return row.toString();
    }

    public static void printArrayListAsRows(ArrayList<String> rows) { for(String row : rows) System.out.println(row); }

    public static String capitalize(String s) {
        StringBuilder builder = new StringBuilder();
        String[] parts = s.split(" ");
        for(String part : parts) {
            builder.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
        }
        return builder.toString();
    }
}

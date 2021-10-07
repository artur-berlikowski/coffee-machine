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
        for(int i = 0; i < margin; i++) { row.append(' '); }
        row.append(s);
        for(int i = 0; i < margin; i++) { row.append(' '); }
        for(int i = 0; i < width - row.toString().length(); i++) row.append(' ');
        return row.toString();
    }

    public static void printArrayListAsRows(ArrayList<String> rows) { for(String row : rows) System.out.println(row); }

    public static String capitalize(String s) {
        StringBuilder builder = new StringBuilder();
        String[] parts = s.split(" ");
        for(String part : parts) {
            builder.append(part.substring(0,1).toUpperCase() + part.substring(1));
        }
        return builder.toString();
    }
}

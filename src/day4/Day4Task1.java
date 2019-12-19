package day4;

public class Day4Task1 {

    private static final int MIN = 137683;
    private static final int MAX = 596253;

    public static void main(String[] args) {
        int pwCount = 0;

        for (int i = MIN; i <= MAX; i++) {
            if (isValidPassword(i)) {
                pwCount++;
            }
        }
        System.out.print("There are " + pwCount + " different valid passwords in the given interval.");
    }

    private static boolean isValidPassword(int pw) {
        int prevDigit = 10, currDigit;
        boolean containsDouble = false;

        while (pw > 0) {
            currDigit = pw % 10;
            if (currDigit > prevDigit) {
                return false; // stop execution when digits decrease at any point
            }
            if (!containsDouble && currDigit == prevDigit) {
                containsDouble = true;
            }
            pw = pw / 10;
            prevDigit = currDigit;
        }
        return containsDouble;
    }


}

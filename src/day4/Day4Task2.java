package day4;

public class Day4Task2 {

    private static final int MIN = 137683;
    private static final int MAX = 596253;

    public static void main(String[] args) {
        int pwCount = 0;

        System.out.print(isValidPassword(112345));

        for (int i = MIN; i <= MAX; i++) {
            if (isValidPassword(i)) {
                pwCount++;
            }
        }
        System.out.print("There are " + pwCount + " different valid passwords in the given interval.");
    }

    private static boolean isValidPassword(int pw) {
        int prevDigit = 10, currDigit;
        int groupSize = 0; // to keep track of the size of the current digit group
        boolean containsRealDouble = false;

        while (pw > 0) {
            currDigit = pw % 10;
            if (currDigit > prevDigit) {
                return false; // stop execution when digits decrease at any point
            }
            if (!containsRealDouble) {
                if ((currDigit != prevDigit && prevDigit < 10)) {
                    containsRealDouble = groupSize == 2;
                    groupSize = 0;
                }
                groupSize++;
            }
            pw = pw / 10;
            prevDigit = currDigit;
        }
        // ternary operator for cases where left most digits form the double
        return containsRealDouble ? containsRealDouble : groupSize == 2;
    }

}

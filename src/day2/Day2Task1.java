package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day2Task1 {

    private final static int ADD = 1;
    private final static int MUL = 2;
    private final static int END = 99;

    public static void main(String[] args) {
        try {
            String inputStr = new String(Files.readAllBytes(Paths.get("src/day2/Intcode.txt")));
            int[] intCode = Arrays.stream(inputStr.split(",")).mapToInt(Integer::parseInt).toArray();
            executeIntcode(intCode);
        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
    }

    private static void executeIntcode(int[] intCode) {
        int index = 0;
        int current, firstVal, secondVal, destination;

        while (index < intCode.length) {
            current = intCode[index];
            if (current == END) {
                System.out.print("Position 0 after execution: " + intCode[0]);
                return;
            } else {
                firstVal = intCode[intCode[index + 1]];
                secondVal = intCode[intCode[index + 2]];
                destination = intCode[index + 3];
                if (current == ADD) {
                    intCode[destination] = firstVal + secondVal;
                } else if (current == MUL) {
                    intCode[destination] = firstVal * secondVal;
                }
                index += 4;
            }
        }
    }
}

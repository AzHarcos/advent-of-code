package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day2Task2 {

    private final static int ADD = 1;
    private final static int MUL = 2;
    private final static int END = 99;

    public static void main(String[] args) {
        try {
            String inputStr = new String(Files.readAllBytes(Paths.get("src/day2/Intcode.txt")));
            int[] initialCode = Arrays.stream(inputStr.split(",")).mapToInt(Integer::parseInt).toArray();
            int[] intCode;
            int result;

            for (int noun = 0; noun < 100; noun++) {
                for (int verb = 0; verb < 100; verb++) {
                    intCode = initialCode.clone();
                    intCode[1] = noun;
                    intCode[2] = verb;
                    result = executeIntcode(intCode);
                    if (result == 19690720) {
                        System.out.print("Noun "+ noun + " and verb " + verb + " found, result: " + (100 * noun + verb));
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
    }

    private static int executeIntcode(int[] intCode) {
        int index = 0;
        int current, firstVal, secondVal, destination;

        while (index < intCode.length) {
            current = intCode[index];
            if (current == END) {
                break;
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
        return intCode[0];
    }

}

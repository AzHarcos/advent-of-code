package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Day5Task2 {


    public static void main(String[] args) {
        try {
            String inputStr = new String(Files.readAllBytes(Paths.get("src/day5/Intcode.txt")));
            int[] intCode = Arrays.stream(inputStr.split(",")).mapToInt(Integer::parseInt).toArray();
            executeIntcode(intCode);
        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
    }

    private static void executeIntcode(int[] intCode) {
        Op op;
        int current, index = 0;
        int destination;
        int firstVal, secondVal;
        int[] paramModes;
        boolean jumped;

        while (index < intCode.length) {
            jumped = false;
            current = intCode[index];
            op = Op.getByOpcode(current % 100); // get Operator using last 2 digits
            paramModes = new int[op.getParamCount()];
            current = current / 100;
            for (int i = 0; i < paramModes.length && current > 0; i++) { // get param modes from the other digits
                paramModes[i] = current % 10;
                current = current / 10;
            }
            switch (op) {
                case IN:
                    destination = intCode[index + 1];
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Provide int value for input operation: ");
                    firstVal = scanner.nextInt();
                    System.out.println("Continuing with value " + firstVal + ".");
                    intCode[destination] = firstVal;
                    break;
                case OUT:
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    System.out.println(firstVal);
                    break;
                case ADD:
                    destination = intCode[index + 3];
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    secondVal = getWithParamMode(intCode, index + 2, paramModes[1]);
                    intCode[destination] = firstVal + secondVal;
                    break;
                case MUL:
                    destination = intCode[index + 3];
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    secondVal = getWithParamMode(intCode, index + 2, paramModes[1]);
                    intCode[destination] = firstVal * secondVal;
                    break;
                case IF_T:
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    secondVal = getWithParamMode(intCode, index + 2, paramModes[1]);
                    if (firstVal != 0) {
                        index = secondVal;
                        jumped = true;
                    }
                    break;
                case IF_F:
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    secondVal = getWithParamMode(intCode, index + 2, paramModes[1]);
                    if (firstVal == 0) {
                        index = secondVal;
                        jumped = true;
                    }
                    break;
                case LT:
                    destination = intCode[index + 3];
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    secondVal = getWithParamMode(intCode, index + 2, paramModes[1]);
                    intCode[destination] = firstVal < secondVal ? 1 : 0;
                    break;
                case EQ:
                    destination = intCode[index + 3];
                    firstVal = getWithParamMode(intCode, index + 1, paramModes[0]);
                    secondVal = getWithParamMode(intCode, index + 2, paramModes[1]);
                    intCode[destination] = firstVal == secondVal ? 1 : 0;
                    break;
                case END:
                    return;
                default:
                    System.out.println("Unknown operator " + op + " in Intcode program.");
            }
            if (!jumped) {
                index += op.getParamCount() + 1;
            }
        }
    }

    private static int getWithParamMode(int[] intCode, int index, int paramMode) {
        if (paramMode == 0) {
            return intCode[intCode[index]]; // position mode
        } else {
            return intCode[index];          // immediate mode
        }
    }


}

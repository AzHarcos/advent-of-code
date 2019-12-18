package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1Task1 {

    public static void main(String[] args) {
        File inputFile = new File("src/day1/Intcode.txt");
        int fuelRequirement;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            fuelRequirement = br.lines().mapToInt(e -> getFuelRequirement(Integer.parseInt(e))).sum();
            System.out.print("The total fuel requirement for all modules is " + fuelRequirement);
        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
    }

    private static int getFuelRequirement(int mass) {
        return (mass / 3) - 2;
    }

}

package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day3Task2 {

    private static final String UP = "U";
    private static final String RIGHT = "R";
    private static final String DOWN = "D";
    private static final String LEFT = "L";

    public static void main(String[] args) {
        File inputFile = new File("src/day3/cables.txt");
        int minDistance;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String[] firstPath = br.readLine().split(",");
            String[] secondPath = br.readLine().split(",");
            List<Pos> firstPositions = determinePositions(firstPath); // get all positions of first cable
            List<Pos> secondPositions = determinePositions(secondPath); // get all positions of second cable
            List<Pos> intersections = new LinkedList<>(firstPositions);
            intersections.retainAll(secondPositions); // get only shared positions
            minDistance = intersections.stream()
                            .mapToInt(p -> firstPositions.indexOf(p) + secondPositions.indexOf(p) + 2) // index in position list == steps - 1
                            .min().orElse(-1);
            System.out.print("The shortest distance from an intersection to the central port is " + minDistance);
        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
    }

    private static List<Pos> determinePositions(String[] cablePath) {
        List<Pos> positions = new ArrayList<>();
        Pos currPos = new Pos(0, 0);
        int distance;
        String direction;

        for (String segment : cablePath) {
            direction = segment.substring(0, 1);
            distance = Integer.parseInt(segment.substring(1));
            switch (direction) {
                case RIGHT:
                    for (int i = 1; i < (distance + 1); i++) {
                        currPos = new Pos(currPos.getX() + 1, currPos.getY());
                            positions.add(currPos);
                    }
                    break;
                case LEFT:
                    for (int i = 1; i < (distance + 1); i++) {
                        currPos = new Pos(currPos.getX() - 1, currPos.getY());
                            positions.add(currPos);
                    }
                    break;
                case UP:
                    for (int i = 1; i < (distance + 1); i++) {
                        currPos = new Pos(currPos.getX(), currPos.getY() + 1);
                            positions.add(currPos);
                    }
                    break;
                case DOWN:
                    for (int i = 1; i < (distance + 1); i++) {
                        currPos = new Pos(currPos.getX(), currPos.getY() - 1);
                            positions.add(currPos);
                    }
                    break;
                default: System.out.print("Unknown direction " + direction + " in cable path.");
            }
        }
        return positions;
    }

}

package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day6Task1 {

    public static void main(String[] args) {
        File inputFile = new File("src/day6/orbits.txt");
        Map<String, SpaceObject> spaceObjects = new HashMap<>();
        int orbitCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            br.lines().forEach((e -> addOrbitInfo(spaceObjects, e)));
            for (String name : spaceObjects.keySet()) {
                orbitCount += getOrbitCount(spaceObjects.get(name));
            }
        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
        System.out.print("Found " + orbitCount + " direct or indirect orbits.");
    }

    private static void addOrbitInfo(Map<String, SpaceObject> spaceObjects, String orbitInfo) {
        int sepIndex = orbitInfo.indexOf(')');
        String centerName = orbitInfo.substring(0, sepIndex);
        String outerName = orbitInfo.substring(sepIndex + 1);
        SpaceObject centerObject;

        if (spaceObjects.containsKey(centerName)) {
            centerObject = spaceObjects.get(centerName);
        } else {
            centerObject = new SpaceObject(centerName, null);
            spaceObjects.put(centerName, centerObject);
        }
        if (spaceObjects.containsKey(outerName)) {
            spaceObjects.get(outerName).setOrbitCenter(centerObject);
        } else {
            spaceObjects.put(outerName, new SpaceObject(outerName, centerObject));
        }
    }

    private static int getOrbitCount(SpaceObject spaceObject) {
        int orbitCount = 0;
        while (spaceObject.getOrbitCenter() != null) {
            orbitCount++;
            spaceObject = spaceObject.getOrbitCenter();
        }
        return orbitCount;
    }

}

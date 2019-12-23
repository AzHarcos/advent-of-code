package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6Task2 {

    public static void main(String[] args) {
        File inputFile = new File("src/day6/orbits.txt");
        Map<String, SpaceObject> spaceObjects = new HashMap<>();
        int steps = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            br.lines().forEach((e -> addOrbitInfo(spaceObjects, e)));
            List<SpaceObject> startOrbits = getAllOrbitedObjects(spaceObjects.get("YOU"));
            List<SpaceObject> targetOrbits = getAllOrbitedObjects(spaceObjects.get("SAN"));
            LinkedList<SpaceObject> commonOrbits = new LinkedList<>(startOrbits);
            commonOrbits.retainAll(targetOrbits);
            SpaceObject intersection = commonOrbits.getFirst();
            steps = startOrbits.indexOf(intersection) + targetOrbits.indexOf(intersection);
        } catch (IOException e) {
            System.out.print("Could not parse input file.");
        }
        System.out.print("Needed " + steps + " orbital transfers from YOU to SAN.");
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

    private static List<SpaceObject> getAllOrbitedObjects(SpaceObject startObject) {
        List<SpaceObject> orbitedObjectNames = new LinkedList<>();
        while (startObject.getOrbitCenter() != null) {
            startObject = startObject.getOrbitCenter();
            orbitedObjectNames.add(startObject);
        }
        return orbitedObjectNames;
    }

}

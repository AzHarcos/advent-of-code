package day6;

public class SpaceObject {

    private String name;
    private SpaceObject orbitCenter;

    public SpaceObject(String name, SpaceObject orbitCenter) {
        this.name = name;
        this.orbitCenter = orbitCenter;
    }

    public String getName() {
        return name;
    }

    public SpaceObject getOrbitCenter() {
        return orbitCenter;
    }

    public void setOrbitCenter(SpaceObject orbitCenter) {
        this.orbitCenter = orbitCenter;
    }
}

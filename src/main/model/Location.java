package model;

/**
 * Represents a named location (vertex) on the university campus graph.
 */
public class Location {
    private String name;

    /**
     * Creates a campus location with the given name.
     *
     * @param name unique location name
     */
    public Location(String name) {
        this.name = name;
    }

    /**
     * @return the location name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the location name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return a readable summary of this location
     */
    @Override
    public String toString() {
        return "Location{name='" + name + "'}";
    }
}

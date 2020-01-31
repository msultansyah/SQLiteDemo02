package id.ac.poliban.mi.sultan.sqlitedemo02.domain;

public class Friend {
    private int id;
    private String name;
    private String character;
    private String description;

    public Friend(String name, String character, String description) {
        this.name = name;
        this.character= character;
        this.description= description;
    }

    public Friend(int id, String name, String character, String description) {
        this.id = id;
        this.name = name;
        this.character= character;
        this.description= description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s", getName(), getDescription());
    }
}

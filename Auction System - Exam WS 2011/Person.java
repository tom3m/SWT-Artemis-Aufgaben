public class Person {
    private String name;

    public Person(String name) {
        if (name == "") {
            throw new IllegalArgumentException("Bieter muss einen Namen haben");
        }
        if (name == null) {
            throw new NullPointerException("Bieter muss einen Namen haben");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
import java.util.Objects;
public class Event implements Comparable<Event>{
    private String title;
    private EventCategory category;


    public Event(String title, EventCategory category) {
        if (title == null || category == null) {
            throw new NullPointerException("");
        }
        if (title == "") {
            throw new IllegalArgumentException("");
        }
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public EventCategory getCategory() {
        return category;
    }

    @Override
    public int compareTo(Event o) {
        int titleComparison = this.title.compareTo(o.title);
        if (titleComparison == 0) {
            return this.category.compareTo(o.category);
        }
        return titleComparison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Event oevent = (Event) o;
        return title.equals(oevent.title) && category.equals(oevent.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category);
    }
}
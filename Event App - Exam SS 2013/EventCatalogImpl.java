import java.util.*;
public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {
    private TreeMap<Event, Set<Time>> catalog;

    public EventCatalogImpl() {
        this.catalog = new TreeMap<>();
    }

    @Override
    public boolean addCatalogEntry(Event e, Set<Time> tSet) {
        if (e == null || tSet == null) {
            throw new NullPointerException("");
        }
        
        for (Time o : tSet) {
            if (o == null) {
                throw new NullPointerException("");
            }
        }

        if (!catalog.containsKey(e)) {
            catalog.put(e, tSet);
            return true;
        }
        return false;
    }

    @Override
    public boolean addTimeToEvent(Event e, Time t) {
        if (t == null || e == null) {
            throw new NullPointerException("");
        }
        Set<Time> times = catalog.get(e);
        if (times == null || times.contains(t)) {
            return false;
        }

        times.add(t);
        catalog.put(e, times);
        return true;
    }

    @Override
    public Set<Event> getAllEvents() {
        return catalog.keySet();
    }

    @Override
    public Set<Time> getTimesOfEvent(Event e) {
        return catalog.get(e);
    }

    @Override
    public Map<Event, Set<Time>>filterByEventCategory(EventCategory category) {
        if (category == null) {
            throw new NullPointerException("");
        }

        Map<Event, Set<Time>> result = new HashMap<>();
        for (Map.Entry<Event,Set<Time>> entry: catalog.entrySet()) {
            if (entry.getKey().getCategory() == category) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @Override
    public Set<Time> deleteEvent(Event e) {
        Set<Time> result = catalog.get(e);
        catalog.remove(e);
        return result;
    }

    @Override
    public boolean deleteTime(Event e, Time t) {
        if (t == null || e == null) {
            throw new NullPointerException("");
        }
        Set<Time> times = catalog.get(e);
        if (times == null) {
            return false;
        }
        if (times.contains(t)) {
            catalog.remove(e);
            times.remove(t);
            catalog.put(e, times);
            return true;
        } else {
            return false;
        }
    }
}
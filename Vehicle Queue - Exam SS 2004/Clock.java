import java.util.ArrayList;
import java.util.List;
public class Clock {
    private int currentTime;
    private int endOfTime;
    private List<ClockObserver> observers;

    public Clock (int endOfTime) {
        if (endOfTime < 0) {
            throw new IllegalArgumentException("endOfTime must be positive");
        }
        this.currentTime = 0;
        this.endOfTime = endOfTime;
        this.observers = new ArrayList<>();
    }

    public void addObserver(ClockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ClockObserver observer) {
        observers.remove(observer);
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void run() {
        currentTime = 0;
        while (currentTime < endOfTime) {
            currentTime++;
            tick(currentTime);
        }
    }

    private void tick(int currentTime) {
        for (ClockObserver observer : observers) {
            observer.tick(currentTime);
        }
    }
}
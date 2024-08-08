import java.util.Random;
public class VehicleGenerator {
    private Random randomGenerator;

    public VehicleGenerator() {
        this.randomGenerator = new Random();
    }

    public Vehicle createVehicle() {
        int random = randomGenerator.nextInt(3);
        switch (random) {
            case 0: return new  Bus();
            case 1: return new Car();
            case 2: return new Bicycle();
            default: return null;
        }
    }
}
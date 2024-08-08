import java.util.LinkedList;
import java.util.Queue;
public class VehicleQueue implements ClockObserver {
    private double entryDelay;
    private double exitDelay;
    private int trafficLightRate;
    private boolean greenLight = false;
    private Queue<Vehicle> queue;
    private VehicleGenerator generator;
    private double timeSinceLastEntry;
    private double timeSinceLastExit;

    public VehicleQueue(double entryDelay, double exitDelay, int trafficLightRate, VehicleGenerator generator) {
        if (entryDelay == 0 || exitDelay == 0 || trafficLightRate == 0) {
            throw new IllegalArgumentException("entryDelay and exitDelay and trafficLightRate  must be greater than 0");
        }
        if (generator == null) {
            throw new NullPointerException("Generator shouldn't be null");
        }
        if (entryDelay < 0 || exitDelay < 0 || trafficLightRate < 0) {
            throw new IllegalArgumentException("entryDelay and exitDelay and tafficLightRate must be greater than 0");
        }
        this.entryDelay = entryDelay;
        this.exitDelay = exitDelay;
        this.trafficLightRate = trafficLightRate;
        this.queue = new LinkedList<>();
        this.generator = generator;
        this.timeSinceLastEntry = 0;
        this.timeSinceLastExit = 0;
    }

    public void enter() {
        queue.add(generator.createVehicle());
    }

    public void leave() {
        queue.poll();    
    }

    public double getLength() {
        double length = 0;
        for (Vehicle vehicle : queue) {
            length = length + vehicle.getLength();
        }
        return length;
    }

    public int getSize() {
        return queue.size();
    }
    @Override
    public void tick(int currentTime) {
        System.out.println("currentTime: " + currentTime +" |trafficLightRate: " + trafficLightRate);
        /*if ((currentTime % trafficLightRate) == 0) {
            greenLight = true;
            System.out.println("Licht auf grün!!!");
        } else {
            greenLight = false;
            System.out.println("Licht auf Rot!!!");
        }*/
        

        while (timeSinceLastEntry + entryDelay <= currentTime + 0.000001) {
            enter();
            timeSinceLastEntry  += entryDelay;
            //System.out.println("Entry at "  + currrentTime);

        }

        while ((timeSinceLastExit + exitDelay <= currentTime + 0.000001) && (greenLight)) {
            leave();
            timeSinceLastExit += exitDelay;
            System.out.println("Exit at "  + currentTime);
        }

        if(currentTime % trafficLightRate == 0){
            //System.out.println("passiert das?");
            greenLight = !greenLight;
            timeSinceLastExit  = currentTime;
        }
        System.out.println(greenLight);



        /*if (greenLight && (currentTime/exitDelay) == Math.round(currentTime/exitDelay) && !queue.isEmpty()) {
            leave();
        }

        if (!greenLight && (currentTime/entryDelay) == Math.round(currentTime/entryDelay)) {
            enter();
        }*/
        
        
        /*timeSinceLastEntry ++;
        timeSinceLastExit ++;


        if ((timeSinceLastEntry*10)/10 >= entryDelay) {
            System.out.println("Enter vehicle");
            enter();
            timeSinceLastEntry = 0;
        }

        if (greenLight && (timeSinceLastExit*10)/10 >= exitDelay) {
            System.out.println("Leave vehicle");
            leave();
            timeSinceLastExit = 0;
        }*/

        /*if (currentTime % entryDelay == 0) {
            System.out.println("Enter vehicle");
            enter();
        }

        if (currentTime % exitDelay == 0 && greenLight) {
            System.out.println("Leave vehicle");
            leave();
        }*/

        System.out.println(getSize());


    }
}
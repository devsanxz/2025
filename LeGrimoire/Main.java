package LeGrimoire;

public class Main {
    public static void main(String[] args) {
        // Get Environment
        getEnvironment();
        // Calculate Mesurements
        thinkMesurements();
        // Set Sizes
        setSizes();
        // Create Objects
        makeObjects();
        // Set Behavior
        setBehavior();
        // Start Simulation
        startSimulation();
    }    
    public static void getEnvironment() {
        System.out.println("Environment setup complete.");
    }

    public static void thinkMesurements() {
        System.out.println("Measurements calculated.");
    }

    public static void setSizes() {
        System.out.println("Sizes set.");
    }

    public static void makeObjects() {
        System.out.println("Objects created.");
    }

    public static void setBehavior() {
        System.out.println("Behavior set.");
    }

    public static void startSimulation() {
        System.out.println("Simulation started.");
    }
}

package Assignment_05_Singleton;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.setFileName("src/Assignment_05_Singleton/logs/new_log.txt");

        logger.write("Simulation started");
        logger.write("Processing data...");
        logger.write("Simulation finished");

        logger.close();
    }
}

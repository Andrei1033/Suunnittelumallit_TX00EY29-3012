package Assignment_05_Singleton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance;
    private BufferedWriter writer;

    private Logger() {
        setFileName("src/Assignment_05_Singleton/logs/log.txt");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setFileName(String fileName) {
        try {
            if (writer != null) {
                writer.close();
            }
            writer = new BufferedWriter(new FileWriter(fileName));
        }
        catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    public void write(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        }
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
                writer = null;
            }
        }
        catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
}

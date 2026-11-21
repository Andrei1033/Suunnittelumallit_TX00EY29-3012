package Assignment_18_Prototype;

import Assignment_18_Prototype.service.RecommendationManager;
import Assignment_18_Prototype.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        RecommendationManager manager =
                new RecommendationManager();

        ConsoleUI ui = new ConsoleUI(manager);

        ui.start();
    }
}
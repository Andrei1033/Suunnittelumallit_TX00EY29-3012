package Assignment_01_Factory_method.map;

import java.util.Scanner;

public abstract class Map {
    public void play() {
        Scanner scanner = new Scanner(System.in);
        Map map;
        do {
            map = createMap();
            map.create();
            System.out.println("Press enter to swich the map ('q' to quit).");
        } while (!scanner.nextLine().equals("q"));
    }
    public abstract Map createMap();
}

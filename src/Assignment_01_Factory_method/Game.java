package Assignment_01_Factory_method;

import Assignment_01_Factory_method.map.CityMap;
import Assignment_01_Factory_method.map.Map;
import Assignment_01_Factory_method.map.WildernessMap;

import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Map map = createMap();
        map.display();
    }

    public static Map createMap() {
        Random random = new Random();

        if (random.nextBoolean()) {
            return new WildernessMap();
        } else {
            return new CityMap();
        }
    }
}

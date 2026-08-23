package Assignment_01_Factory_method.map;

public class WildernessMap extends Map {
    @Override
    public Map createMap() {
        return new  WildernessMap();
    }
}

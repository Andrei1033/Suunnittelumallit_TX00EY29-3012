package Assignment_01_Factory_method.map;

public class CityMap extends Map {
    @Override
    public Map createMap() {
        return new CityMap();
    }
}

package Assignment_04_Observer;

public class WeatherAlert implements WeatherObserver {
    private final String name;

    public WeatherAlert(String name) {
        this.name = name;
    }

    public WeatherAlert() {
        this.name = "Weather Alert";
    }

    @Override
    public void update(int temperature) {
        if (temperature < 0) {
            System.out.println(name + " Alert: Freezing temperature! Current temperature is: " + temperature + "°C");
        } else if (temperature > 35) {
            System.out.println(name + " Alert: Heatwave! Current temperature is: " + temperature + "°C");
        }
    }
}

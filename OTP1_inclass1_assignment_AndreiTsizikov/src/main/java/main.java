public class main {
    public static void main(String[] args) {
        double celsius = 25.0;
        double fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
        System.out.println(celsius + "°C is equal to " + fahrenheit + "°F");

        fahrenheit = 77.0;
        celsius = TemperatureConverter.fahrenheitToCelsius(fahrenheit);
        System.out.println(fahrenheit + "°F is equal to " + celsius + "°C");

        double extremeTemp = -50.0;
        boolean isExtreme = TemperatureConverter.isExtremeTemperature(extremeTemp);
        System.out.println(extremeTemp + "°C is extreme: " + isExtreme);
    }
}

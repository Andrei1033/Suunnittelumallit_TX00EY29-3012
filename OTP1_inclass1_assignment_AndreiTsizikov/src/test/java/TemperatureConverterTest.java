import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperatureConverterTest {
    @Test
    public void farenheitToCelsiusTest() {
        TemperatureConverter converter = new TemperatureConverter();

        assertEquals(0.0, converter.fahrenheitToCelsius(32.0), 0.0001);
        assertEquals(100.0, converter.fahrenheitToCelsius(212.0), 0.0001);
        assertEquals(20.0, converter.fahrenheitToCelsius(68.0), 0.0001);
    }

    @Test
    public void celsiusToFarenheitTest() {
        TemperatureConverter converter = new TemperatureConverter();

        assertEquals(32.0, converter.celsiusToFahrenheit(0.0), 0.0001);
        assertEquals(212.0, converter.celsiusToFahrenheit(100.0), 0.0001);
        assertEquals(68.0, converter.celsiusToFahrenheit(20.0), 0.0001);
    }

    @Test
    public void isExtremeTemperatureTest() {
        TemperatureConverter converter = new TemperatureConverter();

        assertEquals(false, converter.isExtremeTemperature(-40.0));
        assertEquals(false, converter.isExtremeTemperature(50.0));
        assertEquals(true, converter.isExtremeTemperature(-41.0));
        assertEquals(true, converter.isExtremeTemperature(51.0));
    }
}

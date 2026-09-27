package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperatureConverterTest {
    @Test
    public void farenheitToCelsiusTest() {
        TemperatureConverter converter = new TemperatureConverter();

        Assertions.assertEquals(0.0, converter.fahrenheitToCelsius(32.0), 0.0001);
        Assertions.assertEquals(100.0, converter.fahrenheitToCelsius(212.0), 0.0001);
        Assertions.assertEquals(20.0, converter.fahrenheitToCelsius(68.0), 0.0001);
    }

    @Test
    public void celsiusToFarenheitTest() {
        TemperatureConverter converter = new TemperatureConverter();

        Assertions.assertEquals(32.0, converter.celsiusToFahrenheit(0.0), 0.0001);
        Assertions.assertEquals(212.0, converter.celsiusToFahrenheit(100.0), 0.0001);
        Assertions.assertEquals(68.0, converter.celsiusToFahrenheit(20.0), 0.0001);
    }

    @Test
    public void kelvinToCelsiusTest() {
        TemperatureConverter converter = new TemperatureConverter();

        Assertions.assertEquals(0.0, converter.kelvinToCelsius(273.15), 0.0001);
        Assertions.assertEquals(100.0, converter.kelvinToCelsius(373.15), 0.0001);
        Assertions.assertEquals(-20.0, converter.kelvinToCelsius(253.15), 0.0001);
    }

    @Test
    public void isExtremeTemperatureTest() {
        TemperatureConverter converter = new TemperatureConverter();

        Assertions.assertEquals(false, converter.isExtremeTemperature(-40.0));
        Assertions.assertEquals(false, converter.isExtremeTemperature(50.0));
        Assertions.assertEquals(true, converter.isExtremeTemperature(-41.0));
        Assertions.assertEquals(true, converter.isExtremeTemperature(51.0));
    }
}

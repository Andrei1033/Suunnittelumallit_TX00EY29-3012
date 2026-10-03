package org.example;

import org.example.DBConnectors.TemperatureUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private static final double DELTA = 0.0001;

    private final TemperatureUnit celsius = new TemperatureUnit(1, TemperatureConverter.CELSIUS, "°C");
    private final TemperatureUnit fahrenheit = new TemperatureUnit(2, TemperatureConverter.FAHRENHEIT, "°F");
    private final TemperatureUnit kelvin = new TemperatureUnit(3, TemperatureConverter.KELVIN, "K");

    // ---------- convert ----------

    @Test
    void convertCelsiusToFahrenheit() {
        assertEquals(77.0,
                TemperatureConverter.convert(25.0, celsius, fahrenheit),
                DELTA);
    }

    @Test
    void convertFahrenheitToCelsius() {
        assertEquals(25.0,
                TemperatureConverter.convert(77.0, fahrenheit, celsius),
                DELTA);
    }

    @Test
    void convertCelsiusToKelvin() {
        assertEquals(298.15,
                TemperatureConverter.convert(25.0, celsius, kelvin),
                DELTA);
    }

    @Test
    void convertKelvinToCelsius() {
        assertEquals(25.0,
                TemperatureConverter.convert(298.15, kelvin, celsius),
                DELTA);
    }

    @Test
    void convertFahrenheitToKelvin() {
        assertEquals(273.15,
                TemperatureConverter.convert(32.0, fahrenheit, kelvin),
                DELTA);
    }

    @Test
    void convertKelvinToFahrenheit() {
        assertEquals(32.0,
                TemperatureConverter.convert(273.15, kelvin, fahrenheit),
                DELTA);
    }

    @Test
    void convertSameUnitReturnsSameValue() {
        assertEquals(42.0, TemperatureConverter.convert(42.0, celsius, celsius), DELTA);
        assertEquals(42.0, TemperatureConverter.convert(42.0, fahrenheit, fahrenheit), DELTA);
        assertEquals(42.0, TemperatureConverter.convert(42.0, kelvin, kelvin), DELTA);
    }

    @Test
    void convertNullFromThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.convert(10.0, null, celsius));
    }

    @Test
    void convertNullToThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.convert(10.0, celsius, null));
    }

    // ---------- toCelsius ----------

    @ParameterizedTest
    @CsvSource({
            "0.0,     Celsius,    0.0",
            "100.0,   Celsius,    100.0",
            "32.0,    Fahrenheit, 0.0",
            "212.0,   Fahrenheit, 100.0",
            "273.15,  Kelvin,     0.0",
            "373.15,  Kelvin,     100.0"
    })
    void toCelsius(double input, String unit, double expected) {
        assertEquals(expected, TemperatureConverter.toCelsius(input, unit), DELTA);
    }

    @Test
    void toCelsiusUnknownUnitThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.toCelsius(10.0, "Rankine"));
    }

    // ---------- fromCelsius ----------

    @ParameterizedTest
    @CsvSource({
            "0.0,    Celsius,    0.0",
            "100.0,  Celsius,    100.0",
            "0.0,    Fahrenheit, 32.0",
            "100.0,  Fahrenheit, 212.0",
            "0.0,    Kelvin,     273.15",
            "100.0,  Kelvin,     373.15"
    })
    void fromCelsius(double celsiusValue, String unit, double expected) {
        assertEquals(expected, TemperatureConverter.fromCelsius(celsiusValue, unit), DELTA);
    }

    @Test
    void fromCelsiusUnknownUnitThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.fromCelsius(10.0, "Rankine"));
    }

    // ---------- isExtremeTemperature ----------

    @Test
    void isExtremeTemperatureBoundaries() {
        assertFalse(TemperatureConverter.isExtremeTemperature(-40.0));
        assertFalse(TemperatureConverter.isExtremeTemperature(50.0));
        assertTrue(TemperatureConverter.isExtremeTemperature(-41.0));
        assertTrue(TemperatureConverter.isExtremeTemperature(51.0));
    }

    // ---------- isExtreme ----------

    @Test
    void isExtremeInCelsius() {
        assertTrue(TemperatureConverter.isExtreme(51.0, TemperatureConverter.CELSIUS));
        assertFalse(TemperatureConverter.isExtreme(20.0, TemperatureConverter.CELSIUS));
    }

    @Test
    void isExtremeInFahrenheit() {
        // 60 °F = 15.55 °C -> ei ääriarvo
        assertFalse(TemperatureConverter.isExtreme(60.0, TemperatureConverter.FAHRENHEIT));
        // 130 °F = 54.44 °C -> ääriarvo
        assertTrue(TemperatureConverter.isExtreme(130.0, TemperatureConverter.FAHRENHEIT));
    }

    @Test
    void isExtremeInKelvin() {
        // 300 K = 26.85 °C -> ei ääriarvo
        assertFalse(TemperatureConverter.isExtreme(300.0, TemperatureConverter.KELVIN));
        // 350 K = 76.85 °C -> ääriarvo
        assertTrue(TemperatureConverter.isExtreme(350.0, TemperatureConverter.KELVIN));
    }
}

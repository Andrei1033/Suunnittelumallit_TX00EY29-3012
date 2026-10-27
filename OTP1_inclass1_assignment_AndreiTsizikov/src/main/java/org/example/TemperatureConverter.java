package org.example;

import org.example.DBConnectors.TemperatureUnit;

public class TemperatureConverter {

    public static final String CELSIUS = "Celsius";
    public static final String FAHRENHEIT = "Fahrenheit";
    public static final String KELVIN = "Kelvin";

    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double EPSILON = 1e-9;

    private TemperatureConverter() {
    }

    /**
     * Muuntaa annetun arvon lähtöyksiköstä kohdeyksikköön.
     */
    public static double convert(double value, TemperatureUnit from, TemperatureUnit to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Units must not be null");
        }

        double celsius = toCelsius(value, from.getName());
        return fromCelsius(celsius, to.getName());
    }

    /**
     * Muuntaa minkä tahansa yksikön arvon Celsiukseksi.
     */
    public static double toCelsius(double value, String unitName) {
        return switch (unitName) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
            default -> throw new IllegalArgumentException("Unknown unit: " + unitName);
        };
    }

    /**
     * Muuntaa Celsiuksen minkä tahansa yksikön arvoksi.
     */
    public static double fromCelsius(double celsius, String unitName) {
        return switch (unitName) {
            case CELSIUS -> celsius;
            case FAHRENHEIT -> (celsius * 9 / 5) + 32;
            case KELVIN -> celsius + 273.15;
            default -> throw new IllegalArgumentException("Unknown unit: " + unitName);
        };
    }

    /**
     * Ääriarvotarkistus perustuu aina Celsiukseen.
     */
    public static boolean isExtremeTemperature(double celsius) {
        return celsius < -40 || celsius > 50;
    }

    /**
     * Apumetodi UI:lle: tarkistaa ääriarvon suoraan yksikön perusteella.
     */
    public static boolean isExtreme(double value, String unitName) {
        return isExtremeTemperature(toCelsius(value, unitName));
    }

    /**
     * True if the value is physically impossible (below absolute zero).
     */
    public static boolean isBelowAbsoluteZero(double value, String unitName) {
        return toCelsius(value, unitName) < ABSOLUTE_ZERO_CELSIUS - EPSILON;
    }
}

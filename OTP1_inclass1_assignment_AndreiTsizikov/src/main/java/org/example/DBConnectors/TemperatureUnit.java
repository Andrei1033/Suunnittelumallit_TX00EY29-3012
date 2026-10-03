package org.example.DBConnectors;

import java.util.Objects;

public class TemperatureUnit {

    private int id;
    private String name;
    private String symbol;

    public TemperatureUnit() {
    }

    public TemperatureUnit(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public TemperatureUnit(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemperatureUnit)) return false;
        TemperatureUnit that = (TemperatureUnit) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

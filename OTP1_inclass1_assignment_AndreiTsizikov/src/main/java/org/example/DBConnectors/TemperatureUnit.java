package org.example.DBConnectors;

import java.util.Objects;

public class TemperatureUnit {
    private int id;
    // Stable, language-independent value used by conversion logic (e.g. Celsius).
    private String name;
    private String symbol;
    private String localizedName;
    private String description;

    public TemperatureUnit() {}
    public TemperatureUnit(int id, String name, String symbol) {
        this.id = id; this.name = name; this.symbol = symbol;
        this.localizedName = name;
    }
    public TemperatureUnit(int id, String name, String symbol, String localizedName, String description) {
        this(id, name, symbol);
        this.localizedName = localizedName;
        this.description = description;
    }
    public TemperatureUnit(String name, String symbol) {
        this(0, name, symbol);
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public String getLocalizedName() { return localizedName == null ? name : localizedName; }
    public void setLocalizedName(String localizedName) { this.localizedName = localizedName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    @Override public String toString() { return getLocalizedName() + " (" + symbol + ")"; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemperatureUnit that)) return false;
        return Objects.equals(name, that.name);
    }
    @Override public int hashCode() { return Objects.hash(name); }
}

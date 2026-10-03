package org.example.DBConnectors;

import java.time.LocalDateTime;

public class TempRecord {

    private int id;
    private TemperatureUnit sourceUnit;
    private TemperatureUnit targetUnit;
    private double inputValue;
    private double outputValue;
    private LocalDateTime createdAt;

    public TempRecord() {
    }

    public TempRecord(TemperatureUnit sourceUnit,
                      TemperatureUnit targetUnit,
                      double inputValue,
                      double outputValue) {
        this.sourceUnit = sourceUnit;
        this.targetUnit = targetUnit;
        this.inputValue = inputValue;
        this.outputValue = outputValue;
    }

    public TempRecord(int id,
                      TemperatureUnit sourceUnit,
                      TemperatureUnit targetUnit,
                      double inputValue,
                      double outputValue,
                      LocalDateTime createdAt) {
        this.id = id;
        this.sourceUnit = sourceUnit;
        this.targetUnit = targetUnit;
        this.inputValue = inputValue;
        this.outputValue = outputValue;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TemperatureUnit getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(TemperatureUnit sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public TemperatureUnit getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(TemperatureUnit targetUnit) {
        this.targetUnit = targetUnit;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

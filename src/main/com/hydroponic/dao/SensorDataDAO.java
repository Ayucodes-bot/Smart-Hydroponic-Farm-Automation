package com.hydroponic.models;

import java.time.LocalDateTime;

public class SensorData {
    private int id;
    private double temperature;
    private double humidity;
    private double ph;
    private double nutrientLevel;
    private LocalDateTime recordedAt;

    public SensorData() {}

    public SensorData(double temperature, double humidity, double ph, double nutrientLevel) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.ph = ph;
        this.nutrientLevel = nutrientLevel;
        this.recordedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }
    public double getPh() { return ph; }
    public void setPh(double ph) { this.ph = ph; }
    public double getNutrientLevel() { return nutrientLevel; }
    public void setNutrientLevel(double nutrientLevel) { this.nutrientLevel = nutrientLevel; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}

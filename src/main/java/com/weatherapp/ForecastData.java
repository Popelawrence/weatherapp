package com.weatherapp;

public class ForecastData {
    private final String dateTime;
    private final double temperature;
    private final String description;

    public ForecastData(String dateTime, double temperature, String description) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f°C (%s)", dateTime, temperature, description);
    }
}


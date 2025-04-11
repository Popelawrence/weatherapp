package com.weatherapp;

public class WeatherData {
    private double highTemperature;
    private double lowTemperature;
    private int humidity;
    private double windSpeed;
    private String condition;
    private String date;

    // Existing constructor
    public WeatherData(double highTemperature, double lowTemperature, int humidity, double windSpeed, String condition, String date) {
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
        this.date = date;
    }

    // New constructor matching the signature (double, int, double, String, String)
    public WeatherData(double temperature, int humidity, double windSpeed, String condition, String date) {
        this.highTemperature = temperature;
        this.lowTemperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
        this.date = date;
    }

    public double getHighTemperature() {
        return highTemperature;
    }

    public double getLowTemperature() {
        return lowTemperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }
}
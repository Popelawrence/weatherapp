package com.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private List<String> history = new ArrayList<>();

    public void addHistory(String location, WeatherData data) {
        double tempFahrenheit = UnitConverter.celsiusToFahrenheit(data.getHighTemperature());
        String formattedTemp = String.format("%.2f", tempFahrenheit);
        history.add(location + ": " + formattedTemp + "°F, " + data.getCondition());
    }

    public String getHistory() {
        return String.join("\n", history);
    }
}
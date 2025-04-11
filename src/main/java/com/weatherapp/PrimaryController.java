package com.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.List;

public class PrimaryController {
    @FXML private TextField locationInput;
    @FXML private Label temperatureLabel;
    @FXML private ImageView temperatureIcon;
    @FXML private Label humidityLabel;
    @FXML private ImageView humidityIcon;
    @FXML private Label windSpeedLabel;
    @FXML private ImageView windSpeedIcon;
    @FXML private Label conditionLabel;
    @FXML private ImageView conditionIcon;
    @FXML private Label historyLabel;
    @FXML private VBox forecastBox;
    @FXML private Label timeLabel; // Add this label to display the current time

    private WeatherService weatherService = new WeatherService();
    private HistoryManager historyManager = new HistoryManager();

    @FXML private void initialize() {
        locationInput.setOnKeyPressed(this::handleKeyPress);
    }

    @FXML private void fetchWeather() {
        String location = locationInput.getText();
        WeatherData data = weatherService.getWeatherData(location);
        if (data != null) {
            updateWeatherDisplay(data);
            updateHistory(location, data);
            fetchForecast(location);
            updateTime(location); // Update the time when fetching weather data
        } else {
            showError("Failed to retrieve weather data.");
        }
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            fetchWeather();
        }
    }

    private void updateWeatherDisplay(WeatherData data) {
        double tempCelsius = data.getHighTemperature();
        double tempFahrenheit = UnitConverter.celsiusToFahrenheit(tempCelsius);
        temperatureLabel.setText(String.format("Temperature: %.2f°C / %.2f°F", tempCelsius, tempFahrenheit));
        humidityLabel.setText("Humidity: " + data.getHumidity() + "%");
        double windSpeedKmh = UnitConverter.metersPerSecondToKilometersPerHour(data.getWindSpeed());
        windSpeedLabel.setText(String.format("Wind Speed: %.2f m/s / %.2f km/h", data.getWindSpeed(), windSpeedKmh));
        conditionLabel.setText("Condition: " + data.getCondition());
        loadIcons(data);
    }

    private void updateHistory(String location, WeatherData data) {
        historyManager.addHistory(location, data);
        historyLabel.setText(historyManager.getHistory());
    }

    private void showError(String message) {
        temperatureLabel.setText(message);
    }

    private void loadIcons(WeatherData data) {
        temperatureIcon.setImage(new Image(getClass().getResourceAsStream("/com/weatherapp/icons/temperature.png")));
        humidityIcon.setImage(new Image(getClass().getResourceAsStream("/com/weatherapp/icons/humidity.png")));
        windSpeedIcon.setImage(new Image(getClass().getResourceAsStream("/com/weatherapp/icons/wind.png")));
        String conditionIconPath = "/com/weatherapp/icons/" + getConditionIcon(data.getCondition()) + ".png";
        try {
            conditionIcon.setImage(new Image(getClass().getResourceAsStream(conditionIconPath)));
        } catch (Exception e) {
            System.err.println("Error loading condition icon: " + e.getMessage());
            conditionIcon.setImage(new Image(getClass().getResourceAsStream("/com/weatherapp/icons/default.png")));
        }
    }

    private String getConditionIcon(String condition) {
        switch (condition.toLowerCase()) {
            case "rain": return "rain";
            case "clear": return "sun";
            case "storm": return "storm";
            case "clouds": return "clouds";
            default: return "default";
        }
    }

    private void fetchForecast(String location) {
        List<WeatherData> forecast = weatherService.getForecastData(location);
        forecastBox.getChildren().clear();
        for (WeatherData data : forecast) {
            Label forecastLabel = new Label(String.format("%s: High: %.2f°C, Low: %.2f°C, %s", data.getDate(), data.getHighTemperature(), data.getLowTemperature(), data.getCondition()));
            forecastBox.getChildren().add(forecastLabel);
        }
    }

    private void updateTime(String location) {
        String currentTime = weatherService.getCurrentTime(location);
        timeLabel.setText("Current Time: " + currentTime);
    }
}
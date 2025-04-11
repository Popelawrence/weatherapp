package com.weatherapp;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WeatherService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public WeatherData getWeatherData(String location) {
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
            String url = String.format(Config.CURRENT_WEATHER_URL, encodedLocation, Config.API_KEY);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                return new WeatherData(
                        root.path("main").path("temp").asDouble(),
                        root.path("main").path("humidity").asInt(),
                        root.path("wind").path("speed").asDouble(),
                        root.path("weather").get(0).path("main").asText(),
                        root.path("dt_txt").asText()
                );
            } else if (response.statusCode() == 404) {
                System.err.println("City not found.");
            } else if (response.statusCode() == 500) {
                System.err.println("Server error. Please try again later.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WeatherData> getForecastData(String location) {
        List<WeatherData> forecast = new ArrayList<>();
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
            String url = String.format(Config.FORECAST_URL, encodedLocation, Config.API_KEY);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                JsonNode list = root.path("list");
                Map<String, List<WeatherData>> dailyData = new HashMap<>();
                for (JsonNode node : list) {
                    String date = node.path("dt_txt").asText().split(" ")[0];
                    WeatherData data = new WeatherData(
                            node.path("main").path("temp").asDouble(),
                            node.path("main").path("humidity").asInt(),
                            node.path("wind").path("speed").asDouble(),
                            node.path("weather").get(0).path("main").asText(),
                            node.path("dt_txt").asText()
                    );
                    dailyData.computeIfAbsent(date, k -> new ArrayList<>()).add(data);
                }
                for (Map.Entry<String, List<WeatherData>> entry : dailyData.entrySet()) {
                    String date = entry.getKey();
                    List<WeatherData> dailyList = entry.getValue();
                    double highTemp = dailyList.stream().mapToDouble(WeatherData::getHighTemperature).max().orElse(0);
                    double lowTemp = dailyList.stream().mapToDouble(WeatherData::getLowTemperature).min().orElse(0);
                    int avgHumidity = (int) dailyList.stream().mapToInt(WeatherData::getHumidity).average().orElse(0);
                    double avgWindSpeed = dailyList.stream().mapToDouble(WeatherData::getWindSpeed).average().orElse(0);
                    String condition = dailyList.get(0).getCondition(); // Simplified: use the condition of the first entry
                    forecast.add(new WeatherData(highTemp, lowTemp, avgHumidity, avgWindSpeed, condition, date));
                }
            } else if (response.statusCode() == 404) {
                System.err.println("City not found.");
            } else if (response.statusCode() == 500) {
                System.err.println("Server error. Please try again later.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecast;
    }

    public String getCurrentTime(String location) {
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
            String url = String.format(Config.CURRENT_WEATHER_URL, encodedLocation, Config.API_KEY);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                int timezoneOffset = root.path("timezone").asInt();
                LocalDateTime currentTime = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.ofTotalSeconds(timezoneOffset));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return currentTime.format(formatter);
            } else if (response.statusCode() == 404) {
                System.err.println("City not found.");
            } else if (response.statusCode() == 500) {
                System.err.println("Server error. Please try again later.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Time not available";
    }
}

package com.weatherapp;

public class WeatherAPIException extends Exception {
    public WeatherAPIException(String message) {
        super(message);
    }

    public WeatherAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}

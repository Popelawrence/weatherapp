package com.weatherapp;

public class UnitConverter {
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static double metersPerSecondToKilometersPerHour(double mps) {
        return mps * 3.6;
    }

    public static double kilometersPerHourToMetersPerSecond(double kph) {
        return kph / 3.6;
    }
}
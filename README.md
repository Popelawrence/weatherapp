# Weather Information App

## Overview
The Weather Information App is a JavaFX-based desktop application that provides real-time weather updates for any city. It fetches weather data using the OpenWeatherMap API and displays essential weather details such as temperature, humidity, wind speed, and weather conditions. The app also includes dynamic icons for weather conditions, history tracking, unit conversion between Celsius and Fahrenheit, and displays the current time in the respective city.

## Features
1. **Real-Time Weather Updates:**
   - Fetches live weather data from the OpenWeatherMap API.
   - Displays temperature, humidity, wind speed, and weather conditions.

2. **Dynamic Icons:**
   - Shows icons for temperature, humidity, wind speed, and specific weather conditions (e.g., rain, sunny, storm).

3. **Unit Conversion:**
   - Displays temperature in both Celsius and Fahrenheit.

4. **History Tracking:**
   - Keeps a record of recent weather searches with timestamps for easy reference.
   - Displays temperatures in Fahrenheit.

5. **5-Day Forecast:**
   - Provides a 5-day weather forecast with high and low temperatures for each day.

6. **Current Time Display:**
   - Shows the current time in the respective city.

7. **User-Friendly GUI:**
   - Built using JavaFX with a clean and intuitive interface.

8. **Error Handling:**
   - Handles invalid location inputs or failed API requests gracefully.

## Technologies Used
- **Programming Language:** Java
- **Framework:** JavaFX
- **API Integration:** OpenWeatherMap API
- **Libraries:**
  - `javafx.controls` and `javafx.fxml` for GUI development.
  - `com.fasterxml.jackson.databind` for JSON parsing.
  - `java.net.http` for HTTP requests.

## System Requirements
- **Java Version:** JDK 17 or later
- **JavaFX Version:** 20 or later
- **Internet connection:** Required to fetch real-time weather data.

## Setup Instructions

### Prerequisites
1. Install Java JDK 17 or later.
2. Download the JavaFX SDK.
3. Obtain an API key from OpenWeatherMap.

### Project Structure
weatherapp/ ├── src/ │ └── main/ │ ├── java/ │ │ └── com/ │ │ └── weatherapp/ │ │ ├── App.java │ │ ├── PrimaryController.java │ │ ├── WeatherService.java │ │ ├── HistoryManager.java │ │ ├── UnitConverter.java │ │ └── WeatherData.java │ └── resources/ │ └── com/ │ └── weatherapp/ │ ├── primary.fxml │ ├── styles.css │ └── icons/ │ ├── temperature.png │ ├── humidity.png │ ├── wind.png │ ├── rain.png │ ├── sun.png │ ├── storm.png │ ├── clouds.png │ └── default.png ├── pom.xml


### Installation Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/weatherapp.git
   cd weatherapp
Open the project in your IDE (e.g., IntelliJ IDEA, VS Code).

Add the JavaFX SDK to your project:

Configure the module path to include the lib folder of your JavaFX SDK.
Add VM options for running JavaFX:
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
Add your OpenWeatherMap API key to the Config.java file:

public class Config {
    public static final String API_KEY = "your_api_key_here";
    public static final String CURRENT_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
    public static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric";
}
Build and run the application:

mvn clean javafx:run
How to Use
Launch the application.
Enter a city name in the input field (e.g., "San Francisco").
Click the "Get Weather" button or press "Enter".
View real-time weather details, including:
Temperature (in both Celsius and Fahrenheit).
Humidity percentage.
Wind speed in meters per second and kilometers per hour.
Weather condition with a dynamic icon.
Current time in the respective city.
Check the search history at the bottom of the app for recently queried locations.
Error Handling
If an invalid location is entered or there is no internet connection:
The app will display an error message in place of weather data.
Default icons will be used if specific icons are unavailable.
Future Enhancements
Implement geolocation-based weather updates.
Include more detailed metrics like air pressure and visibility.
Allow users to toggle between metric (m/s) and imperial (mph) units for wind speed.
License
This project is written by Lawrence Akinro and is permitted for educational purposes.# weatherapp

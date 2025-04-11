module com.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires transitive javafx.graphics; // Added to resolve the Stage warning

    opens com.weatherapp to javafx.fxml;
    exports com.weatherapp;
}

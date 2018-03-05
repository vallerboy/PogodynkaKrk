package pl.oskarpolak.weather;

import pl.oskarpolak.weather.controllers.MainController;
import pl.oskarpolak.weather.models.WeatherModel;

public class Starter {
    public static void main(String[] args) {
        new MainController().run();
    }
}

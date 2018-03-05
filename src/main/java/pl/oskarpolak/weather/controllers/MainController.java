package pl.oskarpolak.weather.controllers;

import pl.oskarpolak.weather.models.services.WeatherService;

public class MainController {
    private WeatherService weatherService = WeatherService.getInstance();

    public void run() {
        //todo test
        System.out.println(weatherService.getWeather("Kraków"));
    }
}

package pl.oskarpolak.weather.controllers;

import pl.oskarpolak.weather.models.services.WeatherService;
import pl.oskarpolak.weather.views.MainMenu;

public class MainController {
    private WeatherService weatherService = WeatherService.getInstance();
    private MainMenu mainMenu;

    public MainController(){
        mainMenu = new MainMenu();
    }

    public void run() {

        String response;
        do{
            response = mainMenu.getCityFromUser();
            mainMenu.sendMessageToConsole(String.valueOf(weatherService.getWeather(response)));
        }while (!response.equals("exit"));

    }
}

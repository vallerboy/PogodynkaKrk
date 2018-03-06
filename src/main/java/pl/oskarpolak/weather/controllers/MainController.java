package pl.oskarpolak.weather.controllers;

import pl.oskarpolak.weather.models.WeatherModel;
import pl.oskarpolak.weather.models.WeatherObserver;
import pl.oskarpolak.weather.models.services.WeatherService;
import pl.oskarpolak.weather.views.MainMenu;

public class MainController implements WeatherObserver {
    private WeatherService weatherService = WeatherService.getInstance();
    private MainMenu mainMenu;

    public MainController(){
        weatherService.registerObserver(this);
        mainMenu = new MainMenu();
    }

    public void run() {

        String response;
        do{
            response = mainMenu.getCityFromUser();
            weatherService.getWeather(response);
        }while (!response.equals("exit"));

    }

    public void onWeatherComing(WeatherModel weatherModel) {
       mainMenu.sendMessageToConsole(weatherModel.toString());
       mainMenu.cleanConsole(1);
    }
}

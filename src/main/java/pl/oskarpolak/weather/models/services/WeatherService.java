package pl.oskarpolak.weather.models.services;

import pl.oskarpolak.weather.models.Utils;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }

    private WeatherService() {
    }

    public String getWeather(String city){
        return Utils.readWebsiteContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=ef2028e98b54649bf1f4c4582631f350");
    }


}

package pl.oskarpolak.weather.models.services;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.oskarpolak.weather.models.Utils;
import pl.oskarpolak.weather.models.WeatherModel;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }

    private WeatherService() {
    }

    public WeatherModel getWeather(String city){
        String websiteResponse = Utils.readWebsiteContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=ef2028e98b54649bf1f4c4582631f350");
        String description = null;
        int temperature;
        int pressure;
        int humidity;
        int clouds;

        JSONObject root = new JSONObject(websiteResponse);
        JSONArray weatherObject = root.getJSONArray("weather");
        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject elementInArray = weatherObject.getJSONObject(i);
            description = elementInArray.getString("main");
        }

        JSONObject mainObject = root.getJSONObject("main");
        temperature = (int) mainObject.getFloat("temp");
        pressure = mainObject.getInt("pressure");
        humidity = mainObject.getInt("humidity");

        JSONObject cloudsObject = root.getJSONObject("clouds");
        clouds = cloudsObject.getInt("all");


        return new WeatherModel.Builder(city)
                .setClouds(clouds)
                .setHumidity(humidity)
                .setPressure(pressure)
                .setTemperature(temperature)
                .setWeatherComment(description)
                .build();
    }




}

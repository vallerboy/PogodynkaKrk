package pl.oskarpolak.weather.models.services;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.oskarpolak.weather.models.Utils;
import pl.oskarpolak.weather.models.WeatherModel;
import pl.oskarpolak.weather.models.WeatherObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }
    private ExecutorService executorService;
    private List<WeatherObserver> weatherObserverList;

    private WeatherService() {
        weatherObserverList = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void registerObserver(WeatherObserver weatherObserver){
        weatherObserverList.add(weatherObserver);
    }

    public void getWeather(final String city){

        executorService.execute(new Runnable() {
            public void run() {
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

                WeatherModel weatherModel = new WeatherModel.Builder(city)
                        .setTemperature(temperature)
                        .setHumidity(humidity)
                        .setPressure(pressure)
                        .setClouds(clouds)
                        .setWeatherComment(description)
                        .build();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyObservers(weatherModel);
            }
        });


    }

    private void notifyObservers(WeatherModel weatherModel) {
        for (WeatherObserver weatherObserver : weatherObserverList) {
            weatherObserver.onWeatherComing(weatherModel);
        }
    }


}

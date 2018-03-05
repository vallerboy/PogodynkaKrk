package pl.oskarpolak.weather;

import pl.oskarpolak.weather.controllers.MainController;
import pl.oskarpolak.weather.models.WeatherModel;

public class Starter {
    public static void main(String[] args) {
        new MainController().run();

        WeatherModel weatherModel = new WeatherModel.Builder("Kraków")
                .setPressure(1000)
                .setHumidity(90)
                .setTemperature(20)
                .setWeatherComment("Ciemno")
                .build();

        System.out.println(weatherModel.getTemperature());
    }
}

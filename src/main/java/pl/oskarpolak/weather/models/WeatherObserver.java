package pl.oskarpolak.weather.models;

public interface WeatherObserver {
    void onWeatherComing(WeatherModel weatherModel);
}

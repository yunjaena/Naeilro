package com.koreatech.naeilro.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreatech.naeilro.ui.weather.WeatherCityInfo;

import java.util.List;

public class CurrentWeather {
    @SerializedName("coord")
    @Expose
    private Coordinate coordinate;

    @SerializedName("weather")
    @Expose
    List<Weather> currentWeather;

    private WeatherCityInfo weatherCityInfo;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public List<Weather> getCurrentWeather() {
        return currentWeather;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setCurrentWeather(List<Weather> currentWeather) {
        this.currentWeather = currentWeather;
    }

    public WeatherCityInfo getWeatherCityInfo() {
        return weatherCityInfo;
    }

    public void setWeatherCityInfo(WeatherCityInfo weatherCityInfo) {
        this.weatherCityInfo = weatherCityInfo;
    }
}

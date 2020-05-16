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
    private List<Weather> currentWeather;

    @SerializedName("main")
    @Expose
    private WeatherMain weatherMain;

    @SerializedName("visibility")
    @Expose
    private int visibility;


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

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(WeatherMain weatherMain) {
        this.weatherMain = weatherMain;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}

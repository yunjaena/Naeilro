package com.koreatech.naeilro.network.entity.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DailyWeather {
    @SerializedName("dt")
    @Expose
    private int dayTime;

    @SerializedName("temp")
    @Expose
    private Temperature temperature;


    @SerializedName("weather")
    @Expose
    private List<Weather> weather;

    public int getDayTime() {
        return dayTime;
    }

    public void setDayTime(int dayTime) {
        this.dayTime = dayTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}

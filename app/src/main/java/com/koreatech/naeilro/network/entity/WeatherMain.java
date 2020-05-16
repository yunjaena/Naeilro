package com.koreatech.naeilro.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherMain {
    @SerializedName("temp")
    @Expose
    private double temperature;

    @SerializedName("feels_like")
    @Expose
    private double feelLikeTemperature;

    @SerializedName("temp_min")
    @Expose
    private double minTemperature;

    @SerializedName("temp_max")
    @Expose
    private double maxTemperature;

    @SerializedName("pressure")
    @Expose
    private int pressure;

    @SerializedName("humidity")
    @Expose
    private int humidity;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelLikeTemperature() {
        return feelLikeTemperature;
    }

    public void setFeelLikeTemperature(double feelLikeTemperature) {
        this.feelLikeTemperature = feelLikeTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

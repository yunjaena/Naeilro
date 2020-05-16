package com.koreatech.naeilro.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("main")
    @Expose
    private String currentWeather;

    @SerializedName("description")
    @Expose
    private String currentWeatherDescription;

    @SerializedName("icon")
    @Expose
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getCurrentWeatherDescription() {
        return currentWeatherDescription;
    }

    public void setCurrentWeatherDescription(String currentWeatherDescription) {
        this.currentWeatherDescription = currentWeatherDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

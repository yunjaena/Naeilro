package com.koreatech.naeilro.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("day")
    @Expose
    private double dayTemperature;

    @SerializedName("min")
    @Expose
    private double dayMinTemperature;

    @SerializedName("max")
    @Expose
    private double dayMaxTemperature;

    @SerializedName("night")
    @Expose
    private double dayNightTemperature;

    @SerializedName("eve")
    @Expose
    private double dayEveningTemperature;

    @SerializedName("morn")
    @Expose
    private double dayMorningTemperature;

    public double getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(double dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public double getDayMinTemperature() {
        return dayMinTemperature;
    }

    public void setDayMinTemperature(double dayMinTemperature) {
        this.dayMinTemperature = dayMinTemperature;
    }

    public double getDayMaxTemperature() {
        return dayMaxTemperature;
    }

    public void setDayMaxTemperature(double dayMaxTemperature) {
        this.dayMaxTemperature = dayMaxTemperature;
    }

    public double getDayNightTemperature() {
        return dayNightTemperature;
    }

    public void setDayNightTemperature(double dayNightTemperature) {
        this.dayNightTemperature = dayNightTemperature;
    }

    public double getDayEveningTemperature() {
        return dayEveningTemperature;
    }

    public void setDayEveningTemperature(double dayEveningTemperature) {
        this.dayEveningTemperature = dayEveningTemperature;
    }

    public double getDayMorningTemperature() {
        return dayMorningTemperature;
    }

    public void setDayMorningTemperature(double dayMorningTemperature) {
        this.dayMorningTemperature = dayMorningTemperature;
    }
}

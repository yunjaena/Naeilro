package com.koreatech.naeilro.util;

public class TemperatureUtil {
    public static double celsiusToFahrenheit(double degree) {
        return (degree * 1.8) + 32.0;
    }

    public static double fahrenheitToCelsius(double degree) {
        return (degree - 32.0) / 1.8;
    }

    public static double kelvinToCelsius(double degree){
        return degree - 273.15;
    }

    public static double kelvinToFahrenheit(double degree){
        return celsiusToFahrenheit(kelvinToCelsius(degree));
    }
}

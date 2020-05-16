package com.koreatech.naeilro.ui.weather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WeatherCityInfo {
    SEOUL("서울", 37.541361, 127.008998),
    INCHEON("인천", 37.4647398, 126.5342663),
    CHUNCHEON("춘천", 37.8688626, 127.6973306),
    WONJU("원주", 37.3500631, 127.9055806),
    SOGCHO("속초", 38.2047122, 128.5535806),
    GANGLEUNG("강릉", 37.7637627, 128.8649806),
    CHUNGJU("충주", 36.9858634, 127.8923306),
    DAEJEON("대전", 36.3732178, 127.3187601),
    ANDONG("안동", 36.5628174, 128.6567601),
    GIMCHEON("김천", 36.1358642, 128.0785806),
    GUNSAN("군산", 35.9659686, 126.6410101),
    DAEGU("대구", 35.8799469, 128.4266162),
    POHANG("포항", 36.0194185, 129.2995601),
    ULSAN("울산", 35.5622483, 129.2114161),
    MOKPO("목포", 34.8029209, 126.3501601),
    GWANGJU("광주", 35.1769, 126.7037161),
    YEOSU("여수", 34.752621, 127.6218101),
    JINJU("진주", 35.1823151, 128.0548306),
    TONGYEONG("통영", 34.8561154, 128.3837306),
    BUSAN("부산", 35.1646501, 128.9317161);


    private String cityName;
    private double locationLongitude;
    private double locationLatitude;

    WeatherCityInfo(String cityName, double locationLongitude, double locationLatitude) {
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public double getLocationLongitude() {
        return this.locationLongitude;
    }

    public double getLocationLatitude() {
        return this.locationLatitude;
    }

    public static List<WeatherCityInfo> getWeatherCityInfoList() {
        return new ArrayList<>(Arrays.asList(WeatherCityInfo.values()));
    }

    public static WeatherCityInfo getWeatherCityInfoByCItyName(String cityName) {
        for (WeatherCityInfo weatherCityInfo : WeatherCityInfo.values()) {
            if (weatherCityInfo.cityName.equals(cityName)) {
                return weatherCityInfo;
            }
        }
        return null;
    }


}

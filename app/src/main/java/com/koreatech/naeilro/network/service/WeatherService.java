package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.weather.CurrentWeather;
import com.koreatech.naeilro.network.entity.weather.OneWeekWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    String WEATHER_TODAY_PATH = "weather";
    String WEATHER_ONE_WEEK_PATH = "onecall";

    @GET(WEATHER_TODAY_PATH)
    @Json
    Observable<CurrentWeather> getCurrentWeather(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String apiKey, @Query("lang") String responseLanguage);

    @GET(WEATHER_ONE_WEEK_PATH)
    @Json
    Observable<OneWeekWeather> getOneWeekWeather(@Query("lat") double latitude, @Query("lon") double longitude, @Query("exclude") String exclude, @Query("appid") String apiKey, @Query("lang") String responseLanguage);

}

package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.CurrentWeather;
import com.koreatech.naeilro.network.entity.TrainArrivalDepartInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    String WEATHER_TODAY_PATH = "weather";
    @GET(WEATHER_TODAY_PATH)
    @Json
    Observable<CurrentWeather> getTrainArrivalDepartInfo(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String apiKey, @Query("lang") String responseLanguage);


}

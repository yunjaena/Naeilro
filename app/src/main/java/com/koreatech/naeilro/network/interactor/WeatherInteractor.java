package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.ui.weather.WeatherCityInfo;

import java.util.List;

public interface WeatherInteractor {
    void getCurrentWeather(List<WeatherCityInfo> weatherCityInfos, String responseLanguage, ApiCallback apiCallback);
}

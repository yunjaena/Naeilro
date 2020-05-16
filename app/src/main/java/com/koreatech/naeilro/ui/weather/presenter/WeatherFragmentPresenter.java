package com.koreatech.naeilro.ui.weather.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.CurrentWeather;
import com.koreatech.naeilro.network.interactor.WeatherInteractor;
import com.koreatech.naeilro.ui.weather.WeatherCityInfo;

import java.util.List;

public class WeatherFragmentPresenter {
    private WeatherFragmentContract.View weatherFragmentView;
    private WeatherInteractor weatherInteractor;

    public WeatherFragmentPresenter(WeatherFragmentContract.View weatherFragmentView, WeatherInteractor weatherInteractor) {
        this.weatherFragmentView = weatherFragmentView;
        this.weatherInteractor = weatherInteractor;
        weatherFragmentView.setPresenter(this);
    }

    final ApiCallback currentWeatherApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {

             List<CurrentWeather> currentWeather = (List<CurrentWeather>) object;
             weatherFragmentView.showTodayWeatherInfo(currentWeather);

        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

    public void getCurrentWeatherInfo(List<WeatherCityInfo> weatherCityInfoList, String responseLanguage) {
        weatherInteractor.getCurrentWeather(weatherCityInfoList, responseLanguage, currentWeatherApiCallback);
    }
}

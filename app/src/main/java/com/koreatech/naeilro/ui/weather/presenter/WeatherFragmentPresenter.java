package com.koreatech.naeilro.ui.weather.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.weather.CurrentWeather;
import com.koreatech.naeilro.network.entity.weather.OneWeekWeather;
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
            weatherFragmentView.hideLoading();

        }

        @Override
        public void onFailure(Throwable throwable) {
            weatherFragmentView.showMessage(R.string.error_network);
            weatherFragmentView.hideLoading();
        }
    };

    final ApiCallback oneWeekWeatherApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            OneWeekWeather oneWeekWeather = (OneWeekWeather) object;
            weatherFragmentView.showOnwWeekWeatherInfo(oneWeekWeather);
            weatherFragmentView.hideLoading();

        }

        @Override
        public void onFailure(Throwable throwable) {
            weatherFragmentView.showMessage(R.string.error_network);
            weatherFragmentView.hideLoading();
        }
    };

    public void getCurrentWeatherInfo(List<WeatherCityInfo> weatherCityInfoList, String responseLanguage) {
        weatherFragmentView.showLoading();
        weatherInteractor.getCurrentWeather(weatherCityInfoList, responseLanguage, currentWeatherApiCallback);
    }

    public void getOneWeekWeatherInfo(WeatherCityInfo weatherCityInfo, String responseLanguage){
        weatherFragmentView.showLoading();
        weatherInteractor.getOneWeekWeather(weatherCityInfo,"hourly,minutely,hourly,current", responseLanguage, oneWeekWeatherApiCallback);
    }
}

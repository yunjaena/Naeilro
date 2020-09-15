package com.koreatech.naeilro.ui.weather.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.weather.CurrentWeather;
import com.koreatech.naeilro.network.entity.weather.OneWeekWeather;

import java.util.List;

public interface WeatherFragmentContract {
    interface View extends BaseView<WeatherFragmentPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showOnwWeekWeatherInfo(OneWeekWeather oneWeekWeather);

        void showTodayWeatherInfo(List<CurrentWeather> currentWeatherList);
    }
}

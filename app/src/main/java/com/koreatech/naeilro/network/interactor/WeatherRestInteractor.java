package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.WeatherRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.weather.CurrentWeather;
import com.koreatech.naeilro.network.entity.weather.OneWeekWeather;
import com.koreatech.naeilro.network.entity.weather.Weather;
import com.koreatech.naeilro.network.service.WeatherService;
import com.koreatech.naeilro.ui.weather.WeatherCityInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class WeatherRestInteractor implements WeatherInteractor {
    public static final String TAG = "WeatherRestInteractor";
    public static final String iconURL = "http://openweathermap.org/img/wn/";

    @Override
    public void getCurrentWeather(List<WeatherCityInfo> weatherCityInfos, String responseLanguage, ApiCallback apiCallback) {

        String apiKey = NaeilroApplication.getWeatherApiKey();
        ArrayList<CurrentWeather> responseList = new ArrayList<>();
        ArrayList<Observable<CurrentWeather>> requestList = new ArrayList<>();

        for (WeatherCityInfo weatherCityInfo : weatherCityInfos) {
            Observable<CurrentWeather> observable = WeatherRetrofitManager.getInstance().getRetrofit().create(WeatherService.class).
                    getCurrentWeather(weatherCityInfo.getLocationLatitude(), weatherCityInfo.getLocationLongitude(), apiKey, responseLanguage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(currentWeather -> {
                        currentWeather.setWeatherCityInfo(weatherCityInfo);
                        return currentWeather;
                    });
            requestList.add(observable);
        }


        Observable.merge(requestList).subscribe(new Observer<CurrentWeather>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(final CurrentWeather response) {
                if (response != null) {
                    List<Weather> weather = response.getCurrentWeather();
                    if (weather != null && weather.get(0).getIcon() != null) {
                        String icon = weather.get(0).getIcon();
                        icon = iconURL + icon + ".png";
                        weather.get(0).setIcon(icon);
                        response.setCurrentWeather(weather);
                    }
                    responseList.add(response);
                } else {
                    apiCallback.onFailure(new Throwable("fail read weather info"));
                }
            }


            @Override
            public void onError(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    Log.d(TAG, ((HttpException) throwable).code() + " ");
                }
                apiCallback.onFailure(throwable);
            }

            @Override
            public void onComplete() {
                apiCallback.onSuccess(responseList);
            }
        });
    }

    @Override
    public void getOneWeekWeather(WeatherCityInfo weatherCityInfo, String exclude, String responseLanguage, ApiCallback apiCallback) {
        String apiKey = NaeilroApplication.getWeatherApiKey();
        Observable<OneWeekWeather> observable = WeatherRetrofitManager.getInstance().getRetrofit().create(WeatherService.class).
                getOneWeekWeather(weatherCityInfo.getLocationLatitude(), weatherCityInfo.getLocationLongitude(), exclude, apiKey, responseLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(currentWeather -> {
                    currentWeather.setCity(weatherCityInfo.getCityName());
                    return currentWeather;
                });

        observable.subscribe(new Observer<OneWeekWeather>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(final OneWeekWeather response) {
                if (response != null) {
                    for (int i = 0; i < response.getDailyWeather().size(); i++) {
                        String icon = response.getDailyWeather().get(i).getWeather().get(0).getIcon();
                        icon = iconURL + icon + ".png";
                        response.getDailyWeather().get(i).getWeather().get(0).setIcon(icon);
                    }
                    apiCallback.onSuccess(response);
                } else {
                    apiCallback.onFailure(new Throwable("fail read weather info"));
                }
            }


            @Override
            public void onError(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    Log.d(TAG, ((HttpException) throwable).code() + " ");
                }
                apiCallback.onFailure(throwable);
            }

            @Override
            public void onComplete() {
            }
        });
    }

}

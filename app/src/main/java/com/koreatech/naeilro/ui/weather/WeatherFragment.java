package com.koreatech.naeilro.ui.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.weather.CurrentWeather;
import com.koreatech.naeilro.network.entity.weather.OneWeekWeather;
import com.koreatech.naeilro.network.interactor.WeatherRestInteractor;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.weather.presenter.WeatherFragmentContract;
import com.koreatech.naeilro.ui.weather.presenter.WeatherFragmentPresenter;
import com.koreatech.naeilro.util.BitmapUtil;
import com.koreatech.naeilro.util.TemperatureUtil;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class WeatherFragment extends Fragment implements WeatherFragmentContract.View, TMapView.OnCalloutRightButtonClickCallback {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat = 36.41592967015607;
    private WeatherFragmentPresenter weatherFragmentPresenter;
    private LinearLayout tMapLinearLayout;
    private TMapView tMapView;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        init(view);
        return view;
    }

    public void init(View view) {
        handler = new Handler(Looper.getMainLooper());
        tMapLinearLayout = view.findViewById(R.id.weather_t_map_linear_layout);
        new WeatherFragmentPresenter(this, new WeatherRestInteractor());
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapLinearLayout.addView(tMapView);
        tMapView.setZoomLevel(6);
        tMapView.setUserScrollZoomEnable(true);
        tMapView.setCenterPoint(centerLon, centerLat);
        tMapView.setOnCalloutRightButtonClickListener(this);
        weatherFragmentPresenter.getCurrentWeatherInfo(WeatherCityInfo.getWeatherCityInfoList(), "kr");
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showProgressDialog(R.string.loading);
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showMessage(int message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showTodayWeatherInfo(List<CurrentWeather> currentWeatherList) {
        tMapView.removeAllMarkerItem();
        for (CurrentWeather currentWeather : currentWeatherList) {
            addWeatherMarker(currentWeather);
        }
    }

    @Override
    public void setPresenter(WeatherFragmentPresenter presenter) {
        this.weatherFragmentPresenter = presenter;
    }

    public void addWeatherMarker(final CurrentWeather currentWeather) {
        Glide.with(this)
                .asBitmap()
                .load(currentWeather.getCurrentWeather().get(0).getIcon())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap markerBitmap = BitmapUtil.getRoundedCornerBitmapWithStroke(BitmapUtil.transParentBackgroundToWhiteBackground(resource), Color.BLACK, 2);
                        Bitmap rightClickBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_zoom_in_white_18dp);
                        TMapMarkerItem markerItem = new TMapMarkerItem();
                        TMapPoint tMapPoint1 = new TMapPoint(currentWeather.getWeatherCityInfo().getLocationLatitude(), currentWeather.getWeatherCityInfo().getLocationLongitude());
                        markerItem.setIcon(markerBitmap); // 마커 아이콘 지정
                        markerItem.setVisible(TMapMarkerItem.VISIBLE);
                        markerItem.setPosition(0f, 0f);
                        markerItem.setTMapPoint(tMapPoint1);
                        markerItem.setName(currentWeather.getWeatherCityInfo().getCityName());
                        markerItem.setCanShowCallout(true);
                        markerItem.setCalloutTitle(currentWeather.getWeatherCityInfo().getCityName());
                        markerItem.setCalloutSubTitle(getWeatherInfoToString(currentWeather));
                        markerItem.setCalloutRightButtonImage(rightClickBitmap);
                        tMapView.addMarkerItem(currentWeather.getWeatherCityInfo().getCityName(), markerItem);
                        tMapView.initView();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    public String getWeatherInfoToString(CurrentWeather currentWeather) {
        StringBuilder weatherInfoStringBuilder = new StringBuilder();
        weatherInfoStringBuilder
                .append(getResources().getString(R.string.current_weather_status)).append(" : ").append(currentWeather.getCurrentWeather().get(0).getCurrentWeatherDescription()).append("\n")
                .append(getResources().getString(R.string.current_weather_temperature)).append(" : ").append(String.format(Locale.KOREA,"%.0f",TemperatureUtil.kelvinToCelsius(currentWeather.getWeatherMain().getTemperature()))).append("\n")
//                .append(getResources().getString(R.string.current_weather_max_temperature)).append(" : ").append(TemperatureUtil.kelvinToCelsius(currentWeather.getWeatherMain().getMaxTemperature())).append("\n")
//                .append(getResources().getString(R.string.current_weather_min_temperature)).append(" : ").append(TemperatureUtil.kelvinToCelsius(currentWeather.getWeatherMain().getMinTemperature())).append("\n")
//                .append(getResources().getString(R.string.current_weather_pressure)).append(" : ").append(currentWeather.getWeatherMain().getHumidity()).append("\n")
//                .append(getResources().getString(R.string.current_weather_pressure)).append(" : ").append(currentWeather.getWeatherMain().getPressure()).append("\n");
        ;
        return weatherInfoStringBuilder.toString();
    }

    @Override
    public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
        String markerName = tMapMarkerItem.getName();
        WeatherCityInfo weatherCityInfo = WeatherCityInfo.getWeatherCityInfoByCItyName(markerName);
        weatherFragmentPresenter.getOneWeekWeatherInfo(weatherCityInfo,"kr");

    }

    @Override
    public void showOnwWeekWeatherInfo(OneWeekWeather oneWeekWeather) {
        WeatherInfoDialog weatherInfoDialog = new WeatherInfoDialog(getActivity(), oneWeekWeather);
        weatherInfoDialog.show();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(weatherInfoDialog.getWindow().getAttributes());
        lp.width = (int) (size.x * 0.8f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        weatherInfoDialog.show();
        Window window = weatherInfoDialog.getWindow();
        window.setAttributes(lp);

    }
}

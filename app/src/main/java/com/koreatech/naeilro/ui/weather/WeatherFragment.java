package com.koreatech.naeilro.ui.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.CurrentWeather;
import com.koreatech.naeilro.network.interactor.WeatherRestInteractor;
import com.koreatech.naeilro.ui.weather.presenter.WeatherFragmentContract;
import com.koreatech.naeilro.ui.weather.presenter.WeatherFragmentPresenter;
import com.koreatech.naeilro.util.BitmapUtil;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int message) {

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
        weatherInfoStringBuilder.append(getResources().getString(R.string.current_weather_status)).append(" : ").append(currentWeather.getCurrentWeather().get(0).getCurrentWeatherDescription())
        .append("\n").append("");
        return weatherInfoStringBuilder.toString();
    }

    @Override
    public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
        String markerName = tMapMarkerItem.getName();
        WeatherCityInfo weatherCityInfo = WeatherCityInfo.getWeatherCityInfoByCItyName(markerName);
        ToastUtil.getInstance().makeShort(String.format("위치 : %s 위도 : %f 경도 %f", weatherCityInfo.getCityName(), weatherCityInfo.getLocationLatitude(),
                weatherCityInfo.getLocationLongitude()));
    }
}

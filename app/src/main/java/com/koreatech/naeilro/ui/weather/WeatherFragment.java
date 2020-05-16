package com.koreatech.naeilro.ui.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.util.BitmapUtil;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.Objects;


public class WeatherFragment extends Fragment {
    private static final double centerLon = 127.48318433761597;
    private static final double centerLat  = 36.41592967015607;
    private LinearLayout tMapLinearLayout;
    private TMapView tMapView;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        init(view);
        return view;
    }

    public void init(View view){
        handler = new Handler(Looper.getMainLooper());
        tMapLinearLayout = view.findViewById(R.id.weather_t_map_linear_layout);
        tMapView = new TMapView(Objects.requireNonNull(getActivity()));
        tMapView.setSKTMapApiKey(NaeilroApplication.getTMapApiKey());
        tMapLinearLayout.addView(tMapView);
        tMapView.setZoomLevel(6);
        tMapView.setUserScrollZoomEnable(true);
        tMapView.setCenterPoint(centerLon, centerLat);
        tMapView.setOnDisableScrollWithZoomLevelListener(
                (zoom, centerPoint) ->
                        handler.postDelayed(() ->
                        ToastUtil.getInstance().makeShort("zoomLevel=" + zoom + "\nlon=" + centerPoint.getLongitude() + "\nlat=" + centerPoint.getLatitude()),0)
        );

        for(WeatherCityInfo weatherCityInfo : WeatherCityInfo.values()){
            addWeatherMarker("http://openweathermap.org/img/wn/50n.png", weatherCityInfo.getLocationLongitude(), weatherCityInfo.getLocationLatitude(), weatherCityInfo.getCityName());
        }
    }


    public void addWeatherMarker(final String weatherIconUrl, final double LocationLongitude, final double LocationLatitude, String name){
        Glide.with(this)
                .asBitmap()
                .load(weatherIconUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Bitmap markerBitmap = BitmapUtil.getRoundedCornerBitmap(BitmapUtil.transParentBackgroundToWhiteBackground(resource));
                            TMapMarkerItem markerItem1 = new TMapMarkerItem();
                            TMapPoint tMapPoint1 = new TMapPoint(LocationLatitude, LocationLongitude); // SKT타워
                            markerItem1.setIcon(markerBitmap); // 마커 아이콘 지정
                            markerItem1.setPosition(0f, 0f); // 마커의 중심점을 중앙, 하단으로 설정
                            markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
                            markerItem1.setName(name); // 마커의 타이틀 지정
                            markerItem1.setCanShowCallout(true);
                            markerItem1.setCalloutTitle(name);
                            tMapView.addMarkerItem(name, markerItem1); // 지도에 마커 추가
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }
}

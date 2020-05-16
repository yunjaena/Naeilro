package com.koreatech.naeilro;

import android.app.Application;
import android.content.Context;

import com.koreatech.core.toast.ToastUtil;

public class NaeilroApplication  extends Application {
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        init();
    }

    public void init(){
        ToastUtil.getInstance().init(applicationContext);
    }

    public static String getDataApiKey(){
        return applicationContext.getResources().getString(R.string.data_api_key);
    }

    public static String getTMapApiKey(){
        return applicationContext.getResources().getString(R.string.t_map_key);
    }

    public static String getWeatherApiKey(){
        return applicationContext.getResources().getString(R.string.weather_api_key);
    }
}

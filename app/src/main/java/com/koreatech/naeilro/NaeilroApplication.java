package com.koreatech.naeilro;

import android.app.Application;
import android.content.Context;

public class NaeilroApplication  extends Application {
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
    }

    public static String getDataApiKey(){
        return applicationContext.getResources().getString(R.string.data_api_key);
    }
}

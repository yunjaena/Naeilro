package com.koreatech.core.network;

import android.annotation.SuppressLint;

import com.koreatech.core.BuildConfig;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MyPlanRetrofitManager {
    private static MyPlanRetrofitManager instance = null;

    public static final String BASE_URL_PRODUCTION = "http://13.124.205.66:8001"; //release server
    public static final String BASE_URL_STAGE = "http://13.124.205.66:8001"; //development server

    private Retrofit retrofit;

    private MyPlanRetrofitManager() {
    }

    public static MyPlanRetrofitManager getInstance() {
        if (instance == null) {
            synchronized (MyPlanRetrofitManager.class) {
                if (instance == null) {
                    instance = new MyPlanRetrofitManager();
                }
            }
        }
        return instance;
    }

    public void init() {
        retrofit = getDefaultRetrofitSetting();
    }

    private Retrofit getDefaultRetrofitSetting() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).
                addInterceptor(new UTFDecodeInterceptor()).build();
        try {
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
            HostnameVerifier hv = (hostname, session) -> true;
            String workerClassName = "okhttp3.OkHttpClient";
            try {
                Class workerClass = Class.forName(workerClassName);
                Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
                hostnameVerifier.setAccessible(true);
                hostnameVerifier.set(httpClient, hv);
                Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
                sslSocketFactory.setAccessible(true);
                sslSocketFactory.set(httpClient, sslContext.getSocketFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (BuildConfig.IS_DEBUG) {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL_STAGE)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new XmlOrJsonConverterFactory())
                    .client(httpClient)
                    .build();
        } else {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL_PRODUCTION)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new XmlOrJsonConverterFactory())
                    .client(httpClient)
                    .build();
        }
    }

    public Retrofit getRetrofit() {
        if (retrofit != null)
            return retrofit;
        else {
            init();
            return retrofit;
        }
    }

    public static String addAuthorizationBearer(String token) {
        return "Bearer " + token;
    }
}

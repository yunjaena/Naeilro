package com.koreatech.naeilro.auth;

import android.content.Context;

import com.koreatech.core.sharedpreference.BaseSharedPreferencesHelper;

public class TokenSharedPreference extends BaseSharedPreferencesHelper {
    public static final String TAG = "TokenSharedPreference";
    private static volatile TokenSharedPreference instance = null;
    public final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final String TOKEN_SHARED_PREFEREMCE = "com.koreatech.naeilro.token";

    public TokenSharedPreference() {
        sharedPreferences = null;
    }

    public static TokenSharedPreference getInstance() {
        if (instance == null) {
            synchronized (TokenSharedPreference.class) {
                instance = new TokenSharedPreference();
            }
        }
        return instance;
    }

    public void init(Context context) {
        super.init(context, context.getSharedPreferences(TOKEN_SHARED_PREFEREMCE, Context.MODE_PRIVATE));
    }

    public void saveAccessToken(String accessToken) {
        putString(ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
        return getString(ACCESS_TOKEN, "");
    }

    public void saveRefreshToken(String refreshToken) {
        putString(REFRESH_TOKEN, refreshToken);
    }

    public String getRefreshToken() {
        return getString(REFRESH_TOKEN, "");
    }

    public void deleteToken(){
        remove(REFRESH_TOKEN);
        remove(ACCESS_TOKEN);
    }


}

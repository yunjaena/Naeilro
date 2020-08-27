package com.koreatech.naeilro.auth;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import com.koreatech.core.network.UserRetrofitManager;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.user.Token;
import com.koreatech.naeilro.network.service.UserService;
import com.koreatech.naeilro.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTTokenManager implements JWTToken {
    public static final String TAG = "JWTTokenManager";
    private Context applicationContext;

    private JWTTokenManager() {
    }

    public static JWTTokenManager getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        if (applicationContext == null) {
            applicationContext = context.getApplicationContext();
            TokenSharedPreference.getInstance().init(applicationContext);
            if (getRefreshToken() != null && isRefreshTokenExpiredInOneWeek()) {
                updateToken();
            }
        }
    }

    @Override
    public String getRefreshToken() {
        return TokenSharedPreference.getInstance().getRefreshToken();
    }

    @Override
    public String getAccessToken() {
        if (getRefreshToken() != null && !isRefreshTokenExpired() && isAccessTokenExpired()) {
            updateToken();
        } else if (isRefreshTokenExpired()) {
            refreshTokenExpiredAction();
            return "";
        }

        return TokenSharedPreference.getInstance().getAccessToken();
    }

    @Override
    public void saveRefreshToken(String token) {
        TokenSharedPreference.getInstance().saveRefreshToken(token);
    }

    @Override
    public void saveAccessToken(String token) {
        TokenSharedPreference.getInstance().saveAccessToken(token);
    }

    @Override
    public void updateToken() {
        refreshToken();
    }

    @Override
    public boolean isAccessTokenExpired() {
        return isExpired(getAccessTokenExpiredDate());
    }

    @Override
    public boolean isRefreshTokenExpired() {
        return isExpired(getRefreshTokenExpiredDate());
    }

    @Override
    public void refreshTokenExpiredAction() {
        Intent intent = new Intent(applicationContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ToastUtil.getInstance().makeShort(R.string.session_expired);
        applicationContext.startActivity(intent);

    }

    private boolean isRefreshTokenExpiredInOneWeek() {
        Date currentDate = new Date();
        long diff = currentDate.getTime() - getRefreshTokenExpiredDate().getTime();
        long days = diff / (24 * 60 * 60 * 1000);
        return days <= 7 && days > 0;

    }

    @Override
    public Date getRefreshTokenExpiredDate() {
        String refreshToken = TokenSharedPreference.getInstance().getRefreshToken();
        if (refreshToken == null || refreshToken.isEmpty()) return new Date();
        return getExpiredDateFromJWTToken(refreshToken);
    }

    @Override
    public Date getAccessTokenExpiredDate() {
        String accessToken = TokenSharedPreference.getInstance().getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) return new Date();
        return getExpiredDateFromJWTToken(accessToken);
    }

    @Override
    public void deleteAuth() {
        TokenSharedPreference.getInstance().deleteToken();
    }

    private boolean isExpired(Date compareDate) {
        Date currentTime = new Date();
        return compareDate.before(currentTime) || compareDate.equals(currentTime);
    }

    private Date getExpiredDateFromJWTToken(String token) {
        if (token == null || token.isEmpty())
            return null;
        try {
            String JWTData = getJWTDataStringOrEmptyString(token);
            String unixDate = Integer.toString(getIntValueFromJSON(JWTData, "exp"));
            return getTimestampToDate(unixDate);
        } catch (Exception e) {
            return null;
        }
    }

    private Date getTimestampToDate(String timestampStr) {
        long timestamp = Long.parseLong(timestampStr);
        Date date = new java.util.Date(timestamp * 1000L);
        return date;
    }

    private String getJWTDataStringOrEmptyString(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");

            return getJson(split[1]);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    private String getStringValueFromJSON(String json, String key) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString(key);
    }

    private Integer getIntValueFromJSON(String json, String key) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getInt(key);
    }

    private void refreshToken() {
        String token = getRefreshToken();
        if (token == null) {
            refreshTokenExpiredAction();
        }
        Thread loginThread = new Thread() {
            @Override
            public void run() {
                try {
                    Token updatedToken = UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).refreshToken(UserRetrofitManager.addAuthorizationBearer(token))
                            .execute().body();
                    if (updatedToken != null && updatedToken.getAccessToken() != null && updatedToken.getRefreshToken() != null) {
                        JWTTokenManager.getInstance().saveAccessToken(updatedToken.getAccessToken());
                        JWTTokenManager.getInstance().saveRefreshToken(updatedToken.getRefreshToken());
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    refreshTokenExpiredAction();
                }
            }
        };
        loginThread.start();
        try {
            loginThread.join();
        } catch (Exception e) {
            refreshTokenExpiredAction();
        }

    }

    private static class Holder {
        public static final JWTTokenManager INSTANCE = new JWTTokenManager();
    }
}

package com.koreatech.naeilro.auth;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.UserRetrofitManager;
import com.koreatech.naeilro.network.entity.user.Token;
import com.koreatech.naeilro.network.service.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

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
        if (isRefreshTokenExpired())
            return "";
        return TokenSharedPreference.getInstance().getRefreshToken();
    }

    @Override
    public String getAccessToken() {
        if (isRefreshTokenExpired() && isAccessTokenExpired())
            return "";
        if (!isRefreshTokenExpired())
            updateToken();
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
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            refreshToken(new ApiCallback() {
                @Override
                public void onSuccess(Object object) {
                    lock.unlock();
                }

                @Override
                public void onFailure(Throwable throwable) {
                    lock.unlock();
                }
            });
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isAccessTokenExpired() {
        return isExpired(getAccessTokenExpiredDate());
    }

    @Override
    public boolean isRefreshTokenExpired() {
        return isExpired(getRefreshTokenExpiredDate());
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

    private void refreshToken(ApiCallback apiCallback) {
        String token = getRefreshToken();
        if (token == null) {
            apiCallback.onFailure(new Exception("Token Empty"));
            return;
        }

        UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).refreshToken(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Token token) {
                        if (token != null && token.getAccessToken() != null && token.getRefreshToken() != null) {
                            JWTTokenManager.getInstance().saveAccessToken(token.getAccessToken());
                            JWTTokenManager.getInstance().saveRefreshToken(token.getRefreshToken());
                            apiCallback.onSuccess(token);
                        } else
                            apiCallback.onFailure(new Throwable("fail update token"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d(TAG, ((HttpException) e).code() + " ");
                        }
                        apiCallback.onFailure(e);
                        Log.e("sdf", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static class Holder {
        public static final JWTTokenManager INSTANCE = new JWTTokenManager();
    }
}

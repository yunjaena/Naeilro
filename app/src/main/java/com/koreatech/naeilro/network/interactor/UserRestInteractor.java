package com.koreatech.naeilro.network.interactor;

import android.util.ArrayMap;
import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.UserRetrofitManager;
import com.koreatech.naeilro.auth.JWTTokenManager;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.entity.user.Token;
import com.koreatech.naeilro.network.entity.user.UserInfo;
import com.koreatech.naeilro.network.service.UserService;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.HttpException;

public class UserRestInteractor implements UserInteractor {
    public static final String TAG = "UserRestInteractor";

    @Override
    public void signUp(ApiCallback apiCallback, String name, String pw, String email, String phoneNumber) {

        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("name", name);
        jsonObject.put("email", email);
        jsonObject.put("pw", pw);
        jsonObject.put("phone", phoneNumber);


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());
        UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).postEnroll(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnrollObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EnrollObject enrollObject) {
                        if (enrollObject != null) {
                            Log.e("presenter", Integer.toString(enrollObject.getSuccess()));
                            apiCallback.onSuccess(enrollObject);
                        }
                        else
                            apiCallback.onFailure(new Throwable("fail registeration"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d("enrollRestInteractor", ((HttpException) e).code() + " ");
                        }
                        apiCallback.onFailure(e);
                        Log.e("sdf", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void signIn(ApiCallback apiCallback, String email, String password) {

        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("email", email);
        jsonObject.put("pw", password);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonObject)).toString());

        Observable<Token> loginObservable = UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).postSignIn(body);
        Observable<Token> refreshObservable = UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).getRefreshToken(body);

        loginObservable.flatMap(token -> {
            if (token != null && token.getAccessToken() != null) {
                Log.d(TAG, "signIn: " + token.getAccessToken());
                JWTTokenManager.getInstance().saveAccessToken(token.getAccessToken());
                return refreshObservable;
            } else {
                throw new Exception("login failed");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Token token) {
                        if (token != null && token.getRefreshToken() != null) {
                            JWTTokenManager.getInstance().saveRefreshToken(token.getRefreshToken());
                            apiCallback.onSuccess(token);
                        } else
                            apiCallback.onFailure(new Throwable("fail login"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d(TAG, ((HttpException) e).code() + " ");
                        }
                        apiCallback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void logOut(ApiCallback apiCallback, String token) {
        UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).logOut(UserRetrofitManager.addAuthorizationBearer(token))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        if (userInfo != null ) {
                            apiCallback.onSuccess(userInfo);
                        } else
                            apiCallback.onFailure(new Throwable("fail get userInfo"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d(TAG, ((HttpException) e).code() + " ");
                        }
                        apiCallback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getUserInfo(ApiCallback apiCallback, String token) {

            UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).getUserInfo(UserRetrofitManager.addAuthorizationBearer(token))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UserInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UserInfo userInfo) {
                            if (userInfo != null ) {
                                apiCallback.onSuccess(userInfo);
                            } else
                                apiCallback.onFailure(new Throwable("fail get userInfo"));
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                Log.d(TAG, ((HttpException) e).code() + " ");
                            }
                            apiCallback.onFailure(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });


    }

    @Override
    public void getRefreshToken(ApiCallback apiCallback, String email, String password) {

    }

    @Override
    public void getNewAccessToken(ApiCallback apiCallback) {

    }
}

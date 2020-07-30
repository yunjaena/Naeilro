package com.koreatech.naeilro.network.interactor;

import android.util.ArrayMap;
import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.UserRetrofitManager;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.entity.user.SignIn;
import com.koreatech.naeilro.network.service.UserService;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.HttpException;

public class UserRestInteractor implements UserInteractor {
    public static final String TAG = "UserRestInteractor";

    @Override
    public void signUp(ApiCallback apiCallback, String name, String pw, String email) {

        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("name", name);
        jsonObject.put("email", email);
        jsonObject.put("pw", pw);


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
                        if (enrollObject != null)
                            apiCallback.onSuccess(enrollObject);
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
        UserRetrofitManager.getInstance().getRetrofit().create(UserService.class).postSignIn(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignIn>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignIn signIn) {
                        if (signIn != null)
                            apiCallback.onSuccess(signIn);
                        else
                            apiCallback.onFailure(new Throwable("fail login"));
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
}
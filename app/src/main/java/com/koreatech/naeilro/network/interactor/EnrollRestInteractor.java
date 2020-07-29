package com.koreatech.naeilro.network.interactor;

import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.UserRetrofitManager;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;
import com.koreatech.naeilro.network.service.EnrollService;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;

import javax.annotation.Nullable;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import retrofit2.HttpException;

public class EnrollRestInteractor implements EnrollInteractor {
    @Override
    public void getAccept(ApiCallback apiCallback, String name, String pw, String email) {

        Map<String, Object> jsonObject = new ArrayMap<>();
        jsonObject.put("name", name);
        jsonObject.put("email", email);
        jsonObject.put("pw", pw);


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonObject)).toString());
        UserRetrofitManager.getInstance().getRetrofit().create(EnrollService.class).postEnroll(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnrollObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EnrollObject enrollObject) {
                        if(enrollObject != null)
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
                        Log.e("sdf",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
